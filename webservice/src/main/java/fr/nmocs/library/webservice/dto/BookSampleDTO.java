package fr.nmocs.library.webservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSampleDTO {

	private Integer id;

	private BookDTO book;

	private String status;
}
