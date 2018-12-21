package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.webservice.error.WebserviceException;

@WebService(name = "BookService", serviceName = "BookService")
public interface BookService {

	// ===== Book

	Book createBook(@WebParam(name = "book") Book book) throws WebserviceException;

	Book updateBook(@WebParam(name = "book") Book book) throws WebserviceException;

	Book findBookById(@WebParam(name = "id") Integer id);

	List<Book> findBookByAuthor(@WebParam(name = "author") String author);

	List<Book> findBookByTitle(@WebParam(name = "title") String title);

	// ===== BookSample

	BookSample createBookSample(@WebParam(name = "bookSample") BookSample bookSample) throws WebserviceException;

	BookSample updateBookSample(@WebParam(name = "bookSample") BookSample bookSample) throws WebserviceException;

	BookSample findBookSampleById(@WebParam(name = "id") Integer id);

	List<BookSample> findBookSampleByBookId(@WebParam(name = "bookId") Integer bookId);

}
