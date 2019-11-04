package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.webservice.dto.BookDTO;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "BookService", serviceName = "BookService")
public interface BookService {

	// ===== Book

	Book createBook(@WebParam(name = "book") Book book, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	Book updateBook(@WebParam(name = "book") Book book, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	BookDTO findBookById(@WebParam(name = "id") Integer id) throws LibraryWebserviceException;

	List<Book> findBookByAuthor(@WebParam(name = "author") String author);

	List<Book> findBookByTitle(@WebParam(name = "title") String title);

	// ===== BookSample

	BookSample createBookSample(@WebParam(name = "bookSample") BookSample bookSample,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	BookSample updateBookSample(@WebParam(name = "bookSample") BookSample bookSample,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	BookSample findBookSampleById(@WebParam(name = "id") Integer id);

	List<BookSample> findBookSampleByBookId(@WebParam(name = "bookId") Integer bookId);

	List<BookSample> findNotBorrowedBookSampleByBookId(@WebParam(name = "bookId") Integer bookId);

}
