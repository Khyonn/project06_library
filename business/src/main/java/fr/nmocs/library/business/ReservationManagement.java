package fr.nmocs.library.business;

import java.util.List;

import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.pk.ReservationPK;

public interface ReservationManagement {

	Reservation createReservation(Reservation reservation) throws LibraryException;

	void deleteReservation(ReservationPK id) throws LibraryException;

	List<Reservation> findByUserId(Integer userId) throws LibraryException;

}
