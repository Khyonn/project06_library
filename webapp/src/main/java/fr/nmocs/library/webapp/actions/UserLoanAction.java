package fr.nmocs.library.webapp.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.factories.WebserviceFactory;
import fr.nmocs.library.webapp.webservice.ExtendLoan;
import fr.nmocs.library.webapp.webservice.FindByUserId;
import fr.nmocs.library.webapp.webservice.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.webservice.Loan;

@SuppressWarnings("serial")
public class UserLoanAction extends LibraryAbstractAction {

	@Autowired
	public WebserviceFactory wsFactory;

	// ===== INPUT
	public Integer loanToExtendId;

	// ===== OUTPUT
	public List<Loan> loanList;

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
			loanList = wsFactory.getLoanService().findByUserId(params, getUserToken()).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public String doExtendLoan() {
		doReconnectIfNecessary();
		if (!getIsUserConnected()) {
			return ERROR;
		}

		ExtendLoan params = new ExtendLoan();
		params.setId(loanToExtendId);
		try {
			wsFactory.getLoanService().extendLoan(params, getUserToken());
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
}
