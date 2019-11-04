package fr.nmocs.library.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "summary", nullable = true)
	private String summary;

	@Column(name = "bookStatus")
	private String status;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<Reservation> reservations;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<BookSample> samples;
}
