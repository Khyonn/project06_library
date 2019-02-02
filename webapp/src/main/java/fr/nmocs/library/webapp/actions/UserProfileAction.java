package fr.nmocs.library.webapp.actions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.factories.WebserviceFactory;
import fr.nmocs.library.webapp.utils.UserUtils;
import fr.nmocs.library.webapp.webservice.FindById;
import fr.nmocs.library.webapp.webservice.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.webservice.UpdateUser;
import fr.nmocs.library.webapp.webservice.User;

@SuppressWarnings("serial")
public class UserProfileAction extends LibraryAbstractAction {

	@Autowired
	WebserviceFactory wsFactory;

	// ===== INPUT
	public String verifPassword;

	// ===== INPUT AND OUTPUT
	public User user;

	// ========== ACTIONS

	public String doConsult() {
		doReconnectIfNecessary();
		Integer userId = getUserId();
		if (!getIsUserConnected() || userId.equals(UserUtils.WRONG_ID)) {
			return ERROR;
		}
		FindById params = new FindById();
		params.setId(getUserId());
		try {
			user = wsFactory.getUserService().findById(params, getUserToken()).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public String doEdit() {
		doReconnectIfNecessary();
		if (!getIsUserConnected() || user == null || user.getId().equals(UserUtils.WRONG_ID)
				|| !StringUtils.equals(user.getPassword(), verifPassword)) {
			if (!StringUtils.equals(user.getPassword(), verifPassword)) {
				addActionError("Passwords don't match");
			}
			return ERROR;
		}

		UpdateUser params = new UpdateUser();
		user.setId(getUserId());
		params.setUser(user);
		try {
			wsFactory.getUserService().updateUser(params, getUserToken());
			setUserInfos(UserUtils.createUserInfos(user.getEmail(), user.getPassword()));
			// Update du token
			setUserToken(null);
			doReconnectIfNecessary();
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
}
