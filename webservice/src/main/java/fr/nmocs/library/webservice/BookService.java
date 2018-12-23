package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "BookService", serviceName = "BookService")
public interface BookService {

	// ===== Book

	Book createBook(@WebParam(name = "book") Book book) throws LibraryWebserviceException;

	Book updateBook(@WebParam(name = "book") Book book) throws LibraryWebserviceException;

	Book findBookById(@WebParam(name = "id") Integer id);

	List<Book> findBookByAuthor(@WebParam(name = "author") String author);

	List<Book> findBookByTitle(@WebParam(name = "title") String title);

	// ===== BookSample

	BookSample createBookSample(@WebParam(name = "bookSample") BookSample bookSample) throws LibraryWebserviceException;

	BookSample updateBookSample(@WebParam(name = "bookSample") BookSample bookSample) throws LibraryWebserviceException;

	BookSample findBookSampleById(@WebParam(name = "id") Integer id);

	List<BookSample> findBookSampleByBookId(@WebParam(name = "bookId") Integer bookId);

}
