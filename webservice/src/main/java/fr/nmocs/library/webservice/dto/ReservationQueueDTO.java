package fr.nmocs.library.webservice.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationQueueDTO {

	private List<UserDTO> reservers;

	private List<UserDTO> borrowers;

	private Boolean isAvailable;

	private Integer queueMaxSize;

	private Boolean isReservable;

	private Date soonestAvailabilityDate;

	private Date latestAvailabilityDate;

}
