package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.LoanManagement;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.webservice.LoanService;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class LoanServiceImpl implements LoanService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private LoanManagement loanMgmt;

	@Autowired
	private AuthManagement authMgmt;

	@Override
	public Loan createLoan(Loan loan, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return loanMgmt.createLoan(loan);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}

	}

	@Override
	public Loan updateLoan(Loan loan, String token) throws LibraryWebserviceException {
		checkAdmin(token);

		try {
			return loanMgmt.updateLoan(loan);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public Loan findLoanById(Integer id, String token) throws LibraryWebserviceException {
		User userFromToken = authMgmt.getUser(token);
		try {
			Loan loan = loanMgmt.findLoanById(id);

			if (loan == null || loan.getBorrower() == null || userFromToken == null
					|| (!userFromToken.getId().equals(loan.getBorrower().getId()) && !authMgmt.isAdmin(token))) {
				throw new LibraryWebserviceException(NOT_ALLOWED);
			}
			return loan;
		} catch (LibraryException le) {
			return null;
		}
	}

	@Override
	public Loan extendLoan(Integer id, String token) throws LibraryWebserviceException {
		User userFromToken = authMgmt.getUser(token);
		try {
			Loan loan = loanMgmt.findLoanById(id);

			if (loan == null || loan.getBorrower() == null || userFromToken == null
					|| (!userFromToken.getId().equals(loan.getBorrower().getId()) && !authMgmt.isAdmin(token))) {
				throw new LibraryWebserviceException(NOT_ALLOWED);
			}
			loan.setProlongationNumber(loan.getProlongationNumber() + 1);
			return loanMgmt.updateLoan(loan);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public List<Loan> findByUserId(Integer userId, String token) throws LibraryWebserviceException {
		if (userId == null || (!authMgmt.getUser(token).getId().equals(userId) && !authMgmt.isAdmin(token))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
		try {
			return loanMgmt.findByUserId(userId);
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Loan> findNotReturned(String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return loanMgmt.findNotReturned();
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	// ===== GESTION DES EXCEPTION

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
		String reason = le.getErrorCode().getId() + " => Error editing loan : ";
		switch (le.getErrorCode()) {
		case LOAN_UNSETTED:
			reason += "no loan given";
			break;
		case LOAN_DOESNT_EXIST:
			reason += "loan doesn't exist";
			break;
		case LOAN_NOT_FOUND:
			reason += "loan not found";
			break;
		case LOAN_AMBIGOUS_RETURNDATE:
			reason += "return date is ambigous";
			break;
		case LOAN_UNEXISTING_REFERENCED_BOOKSAMPLE:
			reason += "referenced book sample doesn't exist";
			break;
		case LOAN_UNEXISTING_REFERENCED_BORROWER:
			reason += "referenced borrower doesn't exist";
			break;
		case LOAN_UNSETTED_BOOKSAMPLEID:
			reason += "there is no reference to a book sample";
			break;
		case LOAN_UNSETTED_BORROWERID:
			reason += "there is no reference to a borrower";
			break;
		case LOAN_ALREADY_BORROWED_BOOKSAMPLE:
			reason += "referenced book sample is already borrowed";
			break;
		case LOAN_BOOKSAMPLE_UNAVAILABLE:
			reason += "referenced book sample is not available for borrow";
			break;
		case LOAN_USER_UNAVAILABLE:
			reason += "referenced user is not allowed to borrow any book sample";
			break;
		default:
			reason += "unknown reason";
			break;
		}
		return reason;
	}

}
