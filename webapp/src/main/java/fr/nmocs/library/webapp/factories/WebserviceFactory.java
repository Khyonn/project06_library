package fr.nmocs.library.webapp.factories;

import fr.nmocs.library.webapp.webservice.BookService;
import fr.nmocs.library.webapp.webservice.LoanService;
import fr.nmocs.library.webapp.webservice.TokenService;
import fr.nmocs.library.webapp.webservice.UserService;

public interface WebserviceFactory {

	TokenService getTokenService();

	BookService getBookService();

	UserService getUserService();

	LoanService getLoanService();

}
