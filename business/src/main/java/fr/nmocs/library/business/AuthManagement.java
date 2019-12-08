package fr.nmocs.library.business;

import fr.nmocs.library.model.User;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;

public interface AuthManagement {

	Boolean isAdmin(String token);

	User getUser(String token);

	Integer getUserId(String token);

	void verifyToken(String token) throws LibraryBusinessException;

	String getToken(String userEmail, String userPassword) throws LibraryException;
}
