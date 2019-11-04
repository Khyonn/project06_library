package fr.nmocs.library.business.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationQueue {

	private Integer reservationNb;

	private Integer queueSize;

	private Date soonestAvailabilityDate;

	private Date latestAvailabilityDate;

	public Boolean getIsReservable() {
		return reservationNb != null && queueSize != null && reservationNb < queueSize;
	}
}
