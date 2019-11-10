package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.BookManagement;
import fr.nmocs.library.business.ReservationManagement;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;
import fr.nmocs.library.webservice.BookService;
import fr.nmocs.library.webservice.dto.BookDTO;
import fr.nmocs.library.webservice.dto.BookSampleDTO;
import fr.nmocs.library.webservice.dto.mapper.BookMapper;
import fr.nmocs.library.webservice.dto.mapper.BookSampleMapper;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class BookServiceImpl implements BookService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private BookManagement bookMgmt;

	@Autowired
	private ReservationManagement reservationMgmt;

	@Autowired
	private AuthManagement authMgmt;

	// ========= BOOKS

	@Override
	public BookDTO createBook(BookDTO book, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return BookMapper.INSTANCE.toDTO(bookMgmt.createBook(BookMapper.INSTANCE.fromDTO(book)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public BookDTO updateBook(BookDTO book, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return BookMapper.INSTANCE.toDTO(bookMgmt.updateBook(BookMapper.INSTANCE.fromDTO(book)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public BookDTO findBookById(Integer id) {
		try {
			BookDTO dto = BookMapper.INSTANCE.toDTO(bookMgmt.findBookById(id));

			dto.setReservationQueueInfos(BookMapper.INSTANCE.toDTO(reservationMgmt.getBookReservationInfos(id)));
			return dto;
		} catch (LibraryException le) {
			return null;
		}
	}

	@Override
	public List<BookDTO> findBookByAuthor(String author) {
		try {
			return bookMgmt.findBookByAuthor(author).stream().map(BookMapper.INSTANCE::toDTO)
					.collect(Collectors.toList());
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<BookDTO> findBookByTitle(String title) {
		try {
			return bookMgmt.findBookByTitle(title).stream().map(BookMapper.INSTANCE::toDTO)
					.collect(Collectors.toList());
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	// ========== BOOK_SAMPLES

	@Override
	public BookSampleDTO createBookSample(BookSampleDTO bookSample, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return BookSampleMapper.INSTANCE
					.toDTO(bookMgmt.createBookSample(BookSampleMapper.INSTANCE.fromDTO(bookSample)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public BookSampleDTO updateBookSample(BookSampleDTO bookSample, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return BookSampleMapper.INSTANCE
					.toDTO(bookMgmt.updateBookSample(BookSampleMapper.INSTANCE.fromDTO(bookSample)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public BookSampleDTO findBookSampleById(Integer id) {
		try {
			return BookSampleMapper.INSTANCE.toDTO(bookMgmt.findBookSampleById(id));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BookSampleDTO> findBookSampleByBookId(Integer bookId) {
		try {
			return bookMgmt.findBookSampleByBookId(bookId).stream().map(BookSampleMapper.INSTANCE::toDTO)
					.collect(Collectors.toList());
		} catch (LibraryTechnicalException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<BookSampleDTO> findNotBorrowedBookSampleByBookId(Integer bookId) {
		return bookMgmt.findBookSampleByBookIdNotBorrowed(bookId).stream().map(BookSampleMapper.INSTANCE::toDTO)
				.collect(Collectors.toList());
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
