package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.Reservation;
import fr.nmocs.library.model.pk.ReservationPK;

/**
 * [TK1] => Faire des réservations
 * 
 * @author nathanael
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, ReservationPK> {

	/**
	 * Retrieving reservations by the book
	 * 
	 * @param bookId
	 * @return
	 */
	List<Reservation> findByIdBookId(Integer bookId);

	/**
	 * Retrieving the nb of reservations for a book
	 * 
	 * @param bookId
	 * @return
	 */
	int countByIdBookId(Integer bookId);
}
