package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.model.Loan;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "LoanService", serviceName = "LoanService")
public interface LoanService {

	Loan createLoan(@WebParam(name = "loan") Loan loan, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	Loan updateLoan(@WebParam(name = "loan") Loan loan, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	Loan findLoanById(@WebParam(name = "id") Integer id, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	List<Loan> findByUserId(@WebParam(name = "userId") Integer userId,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	List<Loan> findNotReturned(@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;
}
