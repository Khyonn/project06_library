package fr.nmocs.library.webapp.actions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.ws.BookDTO;
import fr.nmocs.library.webapp.ws.CreateReservation;
import fr.nmocs.library.webapp.ws.DeleteReservation;
import fr.nmocs.library.webapp.ws.FindReservationsByUserId;
import fr.nmocs.library.webapp.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.ws.ReservationDTO;
import fr.nmocs.library.webapp.ws.ReservationService;
import fr.nmocs.library.webapp.ws.UserDTO;

@SuppressWarnings("serial")
public class UserReservationAction extends LibraryAbstractAction {

	@Autowired
	ReservationService reservationService;

	// === INPUT
	private Integer bookId;

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	// ===== OUTPUT
	private List<ReservationDTO> reservations;

	public List<ReservationDTO> getReservations() {
		return reservations;
	}

	public String doConsult() {
		if (!getIsUserConnected()) {
			return LOGIN;
		}

		FindReservationsByUserId params = new FindReservationsByUserId();
		params.setUserId(getUserId());
		try {
			reservations = reservationService.findReservationsByUserId(params, getUserToken()).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			reservations = new ArrayList<ReservationDTO>();
		}
		return SUCCESS;
	}

	private ReservationDTO createReservationFromInput() {
		BookDTO b = new BookDTO();
		b.setId(bookId);
		UserDTO reserver = new UserDTO();
		reserver.setId(getUserId());

		ReservationDTO r = new ReservationDTO();
		r.setBook(b);
		r.setReserver(reserver);
		return r;
	}

	public String doDelete() {
		if (!getIsUserConnected()) {
			return LOGIN;
		}

		DeleteReservation params = new DeleteReservation();
		params.setReservation(createReservationFromInput());
		try {
			reservationService.deleteReservation(params, getUserToken());
			return SUCCESS;
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String doCreate() {
		if (!getIsUserConnected()) {
			return LOGIN;
		}

		CreateReservation params = new CreateReservation();
		params.setReservation(createReservationFromInput());
		try {
			reservationService.createReservation(params, getUserToken());
			return SUCCESS;
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
	}
}
