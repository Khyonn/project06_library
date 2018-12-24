package fr.nmocs.library.business.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.BookManagement;
import fr.nmocs.library.consumer.BookRepository;
import fr.nmocs.library.consumer.BookSampleRepository;
import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.BookSample;
import fr.nmocs.library.model.constants.BookSampleStatus;
import fr.nmocs.library.model.constants.BookStatus;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class BookManagementImpl implements BookManagement {

	private static final Integer DATABASE_STRING_SIZE = 255;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BookSampleRepository bookSampleRepo;

	// =============== BOOK

	// ========== CREATES AND UPDATES

	@Transactional
	private Book checkFormatAndSaveBook(Book book) throws LibraryException {
		checkBookFields(book);
		formatBookFields(book);
		return bookRepo.save(book);
	}

	@Override
	public Book createBook(Book book) throws LibraryException {
		if (book == null) {
			throw new LibraryBusinessException(ErrorCode.BOOK_UNSETTED);
		}
		book.setId(null);
		return checkFormatAndSaveBook(book);
	}

	@Override
	public Book updateBook(Book book) throws LibraryException {
		if (book == null) {
			throw new LibraryBusinessException(ErrorCode.BOOK_UNSETTED);
		}
		if (book.getId() == null || book.getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.BOOK_DOESNT_EXIST);
		}
		Book databaseBook = findBookById(book.getId());
		mergeBook(databaseBook, book);
		return checkFormatAndSaveBook(databaseBook);
	}

	// ========== READERS

	@Override
	public Book findBookById(Integer id) throws LibraryTechnicalException {
		try {
			return bookRepo.findById(id).get();
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.BOOK_NOT_FOUND);
		}
	}

	@Override
	public List<Book> findBookByAuthor(String author) throws LibraryTechnicalException {
		try {
			return bookRepo.findByAuthorIgnoreCaseContaining(author);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.BOOK_NOT_FOUND);
		}
	}

	@Override
	public List<Book> findBookByTitle(String title) throws LibraryTechnicalException {
		try {
			return bookRepo.findByTitleIgnoreCaseContaining(title);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.BOOK_NOT_FOUND);
		}
	}

	// ============== BOOKSAMPLE

	// ========== CREATE AND UPDATE

	@Transactional
	private BookSample checkFormatAndSaveBookSample(BookSample bookSample) throws LibraryException {
		checkBookSampleFields(bookSample);
		formatBookSampleFields(bookSample);
		return bookSampleRepo.save(bookSample);
	}

	@Override
	public BookSample createBookSample(BookSample bookSample) throws LibraryException {
		if (bookSample == null) {
			throw new LibraryBusinessException(ErrorCode.BOOKSAMPLE_UNSETTED);
		}
		bookSample.setId(null);
		return checkFormatAndSaveBookSample(bookSample);
	}

	@Override
	public BookSample updateBookSample(BookSample bookSample) throws LibraryException {
		if (bookSample == null) {
			throw new LibraryBusinessException(ErrorCode.BOOKSAMPLE_UNSETTED);
		}
		if (bookSample.getId() == null || bookSample.getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.BOOKSAMPLE_DOESNT_EXIST);
		}
		BookSample databaseBookSample = findBookSampleById(bookSample.getId());
		mergeBookSample(databaseBookSample, bookSample);
		return checkFormatAndSaveBookSample(databaseBookSample);
	}

	// ========== READERS

	@Override
	public BookSample findBookSampleById(Integer id) throws LibraryTechnicalException {
		try {
			return bookSampleRepo.findById(id).get();
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.BOOKSAMPLE_NOT_FOUND);
		}
	}

	@Override
	public List<BookSample> findBookSampleByBookId(Integer bookId) throws LibraryTechnicalException {
		try {
			return bookSampleRepo.findByBookId(bookId);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.BOOKSAMPLE_NOT_FOUND);
		}
	}

	// ========== UTILS

	/**
	 * Check if book fields are setted
	 * 
	 * @param book
	 * @throws LibraryBusinessException
	 */
	private void checkBookFields(Book book) throws LibraryBusinessException {
		if (StringUtils.isBlank(book.getAuthor())) {
			throw new LibraryBusinessException(ErrorCode.BOOK_UNSETTED_AUTHOR);
		}
		if (book.getAuthor().length() > DATABASE_STRING_SIZE) {
			throw new LibraryBusinessException(ErrorCode.BOOK_OVERSIZED_AUTHOR);
		}
		if (StringUtils.isBlank(book.getTitle())) {
			throw new LibraryBusinessException(ErrorCode.BOOK_UNSETTED_TITLE);
		}
		if (book.getAuthor().length() > DATABASE_STRING_SIZE) {
			throw new LibraryBusinessException(ErrorCode.BOOK_OVERSIZED_TITLE);
		}
	}

	/**
	 * Check if book fields are setted
	 * 
	 * @param bookSample
	 * @throws LibraryBusinessException
	 */
	private void checkBookSampleFields(BookSample bookSample) throws LibraryBusinessException {
		if (bookSample.getBook() == null || bookSample.getBook().getId() == null
				|| bookSample.getBook().getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.BOOKSAMPLE_UNSETTED_BOOKID);
		}

		if (!bookRepo.existsById(bookSample.getBook().getId())) {
			throw new LibraryBusinessException(ErrorCode.BOOKSAMPLE_UNEXISTING_REFERENCED_BOOK);
		}
	}

	/**
	 * Trim book fields
	 * 
	 * @param book
	 */
	private void formatBookFields(Book book) {
		book.setAuthor(book.getAuthor().trim());
		book.setTitle(book.getTitle().trim());
		if (StringUtils.isBlank(book.getSummary())) {
			book.setSummary(book.getSummary().trim());
		}
		if (book.getStatus() != null && (!book.getStatus().equals(BookStatus.AVAILABLE.getValue())
				&& !book.getStatus().equals(BookStatus.UNAVAILABLE.getValue()))) {
			book.setStatus(BookStatus.UNAVAILABLE.getValue());
		}
	}

	/**
	 * 
	 * @param bookSample
	 */
	private void formatBookSampleFields(BookSample bookSample) {
		if (bookSample.getStatus() != null && (!bookSample.getStatus().equals(BookSampleStatus.AVAILABLE.getValue())
				&& !bookSample.getStatus().equals(BookSampleStatus.UNAVAILABLE.getValue()))) {
			bookSample.setStatus(BookSampleStatus.UNAVAILABLE.getValue());
		}
		bookSample.setBook(bookRepo.findById(bookSample.getBook().getId()).orElse(null));
	}

	/**
	 * Merge new book fields into database book
	 * 
	 * @param databaseBook
	 * @param book
	 */
	private void mergeBook(Book databaseBook, Book book) {
		if (!StringUtils.isBlank(book.getAuthor())) {
			databaseBook.setAuthor(book.getAuthor());
		}
		if (!StringUtils.isBlank(book.getTitle())) {
			databaseBook.setTitle(book.getTitle());
		}
		if (!StringUtils.isBlank(book.getSummary())) {
			databaseBook.setSummary(book.getSummary());
		}
		if (book.getStatus() != null && (book.getStatus().equals(BookStatus.AVAILABLE.getValue())
				|| book.getStatus().equals(BookStatus.UNAVAILABLE.getValue()))) {
			databaseBook.setStatus(book.getStatus());
		}

	}

	/**
	 * 
	 * @param databaseBookSample
	 * @param bookSample
	 */
	private void mergeBookSample(BookSample databaseBookSample, BookSample bookSample) {
		if (bookSample.getStatus() != null && (bookSample.getStatus().equals(BookSampleStatus.AVAILABLE.getValue())
				|| bookSample.getStatus().equals(BookSampleStatus.DAMAGED.getValue())
				|| bookSample.getStatus().equals(BookSampleStatus.UNAVAILABLE.getValue()))) {
			databaseBookSample.setStatus(bookSample.getStatus());
		}
	}

}
