package fr.nmocs.library.loanperemptionwarn.jobs.items;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import fr.nmocs.library.loanperemptionwarn.ws.FindAlmostPeremptedLoans;
import fr.nmocs.library.loanperemptionwarn.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.loanperemptionwarn.ws.LoanDTO;
import fr.nmocs.library.loanperemptionwarn.ws.LoanService;
import fr.nmocs.library.loanperemptionwarn.ws.TokenService;

public class LoanReader implements ItemReader<LoanDTO> {

	// ===== Lecture des prets

	private List<LoanDTO> loans;

	private Integer loanIndex = 0;

	// ===== Méthodes

	public LoanReader (LoanService loanService, TokenService tokenService, String wsEmail, String wsPassword) {
		FindAlmostPeremptedLoans params = new FindAlmostPeremptedLoans();
		try {
			String wsToken = tokenService.getLoginToken(wsEmail, wsPassword);
			loans = loanService.findAlmostPeremptedLoans(params, wsToken).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			loans = new ArrayList<LoanDTO>();
		}
	}

	public LoanDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return loans.isEmpty() || loans.size() <= loanIndex ? null : loans.get(loanIndex++);
	}

}
