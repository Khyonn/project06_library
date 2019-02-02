package fr.nmocs.library.business;

import java.util.List;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

public interface BookManagement {

	// ===== Book

	Book createBook(Book book) throws LibraryException;

	Book updateBook(Book book) throws LibraryException;

	Book findBookById(Integer id) throws LibraryTechnicalException;

	List<Book> findBookByAuthor(String author) throws LibraryTechnicalException;

	List<Book> findBookByTitle(String title) throws LibraryTechnicalException;

	// ===== BookSample

	BookSample createBookSample(BookSample bookSample) throws LibraryException;

	BookSample updateBookSample(BookSample bookSample) throws LibraryException;

	BookSample findBookSampleById(Integer id) throws LibraryTechnicalException;

	List<BookSample> findBookSampleByBookId(Integer bookId) throws LibraryTechnicalException;

	List<BookSample> findBookSampleByBookIdNotBorrowed(Integer bookId);
}
