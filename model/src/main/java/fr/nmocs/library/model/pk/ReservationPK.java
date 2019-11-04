package fr.nmocs.library.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ReservationPK implements Serializable {

	@Column(name = "bookId")
	private Integer bookId;

	@Column(name = "userId")
	private Integer reserverId;

	public ReservationPK () {
	}

	public ReservationPK (Integer bookId, Integer reserverId) {
		this.bookId = bookId;
		this.reserverId = reserverId;
	}

}
