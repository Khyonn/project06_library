package fr.nmocs.library.loanperemptionwarn.jobs.items;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import fr.nmocs.library.loanperemptionwarn.ws.FindAlmostPeremptedLoans;
import fr.nmocs.library.loanperemptionwarn.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.loanperemptionwarn.ws.LoanDTO;
import fr.nmocs.library.loanperemptionwarn.ws.LoanService;
import fr.nmocs.library.loanperemptionwarn.ws.TokenService;

public class LoanReader implements ItemReader<LoanDTO> {

	// ===== Properties
	@Value("${webservice.authentication.email}")
	private String wsEmail;

	@Value("${webservice.authentication.password}")
	private String wsPassword;

	// ===== Services
//	@Autowired
//	private LoanService loanService;
//
//	@Autowired
//	private TokenService tokenService;

	// ===== Lecture des prets

	private List<LoanDTO> loans;

	private Integer loanIndex = 0;

	// ===== Méthodes

	public LoanReader (LoanService loanService, TokenService tokenService) {
		FindAlmostPeremptedLoans params = new FindAlmostPeremptedLoans();
		try {
			String wsToken = tokenService.getLoginToken(wsEmail, wsPassword);
			loans = loanService.findAlmostPeremptedLoans(params, wsToken).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			loans = new ArrayList<LoanDTO>();
		}
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		return RepeatStatus.FINISHED;
	}

	public LoanDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return loans.isEmpty() ? null : loans.get(loanIndex++);
	}

}
