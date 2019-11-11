package fr.nmocs.library.webapp.actions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.utils.UserUtils;
import fr.nmocs.library.webapp.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.ws.TokenService;

@SuppressWarnings("serial")
public class LoginAction extends LibraryAbstractAction {

	@Autowired
	private TokenService tokenService;

	// ===== INPUTS
	public String userEmail;

	public String userPassword;

	// ========== ACTIONS
	public String doLogin() {
		if (getIsUserConnected()) {
			return ERROR;
		}
		if (StringUtils.isAnyBlank(userEmail, userPassword)) {
			return INPUT;
		}

		String token;
		try {
			token = tokenService.getLoginToken(userEmail, userPassword);
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
