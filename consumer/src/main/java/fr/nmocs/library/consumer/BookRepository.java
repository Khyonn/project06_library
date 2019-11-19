package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.nmocs.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(
		"SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.reservations r LEFT JOIN FETCH b.samples s LEFT JOIN FETCH s.loans l WHERE b.id = :id"
	)
	Book findByIdFetchReservationsAndSampleAndLoans(@Param("id") Integer id);

	List<Book> findByAuthorIgnoreCaseContaining(String author);

	List<Book> findByTitleIgnoreCaseContaining(String title);

}
