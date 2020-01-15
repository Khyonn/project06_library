package fr.nmocs.library.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.nmocs.library.webservice.dto.LoanDTO;
import fr.nmocs.library.webservice.error.LibraryWebserviceException;

@WebService(name = "LoanService", serviceName = "LoanService")
public interface LoanService {

	LoanDTO createLoan(@WebParam(name = "loan") LoanDTO loan, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	LoanDTO updateLoan(@WebParam(name = "loan") LoanDTO loan, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	LoanDTO findLoanById(@WebParam(name = "id") Integer id, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	LoanDTO extendLoan(@WebParam(name = "id") Integer id, @WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	List<LoanDTO> findByUserId(@WebParam(name = "userId") Integer userId,
			@WebParam(name = "token", header = true) String token) throws LibraryWebserviceException;

	List<LoanDTO> findNotReturned(@WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;

	List<LoanDTO> findAlmostPeremptedLoans(@WebParam(name = "token", header = true) String token)
			throws LibraryWebserviceException;
}
