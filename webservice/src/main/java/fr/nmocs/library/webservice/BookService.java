package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.webservice.dto.BookDTO;
import fr.nmocs.library.webservice.dto.BookSampleDTO;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "BookService", serviceName = "BookService")
public interface BookService {

	// ===== Book

	BookDTO createBook(@WebParam(name = "book") BookDTO book, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	BookDTO updateBook(@WebParam(name = "book") BookDTO book, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	BookDTO findBookById(@WebParam(name = "id") Integer id) throws LibraryWebserviceException;

	List<BookDTO> findBookByAuthor(@WebParam(name = "author") String author);

	List<BookDTO> findBookByTitle(@WebParam(name = "title") String title);

	// ===== BookSample

	BookSampleDTO createBookSample(@WebParam(name = "bookSample") BookSampleDTO bookSample,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	BookSampleDTO updateBookSample(@WebParam(name = "bookSample") BookSampleDTO bookSample,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	BookSampleDTO findBookSampleById(@WebParam(name = "id") Integer id);

	List<BookSampleDTO> findBookSampleByBookId(@WebParam(name = "bookId") Integer bookId);

	List<BookSampleDTO> findNotBorrowedBookSampleByBookId(@WebParam(name = "bookId") Integer bookId);

}
