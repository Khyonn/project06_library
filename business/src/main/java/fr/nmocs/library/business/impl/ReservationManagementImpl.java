package fr.nmocs.library.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.BusinessHelper;
import fr.nmocs.library.business.ReservationManagement;
import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.consumer.BookRepository;
import fr.nmocs.library.consumer.BookSampleRepository;
import fr.nmocs.library.consumer.LoanRepository;
import fr.nmocs.library.consumer.ReservationRepository;
import fr.nmocs.library.consumer.UserRepository;
import fr.nmocs.library.model.Book;
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
 * @author Nathanaël
 *
 */
@Service
public class ReservationManagementImpl implements ReservationManagement {

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private UserRepository useRepo;

	@Autowired
	private BookRepository bookRepo;

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
		reservation.setMailedDate(null);
		reservation.setReservationDate(new Date());
		checkBusiness(reservation);

		Book book = bookRepo.findByIdFetchReservationsAndSampleAndLoans(reservation.getBook().getId());
		ReservationQueue infos = businessHelper.getBookReservationInfos(book);

		Reservation createdReservation = reservationRepo.save(reservation);

		// Si le livre est déjà disponible => envoyer un email !!
		if (infos.getIsAvailable()) {
			businessHelper.notifyFirstReserver(book);
		}
		return reservationRepo.findById(createdReservation.getId()).orElse(null);
	}

	@Override
	@Transactional
	public void deleteReservation(ReservationPK id) throws LibraryException {
		if (!reservationRepo.existsById(id)) {
			throw new LibraryTechnicalException(ErrorCode.RESERVATION_NOT_FOUND);
		}

		// Si la réservation ne correspond à aucun pret en cours mais que le reserveur a
		// déjà été notifié, on peut notifier le prochain reserveur
		Reservation reservation = reservationRepo.findById(id).orElse(null);
		Boolean shouldNotify = reservation.getMailedDate() != null
				&& !loanRepo.existsByBorrowerIdAndBookSampleBookIdAndReturnDateIsNull(reservation.getReserver().getId(),
						reservation.getBook().getId());

		reservationRepo.deleteById(id);
		if (shouldNotify) {
			businessHelper.notifyFirstReserver(reservation.getBook());
		}
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
		// La liste de réservation ne peut comporter qu’un maximum de personnes
		// correspondant à 2x le nombre d’exemplaires de l’ouvrage. [AVEC ouvrage.status
		// == DISPONIBLE et exemplaire.status == DISPONIBLE]
		int bookSampleNumber = bookSampleRepo.countByStatusAndBookIdAndBookStatus(BookSampleStatus.AVAILABLE.getValue(),
				reservation.getId().getBookId(), BookStatus.AVAILABLE.getValue());
		int reservationNumber = reservationRepo.countByIdBookId(reservation.getId().getBookId());
		if (reservationNumber >= bookSampleNumber * RESERVATION_QUEUE_SAMPLE_FACTOR) {
			throw new LibraryBusinessException(ErrorCode.RESERVATION_NB);
		}

		// Il n’est pas possible pour un usager de réserver un ouvrage qu’il a déjà en
		// cours d’emprunt
		if (loanRepo.existsByBorrowerIdAndBookSampleBookIdAndReturnDateIsNull(reservation.getId().getReserverId(),
				reservation.getId().getBookId())) {
			throw new LibraryBusinessException(ErrorCode.RESERVATION_RESERVER_ALREADY_BORROWING);
		}

	}

	@Override
	public List<Reservation> findByUserId(Integer userId) throws LibraryException {
		if (!reservationRepo.existsByIdReserverId(userId)) {
			return new ArrayList<>();
		}
		return reservationRepo.findByIdReserverId(userId);
	}

}
