package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.LoanManagement;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.webservice.LoanService;
import fr.nmocs.library.webservice.dto.LoanDTO;
import fr.nmocs.library.webservice.dto.mapper.LoanMapper;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class LoanServiceImpl implements LoanService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private LoanManagement loanMgmt;

	@Autowired
	private AuthManagement authMgmt;

	@Override
	public LoanDTO createLoan(LoanDTO loan, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return LoanMapper.INSTANCE.toDTO(loanMgmt.createLoan(LoanMapper.INSTANCE.fromDTO(loan)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}

	}

	@Override
	public LoanDTO updateLoan(LoanDTO loan, String token) throws LibraryWebserviceException {
		checkAdmin(token);

		try {
			return LoanMapper.INSTANCE.toDTO(loanMgmt.updateLoan(LoanMapper.INSTANCE.fromDTO(loan)));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public LoanDTO findLoanById(Integer id, String token) throws LibraryWebserviceException {
		User userFromToken = authMgmt.getUser(token);
		try {
			Loan loan = loanMgmt.findLoanById(id);

			if (loan == null || loan.getBorrower() == null || userFromToken == null
					|| (!userFromToken.getId().equals(loan.getBorrower().getId()) && !authMgmt.isAdmin(token))) {
				throw new LibraryWebserviceException(NOT_ALLOWED);
			}
			return LoanMapper.INSTANCE.toDTO(loan);
		} catch (LibraryException le) {
			return null;
		}
	}

	@Override
	public LoanDTO extendLoan(Integer id, String token) throws LibraryWebserviceException {
		User userFromToken = authMgmt.getUser(token);
		try {
			Loan loan = loanMgmt.findLoanById(id);

			if (loan == null || loan.getBorrower() == null || userFromToken == null
					|| (!userFromToken.getId().equals(loan.getBorrower().getId()) && !authMgmt.isAdmin(token))) {
				throw new LibraryWebserviceException(NOT_ALLOWED);
			}
			loan.setProlongationNumber(loan.getProlongationNumber() + 1);
			return LoanMapper.INSTANCE.toDTO(loanMgmt.updateLoan(loan));
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public List<LoanDTO> findByUserId(Integer userId, String token) throws LibraryWebserviceException {
		if (userId == null || (!authMgmt.getUser(token).getId().equals(userId) && !authMgmt.isAdmin(token))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
		try {
			return loanMgmt.findByUserId(userId).stream().map(LoanMapper.INSTANCE::toDTO).collect(Collectors.toList());
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<LoanDTO> findNotReturned(String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return loanMgmt.findNotReturned().stream().map(LoanMapper.INSTANCE::toDTO).collect(Collectors.toList());
		} catch (LibraryException le) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<LoanDTO> findAlmostPeremptedLoans(String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return loanMgmt.findAlmostPeremptedLoans().stream().map(LoanMapper.INSTANCE::toDTO)
					.collect(Collectors.toList());
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
		case LOAN_BOOK_IS_CURRENTLY_RESERVED:
			reason += "book is currently reserverd";
			break;
		case LOAN_SOMEONE_RESERVED_BEFORE:
			reason += "someone reserved the book before given user";
			break;
		case LOAN_CANNOT_EDIT_OUTDATED:
			reason += "the loan is outdated, cannot add any prolongations";
			break;
		default:
			reason += "unknown reason";
			break;
		}
		return reason;
	}
}
