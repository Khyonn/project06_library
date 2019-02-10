package fr.nmocs.library.webapp.actions;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.webservice.Book;
import fr.nmocs.library.webapp.webservice.BookSample;
import fr.nmocs.library.webapp.webservice.BookService;

@SuppressWarnings("serial")
public class BookAction extends LibraryAbstractAction {

	@Autowired
	private BookService bookService;

	private static final String AVAILABLE_STATUS = "A";

	// ===== Inputs
	public String authorFilter;

	public String titleFilter;

	public Integer bookId;

	// ===== Outputs
	private List<Book> bookList;

	private Book book;

	public List<Book> getBookList() {
		return bookList;
	}

	public Boolean isBookListNotEmpty() {
		return CollectionUtils.isNotEmpty(bookList);
	}

	public Book getBook() {
		return book;
	}

	public Long sampleNumber;

	// ========== ACTIONS

	public String doSearch() {
		titleFilter = StringUtils.trim(titleFilter);
		authorFilter = StringUtils.trim(authorFilter);

		if (!StringUtils.isAllBlank(titleFilter, authorFilter)) {
			if (!StringUtils.isBlank(titleFilter)) {
				bookList = bookService.findBookByTitle(titleFilter);
				if (CollectionUtils.isNotEmpty(bookList) && !StringUtils.isBlank(authorFilter)) {
					bookList = bookList.stream().filter(book -> book.getAuthor().contains(authorFilter))
							.collect(Collectors.toList());
				}
			} else {
				bookList = bookService.findBookByAuthor(authorFilter);
			}
		} else {
			return INPUT;
		}
		bookList = bookList.stream().filter(book -> StringUtils.equals(book.getStatus(), AVAILABLE_STATUS))
				.collect(Collectors.toList());

		return SUCCESS;
	}

	public String doDetail() {
		if (bookId == null) {
			return ERROR;
		}

		List<BookSample> bookSampleList = bookService.findNotBorrowedBookSampleByBookId(bookId);
		if (CollectionUtils.isNotEmpty(bookSampleList)) {
			sampleNumber = bookSampleList.stream()
					.filter(bookSample -> StringUtils.equals(bookSample.getStatus(), AVAILABLE_STATUS)).count();
			book = bookSampleList.get(0).getBook();
		} else {
			sampleNumber = 0L;
		}
		return SUCCESS;
	}

}
