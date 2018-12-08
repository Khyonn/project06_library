package fr.nmocs.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Loans")
public class Loan {

	@Id
	@GeneratedValue
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

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the bookSample
	 */
	public BookSample getBookSample() {
		return bookSample;
	}

	/**
	 * @param bookSample the bookSample to set
	 */
	public void setBookSample(BookSample bookSample) {
		this.bookSample = bookSample;
	}

	/**
	 * @return the borrower
	 */
	public User getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the prolongationNumber
	 */
	public Integer getProlongationNumber() {
		return prolongationNumber;
	}

	/**
	 * @param prolongationNumber the prolongationNumber to set
	 */
	public void setProlongationNumber(Integer prolongationNumber) {
		this.prolongationNumber = prolongationNumber;
	}

}
