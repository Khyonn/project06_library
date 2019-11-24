package fr.nmocs.library.webapp.actions;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.ws.BookDTO;
import fr.nmocs.library.webapp.ws.BookService;
import fr.nmocs.library.webapp.ws.LibraryWebserviceException_Exception;

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
	private List<BookDTO> bookList;

	private BookDTO book;

	/**
	 * Returns if the book is reservable => based on
	 * reservationQueueInfos.isReservable and based on borrowers list and reservers
	 * list
	 * 
	 * @return
	 */
	public boolean getIsBookReservable() {
		return book != null && book.getReservationQueueInfos() != null
				&& book.getReservationQueueInfos().isIsReservable() && !getDoesUserBorrowingBook()
				&& !getDoesUserReservingBook();
	}

	/**
	 * Returns if current user is currently borrowing the book based on list of
	 * borrowers (reservationQueueInfos)
	 * 
	 * @return
	 */
	public boolean getDoesUserBorrowingBook() {
		return getIsUserConnected() && book != null && book.getReservationQueueInfos() != null
				&& book.getReservationQueueInfos().getBorrowers() != null
				&& !book.getReservationQueueInfos().getBorrowers().isEmpty()
				&& book.getReservationQueueInfos().getBorrowers().stream()
						.anyMatch(b -> b != null && b.getId() != null && b.getId().equals(getUserId()));
	}

	/**
	 * Returns if current user is currently reserving the book based on list of
	 * reservers (reservationQueueInfos)
	 * 
	 * @return
	 */
	public boolean getDoesUserReservingBook() {
		return getIsUserConnected() && book != null && book.getReservationQueueInfos() != null
				&& book.getReservationQueueInfos().getReservers() != null
				&& !book.getReservationQueueInfos().getReservers().isEmpty()
				&& book.getReservationQueueInfos().getReservers().stream()
						.anyMatch(r -> r != null && r.getId() != null && r.getId().equals(getUserId()));
	}

	public List<BookDTO> getBookList() {
		return bookList;
	}

	public Boolean isBookListNotEmpty() {
		return CollectionUtils.isNotEmpty(bookList);
	}

	public BookDTO getBook() {
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

		try {
			book = bookService.findBookById(bookId);
		} catch (LibraryWebserviceException_Exception e) {
			return ERROR;
		}
		if (book == null || !StringUtils.equals(book.getStatus(), AVAILABLE_STATUS)) {
			return ERROR;
		}
		return SUCCESS;
	}

}
