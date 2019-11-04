package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.BookSample;

public interface BookSampleRepository extends JpaRepository<BookSample, Integer> {

	List<BookSample> findByBookId(Integer bookId);

	List<BookSample> findByBookIdAndStatusAndBookStatus(Integer bookId, String bookSampleStatus, String bookStatus);

	boolean existsByIdAndStatusAndBookStatus(Integer id, String bookSampleStatus, String bookStatus);

	boolean existsByStatusAndBookIdAndBookStatus(String bookSampleStatus, Integer bookId, String bookStatus);

	int countByStatusAndBookIdAndBookStatus(String bookSampleStatus, Integer bookId, String bookStatus);

}
