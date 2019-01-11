package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.User;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "UserService", serviceName = "UserService")
public interface UserService {

	User createUser(@WebParam(name = "user") User user) throws LibraryWebserviceException;

	User updateUser(@WebParam(name = "user") User user, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	Admin grantAdminRightsToUser(@WebParam(name = "userId") Integer userId,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	User downgradeAdminToBasicUser(@WebParam(name = "adminId") Integer adminId, @WebParam(name = "token") String token)
			throws LibraryWebserviceException;

	User findById(@WebParam(name = "id") Integer id, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	User findByEmail(@WebParam(name = "email") String email, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	List<User> findByName(@WebParam(name = "name") String name, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

}
