package fr.nmocs.library.webservice.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

	private BookDTO book;

	private UserDTO reserver;

	private Date reservationDate;

	private Date mailedDate;
}
