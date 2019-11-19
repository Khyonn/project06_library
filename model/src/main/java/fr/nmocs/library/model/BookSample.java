package fr.nmocs.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BookSamples")
@Getter
@Setter
public class BookSample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bookId")
	private Book book;

	@Column(name = "bookSampleStatus", nullable = false)
	private String status;

	@OneToMany(mappedBy = "bookSample", fetch = FetchType.LAZY)
	private Set<Loan> loans;

}
