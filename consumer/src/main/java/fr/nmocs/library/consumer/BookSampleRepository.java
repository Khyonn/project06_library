package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.nmocs.library.model.BookSample;

public interface BookSampleRepository extends JpaRepository<BookSample, Integer> {

	List<BookSample> findByBookId(Integer bookId);

	List<BookSample> findByBookIdAndStatusAndBookStatus(Integer bookId, String bookSampleStatus, String bookStatus);

	boolean existsByIdAndStatusAndBookStatus(Integer id, String bookSampleStatus, String bookStatus);

	boolean existsByStatusAndBookIdAndBookStatus(String bookSampleStatus, Integer bookId, String bookStatus);

	int countByStatusAndBookIdAndBookStatus(String bookSampleStatus, Integer bookId, String bookStatus);

	@Query(
		"SELECT bs FROM BookSample bs "
		+ "INNER JOIN FETCH bs.loans l "
		+ "WHERE bs.book.id = :bookId AND "
		+ "bs.book.status = :bookStatus AND "
		+ "bs.status = :bookSampleStatus"
	)
	List<BookSample> findByBookIdAndBookStatusAndBookSampleStatusFecthLoans(@Param("bookId") Integer bookId, @Param(
		"bookStatus"
	) String bookStatus, @Param("bookSampleStatus") String bookSampleStatus);

}
