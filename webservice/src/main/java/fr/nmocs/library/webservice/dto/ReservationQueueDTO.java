package fr.nmocs.library.webservice.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationQueueDTO {

	private Integer reservationNb;

	private Integer queueSize;

	private Date soonestAvailabilityDate;

	private Date latestAvailabilityDate;

	public boolean isReservable;
}
