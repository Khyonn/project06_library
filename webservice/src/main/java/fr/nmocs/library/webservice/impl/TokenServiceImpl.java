package fr.nmocs.library.webservice.impl;

import org.springframework.beans.factory.annotation.Autowired;

import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.webservice.TokenService;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

public class TokenServiceImpl implements TokenService {

	@Autowired
	private AuthManagement authMgmt;

	@Override
	public String getLoginToken(String userEmail, String userPassword) throws LibraryWebserviceException {
		try {
			return authMgmt.getToken(userEmail, userPassword);
		} catch (LibraryException le) {
			throw new LibraryWebserviceException(getExceptionReason(le));
		}
	}

	/**
	 * 
	 * @param Library Exception
	 * @return
	 */
	private String getExceptionReason(LibraryException le) {
		String reason = le.getErrorCode().getId() + " => Error : ";
		switch (le.getErrorCode()) {
		case PASSWORD_DOESNT_MATCH:
			reason += "email not found or password not matching";
			break;
		case USER_NOT_ACTIVE:
			reason += "user is not active";
			break;
		case TOKEN_NOT_VALID:
			reason += "token is not valid";
			break;
		default:
			reason += "unknown reason";

		}
		return reason;
	}

}
