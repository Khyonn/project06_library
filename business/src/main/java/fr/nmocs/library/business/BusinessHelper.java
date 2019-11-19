package fr.nmocs.library.business;

import java.util.Date;

import fr.nmocs.library.business.model.ReservationQueue;
import fr.nmocs.library.model.Book;
import fr.nmocs.library.model.Loan;

public interface BusinessHelper {

	Date getLoanMaxEndDate(Loan loan);

	Date getLoanActualEndDate(Loan loan);

	ReservationQueue getBookReservationInfos(Book book);

	Date getReservationMaxMailedDate();

}
