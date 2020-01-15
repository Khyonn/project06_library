package fr.nmocs.library.webapp.actions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.webapp.utils.UserUtils;
import fr.nmocs.library.webapp.ws.FindById;
import fr.nmocs.library.webapp.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.ws.UpdateUser;
import fr.nmocs.library.webapp.ws.UserDTO;
import fr.nmocs.library.webapp.ws.UserService;

@SuppressWarnings("serial")
public class UserProfileAction extends LibraryAbstractAction {

	@Autowired
	private UserService userService;

	private UserDTO user;

	// ===== INPUT
	public String verifPassword;

	public String verifEmail;

	public void setUser(UserDTO user) {
		this.user = user;
	}

	// ===== OUTPUT
	public UserDTO getUser() {
		return user;
	}

	// ========== ACTIONS

	public String doConsult() {
		doReconnectIfNecessary();
		Integer userId = getUserId();
		if (!getIsUserConnected() || userId.equals(UserUtils.WRONG_ID)) {
			addActionError("You are not connected");
			return ERROR;
		}
		FindById params = new FindById();
		params.setId(getUserId());
		try {
			user = userService.findById(params, getUserToken()).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public String doEdit() {
		doReconnectIfNecessary();
		if (!getIsUserConnected() || getUserId().equals(UserUtils.WRONG_ID)) {
			return ERROR;
		}
		if (user == null || !StringUtils.equals(user.getPassword(), verifPassword)
				|| !StringUtils.equals(user.getEmail(), verifEmail)) {
			if (!StringUtils.equals(user.getPassword(), verifPassword)) {
				addActionError("Passwords don't match");
			}
			if (!StringUtils.equals(user.getEmail(), verifEmail)) {
				addActionError("Emails don't match");
			}
			return INPUT;
		}

		UpdateUser params = new UpdateUser();
		user.setId(getUserId());
		params.setUser(user);
		try {
			userService.updateUser(params, getUserToken());
		} catch (LibraryWebserviceException_Exception e) {
			addActionError(e.getFaultInfo().getMessage());
			return INPUT;
		}
		cleanSession();
		// Update du token (en mettant à null le token, la méthode doReconnect forcera
		// le rechargement du token)
		setUserInfos(UserUtils.createUserInfos(user.getEmail(), user.getPassword()));
		doReconnectIfNecessary();
		addActionMessage("Edition OK");
		return SUCCESS;
	}
}
