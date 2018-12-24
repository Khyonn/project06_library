package fr.nmocs.library.business.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.nmocs.library.business.LoanManagement;
import fr.nmocs.library.consumer.BookSampleRepository;
import fr.nmocs.library.consumer.LoanRepository;
import fr.nmocs.library.consumer.UserRepository;
import fr.nmocs.library.model.Loan;
import fr.nmocs.library.model.constants.BookSampleStatus;
import fr.nmocs.library.model.constants.BookStatus;
import fr.nmocs.library.model.constants.UserStatus;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class LoanManagementImpl implements LoanManagement {

	// ===== Règles métiers :

	private static final Integer MIN_PROLONGATION_NB = 0;

	// === UN SEUL PROLONGEMENT POSSIBLE
	private static final Integer MAX_PROLONGATION_NB = 1;

	// === NOMBRE DE SEMAINES DE PRET
	private static final long LOAN_BASETIME_WEEKS = 4;

	private static final long LOAN_BASETIME_MS = LOAN_BASETIME_WEEKS * 7 * 24 * 3600 * 1000;

	// === TEMPS D'UNE PROLONGATION D'UN PRET
	private static final long PROLONGATION_WEEKS = 4;

	private static final long PROLONGATION_MS = PROLONGATION_WEEKS * 7 * 24 * 3600 * 1000;

	// === DEPENDENCIES

	@Autowired
	private LoanRepository loanRepo;

	@Autowired
	private BookSampleRepository bookSampleRepo;

	@Autowired
	private UserRepository userRepo;

	// ========== CREATE AND UPDATE

	@Override
	@Transactional
	public Loan createLoan(Loan loan) throws LibraryException {
		if (loan == null) {
			throw new LibraryBusinessException(ErrorCode.LOAN_UNSETTED);
		}
		formatFieldsForCreate(loan);
		checkFields(loan);
		return loanRepo.save(loan);
	}

	@Override
	@Transactional
	public Loan updateLoan(Loan loan) throws LibraryException {
		if (loan == null || loan.getId() == null || loan.getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.LOAN_UNSETTED);
		}
		if (!loanRepo.existsById(loan.getId())) {
			throw new LibraryBusinessException(ErrorCode.LOAN_DOESNT_EXIST);
		}
		Loan databaseLoan = loanRepo.findById(loan.getId()).orElse(null);
		mergeLoan(databaseLoan, loan);
		checkFields(databaseLoan);
		return loanRepo.save(databaseLoan);
	}

	// ========== READERS

	@Override
	public Loan findLoanById(Integer id) throws LibraryTechnicalException {
		try {
			return loanRepo.findById(id).get();
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.LOAN_NOT_FOUND);
		}
	}

	@Override
	public List<Loan> findByUserId(Integer userId) throws LibraryTechnicalException {
		try {
			return loanRepo.findByBorrowerId(userId);
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.LOAN_NOT_FOUND);
		}
	}

	@Override
	public List<Loan> findNotReturned() throws LibraryTechnicalException {
		Date today = new Date();
		try {
			return loanRepo.findByReturnDateIsNull().stream().filter(loan -> getMaxTime(loan) < today.getTime())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.LOAN_NOT_FOUND);
		}
	}

	// ========== UTILS

	/**
	 * 
	 * @param loan
	 */
	private void formatFieldsForCreate(Loan loan) {
		loan.setId(null);
		if (loan.getBookSample() != null && loan.getBookSample().getId() != null
				&& !loan.getBookSample().getId().equals(0)) {
			loan.setBookSample(bookSampleRepo.findById(loan.getBookSample().getId()).orElse(null));
		}
		if (loan.getBorrower() != null && loan.getBorrower().getId() != null && !loan.getBorrower().getId().equals(0)) {
			loan.setBorrower(userRepo.findById(loan.getBorrower().getId()).orElse(null));
		}
		loan.setStartDate(new Date());
		loan.setReturnDate(null);
		loan.setProlongationNumber(MIN_PROLONGATION_NB);
	}

	/**
	 * Check if bookSample and borrower exist and if return date is OK. Also check
	 * that book and book sample are available, and check that user is not disabled
	 * 
	 * @param loan
	 * @throws LibraryBusinessException
	 */
	private void checkFields(Loan loan) throws LibraryBusinessException {
		// BOOKSAMPLE
		if (loan.getBookSample() == null || loan.getBookSample().getId() == null
				|| loan.getBookSample().getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.LOAN_UNSETTED_BOOKSAMPLEID);
		}
		if (!bookSampleRepo.existsById(loan.getBookSample().getId())) {
			throw new LibraryBusinessException(ErrorCode.LOAN_UNEXISTING_REFERENCED_BOOKSAMPLE);
		}
		// Check if book sample is not borrowed
		if (loan.getId() == null) {
			if (loanRepo.existsByBookSampleIdAndReturnDateIsNull(loan.getBookSample().getId())) {
				throw new LibraryBusinessException(ErrorCode.LOAN_ALREADY_BORROWED_BOOKSAMPLE);
			}
		} else {
			if (loanRepo.existsByIdNotAndBookSampleIdAndReturnDateIsNull(loan.getId(), loan.getBookSample().getId())) {
				throw new LibraryBusinessException(ErrorCode.LOAN_ALREADY_BORROWED_BOOKSAMPLE);
			}
		}
		// Check if book sample and its referenced book is available
		if (!bookSampleRepo.existsByIdAndStatusAndBookStatus(loan.getBookSample().getId(),
				BookSampleStatus.AVAILABLE.getValue(), BookStatus.AVAILABLE.getValue())) {
			throw new LibraryBusinessException(ErrorCode.LOAN_BOOKSAMPLE_UNAVAILABLE);
		}

		// BORROWER
		if (loan.getBorrower() == null || loan.getBorrower().getId() == null || loan.getBorrower().getId().equals(0)) {
			throw new LibraryBusinessException(ErrorCode.LOAN_UNSETTED_BORROWERID);
		}
		if (!userRepo.existsById(loan.getBorrower().getId())) {
			throw new LibraryBusinessException(ErrorCode.LOAN_UNEXISTING_REFERENCED_BORROWER);
		}
		// Check if borrower status is available
		if (!userRepo.existsByIdAndStatus(loan.getBorrower().getId(), UserStatus.ACTIVE.getValue())) {
			throw new LibraryBusinessException(ErrorCode.LOAN_USER_UNAVAILABLE);
		}

		// RETURN DATE
		if (loan.getReturnDate() != null && loan.getReturnDate().before(loan.getStartDate())) {
			throw new LibraryBusinessException(ErrorCode.LOAN_AMBIGOUS_RETURNDATE);
		}

	}

	/**
	 * Merge new data on database loan
	 * 
	 * @param databaseLoan
	 * @param loan
	 */
	private void mergeLoan(Loan databaseLoan, Loan loan) {
		if (loan.getReturnDate() != null) {
			databaseLoan.setReturnDate(loan.getReturnDate());
		}

		if (loan.getProlongationNumber() != null && loan.getProlongationNumber() <= MAX_PROLONGATION_NB
				&& loan.getProlongationNumber() >= MIN_PROLONGATION_NB) {
			databaseLoan.setProlongationNumber(loan.getProlongationNumber());
		}
	}

	/**
	 * For a given loan, returns max return time
	 * 
	 * @param loan
	 * @return
	 */
	private long getMaxTime(Loan loan) {
		return loan.getStartDate().getTime() + LOAN_BASETIME_MS + (PROLONGATION_MS * loan.getProlongationNumber());
	}

}
