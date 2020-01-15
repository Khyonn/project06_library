package fr.nmocs.library.webservice.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.ReservationManagement;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.pk.ReservationPK;
import fr.nmocs.library.webservice.ReservationService;
import fr.nmocs.library.webservice.dto.ReservationDTO;
import fr.nmocs.library.webservice.dto.mapper.ReservationMapper;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class ReservationServiceImpl implements ReservationService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private AuthManagement authMgmt;

	@Autowired
	private ReservationManagement reservationMgmt;

	@Override
	public ReservationDTO createReservation(ReservationDTO reservation, String token)
			throws LibraryWebserviceException {
		Integer userId = authMgmt.getUserId(token);
		// Si l'utilisateur n'est pas un admin ou n'est pas l'acteur de la réservation
		if (!authMgmt.isAdmin(token) && (userId != null && reservation != null && reservation.getReserver() != null
				&& !userId.equals(reservation.getReserver().getId()))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
		try {
			return ReservationMapper.INSTANCE
					.toDTO(reservationMgmt.createReservation(ReservationMapper.INSTANCE.fromDTO(reservation)));
		} catch (LibraryException e) {
			throw new LibraryWebserviceException(getExceptionReason(e));
		}
	}

	@Override
	public void deleteReservation(ReservationDTO reservation, String token) throws LibraryWebserviceException {
		Integer userId = authMgmt.getUserId(token);
		// Si l'utilisateur n'est pas un admin ou n'est pas l'acteur de la réservation
		if (!authMgmt.isAdmin(token) && (userId != null && reservation != null && reservation.getReserver() != null
				&& !userId.equals(reservation.getReserver().getId()))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
		try {
			reservationMgmt.deleteReservation(
					new ReservationPK(reservation.getBook().getId(), reservation.getReserver().getId()));
		} catch (LibraryException e) {
			throw new LibraryWebserviceException(getExceptionReason(e));
		}

	}

	@Override
	public List<ReservationDTO> findReservationsByUserId(Integer userId, String token)
			throws LibraryWebserviceException {
		Integer userTokenId = authMgmt.getUserId(token);
		// Si l'utilisateur n'est pas un admin ou n'est pas l'acteur de la réservation
		if (!authMgmt.isAdmin(token) && (userTokenId != null && !userTokenId.equals(userId))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
		try {
			return reservationMgmt.findByUserId(userId).stream().map(ReservationMapper.INSTANCE::toDTO)
					.collect(Collectors.toList());
		} catch (LibraryException e) {
			throw new LibraryWebserviceException(getExceptionReason(e));
		}
	}

	private String getExceptionReason(LibraryException le) {
		String reason = le.getErrorCode().getId() + " : ";
		switch (le.getErrorCode()) {
		// FIELD MISSING
		case RESERVATION_ALREADY_EXIST:
			reason += "The reservation is already saved";
			break;
		case RESERVATION_NOT_FOUND:
			reason += "Did not found the reservation";
			break;
		case RESERVATION_NULL:
			reason += "Reservation is not provided";
			break;
		case RESERVATION_RESERVER_NULL:
			reason += "Reserver is not provided";
			break;
		case RESERVATION_BOOK_NULL:
			reason += "Cannot create reservation without book";
			break;
		case RESERVATION_RESERVER_NOTFOUND:
			reason += "Reserver doesn't exists";
			break;
		case RESERVATION_BOOKSAMPLE_NOTFOUND:
			reason += "There's no sample of this book";
			break;
		case RESERVATION_NB:
			reason += "Cannot create reservation : the queue size for this book is too long";
			break;
		case RESERVATION_RESERVER_ALREADY_BORROWING:
			reason += "Cannot create reservation : the reserver is already borrowing the book";
			break;
		default:
			reason += "Unknown reason";
			break;
		}
		return reason;
	}

}
