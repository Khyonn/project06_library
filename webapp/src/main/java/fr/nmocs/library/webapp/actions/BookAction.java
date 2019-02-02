package fr.nmocs.library.webapp.actions;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.factories.WebserviceFactory;
import fr.nmocs.library.webapp.webservice.Book;
import fr.nmocs.library.webapp.webservice.BookSample;

@SuppressWarnings("serial")
public class BookAction extends LibraryAbstractAction {

	@Autowired
	private WebserviceFactory wsFactory;

	// ===== Inputs
	public String authorFilter;

	public String titleFilter;

	public Integer bookId;

	// ===== Outputs
	public List<Book> bookList;

	public List<BookSample> bookSampleList;

	// ========== ACTIONS

	public String doListBookSample() {
		bookSampleList = wsFactory.getBookService().findNotBorrowedBookSampleByBookId(bookId);
		return SUCCESS;
	}

	public String doListByAuthor() {
		bookList = wsFactory.getBookService().findBookByAuthor(authorFilter);
		if (!StringUtils.isBlank(titleFilter)) {
			bookList = bookList.stream().filter(book -> book.getTitle().contains(titleFilter))
					.collect(Collectors.toList());
		}
		return SUCCESS;
	}

	public String doListByTitle() {
		bookList = wsFactory.getBookService().findBookByTitle(titleFilter);
		if (!StringUtils.isBlank(authorFilter)) {
			bookList = bookList.stream().filter(book -> book.getAuthor().contains(authorFilter))
					.collect(Collectors.toList());
		}
		return SUCCESS;
	}

}
