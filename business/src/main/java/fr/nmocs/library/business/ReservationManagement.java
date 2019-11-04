package fr.nmocs.library.business;

import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.pk.ReservationPK;

public interface ReservationManagement {

	Reservation createReservation(Reservation reservation) throws LibraryException;

	Reservation updateReservation(Reservation reservation) throws LibraryException;

	/**
	 * Returns reservations infos for this book
	 * 
	 * @param bookId
	 * @return
	 */
	ReservationQueue getBookReservationInfos(Integer bookId);

	void deleteReservation(ReservationPK id) throws LibraryException;
}
