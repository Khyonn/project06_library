package fr.nmocs.library.business.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.BusinessHelper;
import fr.nmocs.library.business.ReservationManagement;
import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.consumer.BookSampleRepository;
import fr.nmocs.library.consumer.LoanRepository;
import fr.nmocs.library.consumer.ReservationRepository;
import fr.nmocs.library.consumer.UserRepository;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.model.constants.BookSampleStatus;
import fr.nmocs.library.model.constants.BookStatus;
import fr.nmocs.library.model.constants.UserStatus;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;
import fr.nmocs.library.model.pk.ReservationPK;

/**
 * [TK1] => Faire des réservations
 * 
 * @author nathanael
 *
 */
@Service
public class ReservationManagementImpl implements ReservationManagement {

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private UserRepository useRepo;

	@Autowired
	private BookSampleRepository bookSampleRepo;

	@Autowired
	private LoanRepository loanRepo;

	@Autowired
	private BusinessHelper businessHelper;

	@Value("${business.reservations.queue.sizeFactorSample}")
	private Integer RESERVATION_QUEUE_SAMPLE_FACTOR;

	@Override
	@Transactional
	public Reservation createReservation(Reservation reservation) throws LibraryException {
		checkReservationFields(reservation);
		if (reservationRepo.existsById(reservation.getId())) {
			throw new LibraryBusinessException(ErrorCode.RESERVATION_ALREADY_EXIST);
		}
		reservation.setReservationDate(new Date());
		checkBusiness(reservation);
		return reservationRepo.save(reservation);
	}

	@Override
	public Reservation updateReservation(Reservation reservation) throws LibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ReservationQueue getBookReservationInfos(Integer bookId) {
		ReservationQueue infos = new ReservationQueue();
		// Nombre de réservation pour ce livre
		infos.setReservationNb(reservationRepo.countByIdBookId(bookId));
		// Nombre de réservation max pour ce livre : X fois le nombre d'exemplaire
		infos.setQueueSize(RESERVATION_QUEUE_SAMPLE_FACTOR * bookSampleRepo.countByStatusAndBookIdAndBookStatus(
				BookSampleStatus.AVAILABLE.getValue(), bookId, BookStatus.AVAILABLE.getValue()));

		List<Loan> loans = loanRepo.findByBookSampleBookIdAndReturnDateIsNull(bookId);
		if (loans != null && !loans.isEmpty()) {
			infos.setSoonestAvailabilityDate(new Date(loans.stream()
					.map(loan -> businessHelper.getLoanActualEndDate(loan).getTime()).min(Long::compare).get()));
			infos.setLatestAvailabilityDate(new Date(loans.stream()
					.map(loan -> businessHelper.getLoanMaxEndDate(loan).getTime()).min(Long::compare).get()));
		}
		return infos;
	}

	@Override
	public void deleteReservation(ReservationPK id) throws LibraryException {
		// TODO Auto-generated method stub

	}

	/**
	 * Check if id is setted (reserver + book) Check if book sample exists for this
	 * book Check if user exists
	 * 
	 * @param reservation
	 * @throws LibraryBusinessException
	 */
	private void checkReservationFields(Reservation reservation) throws LibraryTechnicalException {
		// Id non null
		if (reservation == null || reservation.getId() == null) {
			throw new LibraryTechnicalException(ErrorCode.RESERVATION_NULL);
		}
		// Reserver non null
		if (reservation.getId().getReserverId() == null || reservation.getId().getReserverId().equals(0)) {
			throw new LibraryTechnicalException(ErrorCode.RESERVATION_RESERVER_NULL);
		}
		// Livre non null
		if (reservation.getId().getBookId() == null || reservation.getId().getBookId().equals(0)) {
			throw new LibraryTechnicalException(ErrorCode.RESERVATION_BOOK_NULL);
		}
		// Reserver existant et actif
		if (!useRepo.existsByIdAndStatus(reservation.getId().getReserverId(), UserStatus.ACTIVE.getValue())) {
			throw new LibraryTechnicalException(ErrorCode.RESERVATION_RESERVER_NOTFOUND);
		}
		// Livre existant et 'disponible'
		if (!bookSampleRepo.existsByStatusAndBookIdAndBookStatus(BookSampleStatus.AVAILABLE.getValue(),
				reservation.getId().getBookId(), BookStatus.AVAILABLE.getValue())) {
			throw new LibraryTechnicalException(ErrorCode.RESERVATION_BOOKSAMPLE_NOTFOUND);
		}
	}

	/**
	 * Check all business rules
	 * 
	 * @param reservation
	 * @throws LibraryBusinessException
	 */
	private void checkBusiness(Reservation reservation) throws LibraryBusinessException {
		// Pas plus de X fois le nombre d’exemplaires de l’ouvrage
		int bookSampleNumber = ArrayUtils
				.getLength(bookSampleRepo.findByBookIdAndStatusAndBookStatus(reservation.getId().getBookId(),
						BookSampleStatus.AVAILABLE.getValue(), BookStatus.AVAILABLE.getValue()));
		int reservationNumber = ArrayUtils.getLength(reservationRepo.findByIdBookId(reservation.getId().getBookId()));

		if (reservationNumber >= bookSampleNumber * RESERVATION_QUEUE_SAMPLE_FACTOR) {
			throw new LibraryBusinessException(ErrorCode.RESERVATION_NB);
		}

		// L'emprunteur ne doit pas être en train de louer le livre
		if (loanRepo.existsByBorrowerIdAndBookSampleBookIdAndReturnDateIsNull(reservation.getId().getReserverId(),
				reservation.getId().getBookId())) {
			throw new LibraryBusinessException(ErrorCode.RESERVATION_RESERVER_ALREADY_BORROWING);
		}

	}

}
