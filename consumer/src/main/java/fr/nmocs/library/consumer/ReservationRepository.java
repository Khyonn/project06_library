package fr.nmocs.library.consumer;

import java.util.Date;
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
	 * Retrieving reservations by the reserver
	 * 
	 * @param reserverId
	 * @return
	 */
	List<Reservation> findByIdReserverId(Integer reserverId);

	/**
	 * Retrieving the nb of reservations for a book
	 * 
	 * @param bookId
	 * @return
	 */
	int countByIdBookId(Integer bookId);

	/**
	 * Returns if a reservation exists for a book id
	 * 
	 * @param bookId
	 * @return
	 */
	boolean existsByIdBookId(Integer bookId);

	/**
	 * Returns if a reservation exists for a reserver id
	 * 
	 * @param reserverId
	 * @return
	 */
	boolean existsByIdReserverId(Integer reserverId);

	/**
	 * Returns if a reservation exists for a book id and a reserver id
	 * 
	 * @param bookId
	 * @param reserverId
	 * @return
	 */
	boolean existsByIdBookIdAndIdReserverId(Integer bookId, Integer reserverId);

	/**
	 * Deletes a reservation when the mailed date is inferior to the given date
	 * 
	 * @param date
	 */
	void deleteByMailedDateNotNullAndMailedDateLessThan(Date date);

	List<Reservation> findByIdBookIdAndMailedDateIsNull(Integer id);
}
