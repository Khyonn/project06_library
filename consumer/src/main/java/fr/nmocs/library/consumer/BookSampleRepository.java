package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.BookSample;

public interface BookSampleRepository extends JpaRepository<BookSample, Integer> {

	List<BookSample> findByBookId(Integer bookId);

	boolean existsByIdAndStatusAndBookStatus(Integer id, String bookSampleStatus, String bookStatus);

}
