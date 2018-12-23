package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.model.Loan;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "LoanService", serviceName = "LoanService")
public interface LoanService {

	Loan createLoan(@WebParam(name = "loan") Loan loan) throws LibraryWebserviceException;

	Loan updateLoan(@WebParam(name = "loan") Loan loan) throws LibraryWebserviceException;

	Loan findLoanById(@WebParam(name = "id") Integer id);

	List<Loan> findByUserId(@WebParam(name = "userId") Integer userId);

	List<Loan> findNotReturned();
}
