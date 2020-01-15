package fr.nmocs.library.webservice.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDTO {

	private Integer id;

	private BookSampleDTO bookSample;

	private UserDTO borrower;

	private Date startDate;

	private Date returnDate;

	private Integer prolongationNumber;
}
