package fr.nmocs.library.business.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.nmocs.library.business.BusinessHelper;
import fr.nmocs.library.model.Loan;

@Service
public class BusinessHelperImpl implements BusinessHelper {

	// ===== Règles métiers :

	// === UN SEUL PROLONGEMENT POSSIBLE
	private final Integer MAX_PROLONGATION_NB;

	// === NOMBRE DE MS DE PRET
	private final long LOAN_BASETIME_MS;

	// === TEMPS EN MS D'UNE PROLONGATION D'UN PRET
	private final long PROLONGATION_MS;

	public BusinessHelperImpl (@Value("${business.loans.prolongation.maxNb}") Integer maxProlongationNb, @Value(
		"${business.loans.time.weeksNb}"
	) long loanBasetimeWeeks, @Value("${business.loans.prolongation.time.weeksNb}") long prolongationWeeks) {
		this.MAX_PROLONGATION_NB = maxProlongationNb;
		this.LOAN_BASETIME_MS = loanBasetimeWeeks * 7 * 24 * 3600 * 1000;
		this.PROLONGATION_MS = prolongationWeeks * 7 * 24 * 3600 * 1000;
	}

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
