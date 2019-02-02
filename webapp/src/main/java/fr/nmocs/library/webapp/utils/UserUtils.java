package fr.nmocs.library.webapp.utils;

import fr.nmocs.library.webapp.webservice.User;

public final class UserUtils {

	public final static Integer WRONG_ID = -1;

	public static User createUserInfos(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}
}
