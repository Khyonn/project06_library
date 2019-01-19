package fr.nmocs.library.consumer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nmocs.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByAuthorIgnoreCaseContaining(String author);

	List<Book> findByTitleIgnoreCaseContaining(String title);

}
