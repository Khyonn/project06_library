package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.LoanManagement;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.webservice.LoanService;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanManagement loanMgmt;

	@Override
	public Loan createLoan(Loan loan) throws LibraryWebserviceException {
		try {
			return loanMgmt.createLoan(loan);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}

	}

	@Override
	public Loan updateLoan(Loan loan) throws LibraryWebserviceException {
		try {
			return loanMgmt.updateLoan(loan);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public Loan findLoanById(Integer id) {
		try {
			return loanMgmt.findLoanById(id);
		} catch (LibraryException le) {
			return null;
		}
	}

	@Override
	public List<Loan> findByUserId(Integer userId) {
		try {
			return loanMgmt.findByUserId(userId);
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Loan> findNotReturned() {
		try {
			return loanMgmt.findNotReturned();
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	// ===== GESTION DES EXCEPTION

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
		default:
			reason += "unknown reason";
			break;
		}
		return reason;
	}

}
