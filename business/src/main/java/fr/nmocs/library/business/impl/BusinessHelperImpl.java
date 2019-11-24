package fr.nmocs.library.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.nmocs.library.business.BusinessHelper;
import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.constants.BookSampleStatus;
import fr.nmocs.library.model.constants.BookStatus;

@Service
public class BusinessHelperImpl implements BusinessHelper {

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

		if (book != null) {
			if (book.getReservations() != null) {
				Date reservationPeremptionDate = getReservationMaxMailedDate();
				// A) On renseigne les différents utilisateurs ayant réservé le livre
				infos.setReservers(book.getReservations().stream()
						// dont la notification par mail n'est pas passé de X ms
						.filter(r -> r != null
								&& (r.getMailedDate() == null || r.getMailedDate().before(reservationPeremptionDate)))
						.map(r -> r.getReserver()).collect(Collectors.toList()));
			}
			if (book.getSamples() != null && !book.getSamples().isEmpty()) {
				// ===== LISTE DES PRET EN COURS POUR LE LIVRE
				List<Loan> activeLoans = book.getSamples().stream()
						// On récupère tous les prets associés aux exemplaire du livre
						.filter(s -> s != null && s.getLoans() != null && !s.getLoans().isEmpty())
						.map(s -> s.getLoans().stream().collect(Collectors.toList()))
						.reduce(new ArrayList<Loan>(), (accumulator, ls) -> ListUtils.sum(accumulator, ls))
						// et on ne garde que ceux qui ne sont pas rendus (returnDate = null)
						.stream().filter(l -> l.getReturnDate() == null).collect(Collectors.toList());

				// B) On renseigne les utilisateurs qui louent actuellement un exemplaire du
				// livre
				infos.setBorrowers(activeLoans.stream().map(l -> l.getBorrower()).collect(Collectors.toList()));

				if (book.getStatus().equals(BookStatus.AVAILABLE.getValue())) {
					// ===== LISTE DES EXEMPLAIRES DONT L'ETAT EST CORRECT (status = AVAILABLE)
					List<BookSample> borrowableBookSamples = book.getSamples().stream()
							.filter(s -> s != null && s.getStatus().equals(BookSampleStatus.AVAILABLE.getValue()))
							.collect(Collectors.toList());

					// C) Le livre est disponible si
					infos.setIsAvailable(borrowableBookSamples.stream()
							// au moins un de ses exemplaire n'a aucun emprunt en cours (returnDate !=null)
							.anyMatch(bs -> bs.getLoans() == null || bs.getLoans().isEmpty()
									|| bs.getLoans().stream().allMatch(l -> l.getReturnDate() != null))
							// et que personne ne l'a réservé
							&& infos.getReservers().isEmpty());
					if (infos.getIsAvailable()) {
						// D) On rensegine le nombre d'exemplaires disponibles
						infos.setAvailableSamplesNumber(
								(int) borrowableBookSamples.stream()
										.filter(bs -> bs.getLoans() == null || bs.getLoans().isEmpty()
												|| bs.getLoans().stream().allMatch(l -> l.getReturnDate() != null))
										.count());
					} else {
						// E) On renseigne la taille max de ma fome de réservation

						infos.setQueueMaxSize(borrowableBookSamples.size() * RESERVATION_QUEUE_SAMPLE_FACTOR);
						// F) Le livre est réservable si le nombre d'exemplaire * X est supérieur au
						// nombre
						// de réservations
						infos.setIsReservable(infos.getQueueMaxSize() > infos.getReservers().size());

						// Enfin, on estime les date de disponibilité minimum et maximum
						if (activeLoans != null && !activeLoans.isEmpty()) {
							// G) Le plus tot = date min de retour prévue
							Date soonest = new Date(activeLoans.stream()
									.map(loan -> getLoanActualEndDate(loan).getTime()).min(Long::compare).get());
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

			}
		}
		return infos;
	}

	@Override
	public Date getReservationMaxMailedDate() {
		return new Date((new Date()).getTime() - RESERVATION_VALIDITY_AFTERMAIL_MS);
	}
}
