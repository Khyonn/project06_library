package fr.nmocs.library.webapp.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import fr.nmocs.library.webapp.ws.LoanDTO;

public final class LoanUtils {

	// ===== Règles métiers :

	// === UN SEUL PROLONGEMENT POSSIBLE
	private static final Integer MAX_PROLONGATION_NB = 1;

	// === NOMBRE DE SEMAINES DE PRET
	private static final long LOAN_BASETIME_WEEKS = 4;

	private static final long LOAN_BASETIME_MS = LOAN_BASETIME_WEEKS * 7 * 24 * 3600 * 1000;

	// === TEMPS D'UNE PROLONGATION D'UN PRET
	private static final long PROLONGATION_WEEKS = 4;

	private static final long PROLONGATION_MS = PROLONGATION_WEEKS * 7 * 24 * 3600 * 1000;

	/**
	 * For a given loan, returns end time
	 * 
	 * @param loan
	 * @return
	 */
	private static long getEndTime(LoanDTO loan) {
		return loan.getStartDate().toGregorianCalendar().getTime().getTime() + LOAN_BASETIME_MS
				+ (PROLONGATION_MS * loan.getProlongationNumber());
	}

	/**
	 * For a given loan, returns end time
	 * 
	 * @param loan
	 * @return
	 */
	private static long getMaxTime(LoanDTO loan) {
		return loan.getStartDate().toGregorianCalendar().getTime().getTime() + LOAN_BASETIME_MS
				+ (PROLONGATION_MS * MAX_PROLONGATION_NB);
	}

	public static List<LoanDTO> filterLateLoans(List<LoanDTO> loans) {
		Date today = new Date();
		return loans.stream().filter(loan -> getEndTime(loan) < today.getTime()).collect(Collectors.toList());
	}

	public static List<LoanDTO> filterNotLateLoans(List<LoanDTO> loans) {
		Date today = new Date();
		return loans.stream().filter(loan -> getEndTime(loan) >= today.getTime()).collect(Collectors.toList());
	}

	public static Boolean isLoanExtendable(LoanDTO loan) {
		Date today = new Date();
		return loan != null && loan.getReturnDate() == null && getMaxTime(loan) > today.getTime()
				&& loan.getProlongationNumber() < MAX_PROLONGATION_NB;
	}

}
