package fr.nmocs.library.business.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import fr.nmocs.library.business.BusinessHelper;
import fr.nmocs.library.model.Loan;

public class BusinessHelperImpl implements BusinessHelper {

	// ===== Règles métiers :

	// === UN SEUL PROLONGEMENT POSSIBLE
	@Value("${business.loans.prolongation.maxNb}")
	private Integer MAX_PROLONGATION_NB;

	// === NOMBRE DE SEMAINES DE PRET
	@Value("${business.loans.time.weeksNb}")
	private long LOAN_BASETIME_WEEKS;

	private final long LOAN_BASETIME_MS = LOAN_BASETIME_WEEKS * 7 * 24 * 3600 * 1000;

	// === TEMPS D'UNE PROLONGATION D'UN PRET
	@Value("${business.loans.prolongation.time.weeksNb}")
	private long PROLONGATION_WEEKS;

	private final long PROLONGATION_MS = PROLONGATION_WEEKS * 7 * 24 * 3600 * 1000;

	/**
	 * Returns a Date coresponding to the loan start date and the given prolongation
	 * number
	 * 
	 * @param startDate
	 * @param prolongationNumber
	 * @return
	 */
	private Date getLoanEndDate(Date startDate, Integer prolongationNumber) {
		// Retour le plus tard : Jour de location + temps de location + (temps de
		// prolongement * nombre de prolongement)
		return new Date(startDate.getTime() + LOAN_BASETIME_MS + (PROLONGATION_MS * prolongationNumber));
	}

	/**
	 * Returns the actual last date of return
	 * 
	 * @param loan
	 * @return
	 */
	@Override
	public Date getLoanActualEndDate(Loan loan) {
		return getLoanEndDate(loan.getStartDate(), loan.getProlongationNumber());
	}

	/**
	 * Returns the latest date of return
	 * 
	 * @param loan
	 * @return
	 */
	@Override
	public Date getLoanMaxEndDate(Loan loan) {
		return getLoanEndDate(loan.getStartDate(), MAX_PROLONGATION_NB);
	}
}
