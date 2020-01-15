package fr.nmocs.library.webapp.actions;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.utils.LoanUtils;
import fr.nmocs.library.webapp.ws.ExtendLoan;
import fr.nmocs.library.webapp.ws.FindByUserId;
import fr.nmocs.library.webapp.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.ws.LoanDTO;
import fr.nmocs.library.webapp.ws.LoanService;

@SuppressWarnings("serial")
public class UserLoanAction extends LibraryAbstractAction {

	@Autowired
	private LoanService loanService;

	// ===== INPUT
	public Integer loanToExtendId;

	// ===== OUTPUT
	private List<LoanDTO> returnedLoans;

	private List<LoanDTO> inProgressLoans;

	private List<LoanDTO> lateLoans;

	public List<LoanDTO> getReturnedLoans() {
		return returnedLoans;
	}

	public List<LoanDTO> getInProgressLoans() {
		return inProgressLoans;
	}

	public List<LoanDTO> getLateLoans() {
		return lateLoans;
	}

	// ===== OUTPUT TESTS
	public Boolean isReturnedLoansNotEmpty() {
		return CollectionUtils.isNotEmpty(returnedLoans);
	}

	public Boolean isInProgressLoansNotEmpty() {
		return CollectionUtils.isNotEmpty(inProgressLoans);
	}

	public Boolean isLateLoansNotEmpty() {
		return CollectionUtils.isNotEmpty(lateLoans);
	}

	public Boolean areLoansEmpty() {
		return CollectionUtils.isEmpty(returnedLoans) && CollectionUtils.isEmpty(inProgressLoans)
				&& CollectionUtils.isEmpty(lateLoans);
	}

	public Boolean isLoanExtendable(LoanDTO loan) {
		return LoanUtils.isLoanExtendable(loan);
	}

	// ========== ACTIONS
	public String doConsult() {
		doReconnectIfNecessary();
		if (!getIsUserConnected()) {
			return ERROR;
		}
		// Paramètres de récupération
		FindByUserId params = new FindByUserId();
		params.setUserId(getUserId());
		try {
			List<LoanDTO> loanList = loanService.findByUserId(params, getUserToken()).getReturn();
			if (CollectionUtils.isNotEmpty(loanList)) {
				// Set returned
				returnedLoans = loanList.stream().filter(loan -> loan.getReturnDate() != null)
						.collect(Collectors.toList());
				// Set in progress
				inProgressLoans = LoanUtils.filterNotLateLoans(
						loanList.stream().filter(loan -> loan.getReturnDate() == null).collect(Collectors.toList()));
				// Set late
				lateLoans = LoanUtils.filterLateLoans(
						loanList.stream().filter(loan -> loan.getReturnDate() == null).collect(Collectors.toList()));
			}
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public String doExtendLoan() {
		doReconnectIfNecessary();
		if (!getIsUserConnected() || loanToExtendId == null) {
			return ERROR;
		}

		ExtendLoan params = new ExtendLoan();
		params.setId(loanToExtendId);
		try {
			loanService.extendLoan(params, getUserToken());
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
}
