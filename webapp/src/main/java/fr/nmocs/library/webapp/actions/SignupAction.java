package fr.nmocs.library.webapp.actions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.factories.WebserviceFactory;
import fr.nmocs.library.webapp.webservice.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.webservice.User;

@SuppressWarnings("serial")
public class SignupAction extends LibraryAbstractAction {

	@Autowired
	private WebserviceFactory wsFactory;

	// ===== INPUTS
	public User user;

	public String verifPassword;

	// ========= ACTIONS

	public String doSignup() {
		if (user == null || !StringUtils.equals(user.getPassword(), verifPassword)) {
			if (!StringUtils.equals(user.getPassword(), verifPassword)) {
				addActionError("Passwords don't match");
			}
			return ERROR;
		}
		try {
			wsFactory.getUserService().createUser(user);
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return INPUT;
		}

		return LOGIN;
	}

}
