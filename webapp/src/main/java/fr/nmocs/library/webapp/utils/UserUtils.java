package fr.nmocs.library.webapp.utils;

import fr.nmocs.library.webapp.ws.UserDTO;

public final class UserUtils {

	public final static Integer WRONG_ID = -1;

	public static UserDTO createUserInfos(String email, String password) {
		UserDTO user = new UserDTO();
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}
}
