package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.BookManagement;
import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;
import fr.nmocs.library.webservice.BookService;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class BookServiceImpl implements BookService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private BookManagement bookMgmt;

	@Autowired
	private AuthManagement authMgmt;

	// ========= BOOKS

	@Override
	public Book createBook(Book book, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return bookMgmt.createBook(book);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public Book updateBook(Book book, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return bookMgmt.updateBook(book);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public Book findBookById(Integer id) {
		try {
			return bookMgmt.findBookById(id);
		} catch (LibraryException le) {
			return null;
		}
	}

	@Override
	public List<Book> findBookByAuthor(String author) {
		try {
			return bookMgmt.findBookByAuthor(author);
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Book> findBookByTitle(String title) {
		try {
			return bookMgmt.findBookByTitle(title);
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	// ========== BOOK_SAMPLES

	@Override
	public BookSample createBookSample(BookSample bookSample, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return bookMgmt.createBookSample(bookSample);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public BookSample updateBookSample(BookSample bookSample, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return bookMgmt.updateBookSample(bookSample);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public BookSample findBookSampleById(Integer id) {
		try {
			return bookMgmt.findBookSampleById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BookSample> findBookSampleByBookId(Integer bookId) {
		try {
			return bookMgmt.findBookSampleByBookId(bookId);
		} catch (LibraryTechnicalException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<BookSample> findNotBorrowedBookSampleByBookId(Integer bookId) {
		return bookMgmt.findBookSampleByBookIdNotBorrowed(bookId);
	}

	// ==== UTILS

	/**
	 * Check if user is admin based on request token
	 * 
	 * @param token
	 * @throws LibraryWebserviceException
	 */
	private void checkAdmin(String token) throws LibraryWebserviceException {
		if (!authMgmt.isAdmin(token)) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
	}

	private String getExceptionReason(LibraryException le) {
		String reason = le.getErrorCode().getId() + " => Error editing book : ";
		switch (le.getErrorCode()) {
		case BOOK_DOESNT_EXIST:
			reason += "book doesn't exist";
			break;
		case BOOK_NOT_FOUND:
			reason += "book not found";
			break;
		case BOOK_UNSETTED:
			reason += "no book given";
			break;
		case BOOK_UNSETTED_AUTHOR:
			reason += "author is not set";
			break;
		case BOOK_UNSETTED_TITLE:
			reason += "title is not set";
			break;
		case BOOK_OVERSIZED_AUTHOR:
			reason += "author is too long";
			break;
		case BOOK_OVERSIZED_TITLE:
			reason += "title is too long";
			break;
		case BOOKSAMPLE_DOESNT_EXIST:
			reason += "book sample doesn't exist";
			break;
		case BOOKSAMPLE_NOT_FOUND:
			reason += "book sample not found";
			break;
		case BOOKSAMPLE_UNEXISTING_REFERENCED_BOOK:
			reason += "referenced book doesn't exist";
			break;
		case BOOKSAMPLE_UNSETTED:
			reason += "no book sample given";
			break;
		case BOOKSAMPLE_UNSETTED_BOOKID:
			reason += "book sample doesn't reference a book";
			break;
		default:
			reason += "unknown reason";
			break;
		}
		return reason;
	}

}
