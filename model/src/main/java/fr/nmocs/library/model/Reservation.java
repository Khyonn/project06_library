package fr.nmocs.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import fr.nmocs.library.model.pk.ReservationPK;
import lombok.Getter;
import lombok.Setter;

/**
 * [TK1] => Book Reservation
 * 
 * @author nathanael
 *
 */
@Getter
@Setter
@Entity
@Table(name = "Reservations")
public class Reservation {

	@EmbeddedId
	private ReservationPK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("reserverId")
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User reserver;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("bookId")
	@JoinColumn(name = "bookId", insertable = false, updatable = false)
	private Book book;

	@Column(name = "reservationDate", nullable = false)
	private Date reservationDate;

	@Column(name = "mailedDate", nullable = true)
	private Date mailedDate;

}
