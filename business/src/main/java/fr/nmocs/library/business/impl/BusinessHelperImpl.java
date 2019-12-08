package fr.nmocs.library.business.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.BusinessHelper;
import fr.nmocs.library.business.email.LibraryEmailUtils;
import fr.nmocs.library.business.model.LibraryEmail;
import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.consumer.ReservationRepository;
import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.model.constants.BookSampleStatus;
import fr.nmocs.library.model.constants.BookStatus;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class BusinessHelperImpl implements BusinessHelper {

	@Autowired
	private LibraryEmailUtils emailUtils;

	@Autowired
	private ReservationRepository reservationRepo;

	// ===== Règles métiers :

	// === UN SEUL PROLONGEMENT POSSIBLE
	private final Integer MAX_PROLONGATION_NB;

	// === NOMBRE DE MS DE PRET
	private final long LOAN_BASETIME_MS;

	// === TEMPS EN MS D'UNE PROLONGATION D'UN PRET
	private final long PROLONGATION_MS;

	// === TEMPS DE VALIDITE APRES MAIL
	private final long RESERVATION_VALIDITY_AFTERMAIL_MS;

	// === FACTEUR TAILLE QUEUE RESERVATION (nb exemplaires * facteur = nombre de
	// réservations)
	private final Integer RESERVATION_QUEUE_SAMPLE_FACTOR;

	public BusinessHelperImpl (@Value("${business.loans.prolongation.maxNb}") int maxProlongationNb, @Value(
					"${business.loans.time.weeksNb}") long loanBasetimeWeeks,
			@Value("${business.loans.prolongation.time.weeksNb}") long prolongationWeeks, @Value(
							"${business.reservations.queue.sizeFactorSample}") int sizeFactorSample,
			@Value("${business.reservation.peremption.dayNb}") long reservationPeremptionDayNb) {
		this.MAX_PROLONGATION_NB = maxProlongationNb;
		this.LOAN_BASETIME_MS = loanBasetimeWeeks * 7 * 24 * 3600 * 1000;
		this.PROLONGATION_MS = prolongationWeeks * 7 * 24 * 3600 * 1000;
		this.RESERVATION_QUEUE_SAMPLE_FACTOR = sizeFactorSample;
		this.RESERVATION_VALIDITY_AFTERMAIL_MS = reservationPeremptionDayNb * 24 * 3600 * 1000;
	}

	/**
	 * Returns a Date coresponding to the loan start date and the given prolongation
	 * number
	 * 
	 * @param startDate
	 * @param prolongationNumber
	 * @return
	 */
	private Date getLoanEndDate(Date startDate, Integer prolongationNumber) {
		// Retour le plus tard : Jour de location + temps de location + (temps de
		// prolongement * nombre de prolongement)
		return new Date(startDate.getTime() + LOAN_BASETIME_MS + (PROLONGATION_MS * prolongationNumber));
	}

	/**
	 * Returns the actual last date of return
	 * 
	 * @param loan
	 * @return
	 */
	@Override
	public Date getLoanActualEndDate(Loan loan) {
		return getLoanEndDate(loan.getStartDate(), loan.getProlongationNumber());
	}

	/**
	 * Returns the latest date of return
	 * 
	 * @param loan
	 * @return
	 */
	@Override
	public Date getLoanMaxEndDate(Loan loan) {
		return getLoanEndDate(loan.getStartDate(), MAX_PROLONGATION_NB);
	}

	@Override
	public ReservationQueue getBookReservationInfos(Book book) {
		ReservationQueue infos = new ReservationQueue();

		if (book != null && book.getStatus() != null && book.getStatus().equals(BookStatus.AVAILABLE.getValue())) {
			if (book.getReservations() != null && !book.getReservations().isEmpty()) {
				Date reservationPeremptionDate = getReservationMaxMailedDate();
				// A) On renseigne les différents utilisateurs ayant réservé le livre
				// afin de permettre aux FRONTS de savoir si un utilisateur le réserve
				infos.setReservers(book.getReservations().stream()
						// dont la notification par mail n'est pas passé de X ms
						.filter(r -> r != null
								&& (r.getMailedDate() == null || r.getMailedDate().after(reservationPeremptionDate)))
						.sorted(Comparator.comparingLong(r -> r.getReservationDate().getTime()))
						.map(r -> r.getReserver()).collect(Collectors.toList()));
			}
			if (book.getSamples() != null && !book.getSamples().isEmpty()) {
				// ===== ON LISTE LES PRETS EN COURS POUR LE LIVRE
				List<Loan> activeLoans = book.getSamples().stream()
						// On récupère tous les prets associés aux exemplaire du livre
						.filter(s -> s != null && s.getLoans() != null && !s.getLoans().isEmpty())
						.map(s -> s.getLoans().stream().collect(Collectors.toList()))
						.reduce(new ArrayList<Loan>(), (accumulator, ls) -> ListUtils.sum(accumulator, ls))
						// et on ne garde que ceux qui ne sont pas rendus (returnDate = null)
						.stream().filter(l -> l.getReturnDate() == null).collect(Collectors.toList());

				// B) On renseigne les utilisateurs qui louent actuellement un exemplaire du
				// livre (afin de permettre aux FRONTS de savoir si un utilisateur le loue)
				infos.setBorrowers(activeLoans.stream().map(l -> l.getBorrower()).collect(Collectors.toList()));

				// ===== LISTE DES EXEMPLAIRES DONT L'ETAT EST CORRECT (status = AVAILABLE)
				List<BookSample> borrowableBookSamples = book.getSamples().stream()
						.filter(s -> s != null && s.getStatus().equals(BookSampleStatus.AVAILABLE.getValue()))
						.collect(Collectors.toList());

				// C) On rensegine le nombre d'exemplaires disponibles :
				// (ceux qui n'ont pas de pret en cours rattachés)
				infos.setAvailableSamplesNumber((int) borrowableBookSamples.stream()
						.filter(bs -> bs.getLoans() == null || bs.getLoans().isEmpty()
								|| bs.getLoans().stream().allMatch(l -> l.getReturnDate() != null))
						.count()
						// MOINS le nombre de réservations
						- infos.getReservers().size());
				if (infos.getAvailableSamplesNumber() <= 0) {
					infos.setAvailableSamplesNumber(0);
				}

				// D) S'il y a des exemplaires disponibles (non réservés et sans emprunts)
				infos.setIsAvailable(infos.getAvailableSamplesNumber() - infos.getReservers().size()
						- infos.getBorrowers().size() > 0);

				// E) On renseigne la taille max de ma file de réservation
				infos.setQueueMaxSize(borrowableBookSamples.size() * RESERVATION_QUEUE_SAMPLE_FACTOR);

				// F) Le livre est réservable si le nombre d'exemplaire * X est supérieur au
				// nombre de réservations
				infos.setIsReservable(infos.getQueueMaxSize() > infos.getReservers().size());

				// Enfin, on estime les date de disponibilité minimum et maximum
				if (activeLoans != null && !activeLoans.isEmpty()) {
					// G) Le plus tot = date min de retour prévue
					Date soonest = new Date(activeLoans.stream().map(loan -> getLoanActualEndDate(loan).getTime())
							.min(Long::compare).get());
					if (infos.getSoonestAvailabilityDate().before(soonest)) {
						infos.setSoonestAvailabilityDate(soonest);
					}

					// H) Le plus tard = date min de retour + temps prolongement
					Date latest = new Date(activeLoans.stream().map(loan -> getLoanMaxEndDate(loan).getTime())
							.min(Long::compare).get());
					if (infos.getLatestAvailabilityDate().before(latest)) {
						infos.setLatestAvailabilityDate(latest);
					}
				}
			}
		}
		return infos;
	}

	@Override
	public Date getReservationMaxMailedDate() {
		return new Date((new Date()).getTime() - RESERVATION_VALIDITY_AFTERMAIL_MS);
	}

	@Override
	@Transactional
	public void notifyFirstReserver(Book book) {
		Reservation firstReservation = reservationRepo.findByIdBookIdAndMailedDateIsNull(book.getId()).stream()
				.sorted(Comparator.comparingLong(r -> r.getReservationDate().getTime())).findFirst().orElse(null);

		if (firstReservation == null) {
			return;
		}
		// ==== Send email
		LibraryEmail email = new LibraryEmail();
		email.setTo(firstReservation.getReserver().getEmail());
		email.setSubject("Your reservation is available");
		StringBuilder sb = new StringBuilder();
		sb.append(book.getTitle())
				.append(" is now available in your library\nPlease come borrow your sample within two days.\n");
		email.setBody(sb.toString());
		try {
			emailUtils.sendEmail(email);
		} catch (LibraryTechnicalException e) {
			return;
		} finally {
			// ===== Update reservation mailed date
			firstReservation.setMailedDate(new Date());
			reservationRepo.save(firstReservation);
		}
	}
}
