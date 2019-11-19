package fr.nmocs.library.business;

import java.util.Date;
import java.util.List;

import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

public interface LoanManagement {

	Loan createLoan(Loan loan) throws LibraryException;

	Loan updateLoan(Loan loan) throws LibraryException;

	Loan findLoanById(Integer id) throws LibraryTechnicalException;

	List<Loan> findByUserId(Integer userId) throws LibraryTechnicalException;

	List<Loan> findNotReturned() throws LibraryTechnicalException;

	Date getSoonestReturnDate(List<Loan> loans);

	void sendEmailToBorrowers() throws LibraryTechnicalException;
}
