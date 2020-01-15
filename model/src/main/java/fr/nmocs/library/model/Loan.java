package fr.nmocs.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Loans")
@Getter
@Setter
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bookSampleId")
	private BookSample bookSample;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "borrowerId")
	private User borrower;

	@Column(name = "startDate", nullable = false)
	private Date startDate;

	@Column(name = "returnDate", nullable = true)
	private Date returnDate;

	@Column(name = "prolongationNumber", nullable = false)
	private Integer prolongationNumber;

}
