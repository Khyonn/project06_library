package fr.nmocs.library.webapp.actions;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.factories.WebserviceFactory;
import fr.nmocs.library.webapp.utils.UserUtils;
import fr.nmocs.library.webapp.webservice.LibraryWebserviceException_Exception;

@SuppressWarnings("serial")
public class LoginAction extends LibraryAbstractAction {

	@Autowired
	private WebserviceFactory wsFactory;

	// ===== INPUTS
	public String userEmail;

	public String userPassword;

	// ========== ACTIONS
	public String doLogin() {
		if (getIsUserConnected()) {
			return ERROR;
		}

		String token;
		try {
			token = wsFactory.getTokenService().getLoginToken(userEmail, userPassword);
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return INPUT;
		}
		setUserInfos(UserUtils.createUserInfos(userEmail, userPassword));
		setUserToken(token);
		return SUCCESS;
	}

	public String doLogout() {
		cleanSession();
		return SUCCESS;
	}

}
