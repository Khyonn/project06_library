package fr.nmocs.library.webservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	private Integer id;

	private String title;

	private String author;

	private String summary;

	private String status;

	private ReservationQueueDTO reservationQueueInfos;
}
