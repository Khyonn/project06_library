package fr.nmocs.library.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.UserManagement;
import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;
import fr.nmocs.library.webservice.UserService;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class UserServiceImpl implements UserService {

	private static final String NOT_ALLOWED = "You are not allowed to perform this action";

	@Autowired
	private UserManagement userMgmt;

	@Autowired
	private AuthManagement authMgmt;

	@Override
	public User createUser(User user) throws LibraryWebserviceException {
		try {
			return userMgmt.createUser(user);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public User updateUser(User user, String token) throws LibraryWebserviceException {
		// Les utilisateurs peuvent mettre uniquement leur profil à jour sauf s'ils sont
		// admin
		User userFromToken = authMgmt.getUser(token);
		if (user == null || userFromToken == null
				|| (!authMgmt.isAdmin(token) && !user.getId().equals(userFromToken.getId()))) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}

		try {
			return userMgmt.updateUser(user);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	@Override
	public Admin grantAdminRightsToUser(Integer userId, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMgmt.grantAdminRightsToUser(userId);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException("You are not allowed to perform this action");
		}
	}

	@Override
	public User downgradeAdminToBasicUser(Integer adminId, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMgmt.downgradeAdminToBasicUser(adminId);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException("You are not allowed to perform this action");
		}
	}

	@Override
	public User findById(Integer id, String token) throws LibraryWebserviceException {
		// Les utilisateurs peuvent consulter leur profil uniquement (et admin)
		User userFromToken = authMgmt.getUser(token);
		if (userFromToken == null || (!authMgmt.isAdmin(token) && !userFromToken.getId().equals(id))) {
			throw new LibraryWebserviceException("You are not allowed to perform this action");
		}

		try {
			return userMgmt.findById(id);
		} catch (LibraryTechnicalException le) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMgmt.findByEmail(email);
		} catch (LibraryTechnicalException le) {
			return null;
		}
	}

	@Override
	public List<User> findByName(String name, String token) throws LibraryWebserviceException {
		checkAdmin(token);
		try {
			return userMgmt.findByName(name);
		} catch (LibraryTechnicalException le) {
			return new ArrayList<>();
		}
	}

	// ===== GESTION DES EXCEPTION

	/**
	 * Check if user is admin based on request token
	 * 
	 * @param token
	 * @throws LibraryWebserviceException
	 */
	private void checkAdmin(String token) throws LibraryWebserviceException {
		if (!authMgmt.isAdmin(token)) {
			throw new LibraryWebserviceException(NOT_ALLOWED);
		}
	}

	private String getExceptionReason(LibraryException le) {
		String reason = le.getErrorCode().getId() + " => Error editing user : ";
		switch (le.getErrorCode()) {
		// FIELD MISSING
		case USER_UNSETTED:
			reason += "no user given";
			break;
		case USER_DOESNT_EXIST:
			reason += "user doesn't exist";
			break;
		case USER_UNSETTED_LASTNAME:
			reason += "lastname not setted";
			break;
		case USER_UNSETTED_FIRSTNAME:
			reason += "firstname not setted";
			break;
		case USER_UNSETTED_EMAIL:
			reason += "email not setted";
			break;
		case USER_UNSETTED_PASSWORD:
			reason += "password not setted";
			break;
		// SIZE_OVERFLOW
		case USER_OVERSIZED_LASTNAME:
			reason += "lastname is too long";
			break;
		case USER_OVERSIZED_FIRSTNAME:
			reason += "firstname is too long";
			break;
		case USER_OVERSIZED_EMAIL:
			reason += "email is too long";
			break;
		case USER_OVERSIZED_PASSWORD:
			reason += "password is too long";
			break;
		// TECHNICAL
		case USER_DUPLICATED_EMAIL:
			reason += "email is already used";
			break;

		// NOT FOUND
		case USER_NOT_FOUND:
			reason += "no user found";
			break;

		default:
			reason += "unknown cause";
			break;
		}
		return reason;
	}

}
