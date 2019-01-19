package fr.nmocs.library.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService
public interface TokenService {

	@WebMethod
	String getLoginToken(@WebParam(name = "email") String userEmail, @WebParam(name = "password") String userPassword)
			throws LibraryWebserviceException;
}
