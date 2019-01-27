package fr.nmocs.library.batch;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import fr.nmocs.library.batch.webservice.FindNotReturned;
import fr.nmocs.library.batch.webservice.LibraryWebserviceException_Exception;
import fr.nmocs.library.batch.webservice.Loan;
import fr.nmocs.library.batch.webservice.LoanService;
import fr.nmocs.library.batch.webservice.LoanService_Service;
import fr.nmocs.library.batch.webservice.TokenService;
import fr.nmocs.library.batch.webservice.TokenServiceService;

public class App {

	private static final String SUBJECT = "BOOK NOT RETURNED";

	private static void sendEmail(List<Loan> loans) {
		loans.stream().forEach(loan -> {
			StringBuilder sb = new StringBuilder();
			sb.append("Hello ").append(loan.getBorrower().getFirstName()).append(",\nWe are waiting for you to return ")
					.append(loan.getBookSample().getBook().getTitle()).append(" (borrowed on ")
					.append(loan.getStartDate().toString()).append(")");

			EmailUtils.sendEmail(loan.getBorrower().getEmail(), SUBJECT, sb.toString());
		});
	}

	public static void main(String[] args) throws IOException {
		// Récupération de fichier de configuration sous forme de Properties
		Properties webserviceProps = PropsUtils.getProps(args[0]);
		Properties emailProps = PropsUtils.getProps(args[1]);

		// Configuration EmailUtils
		EmailUtils.setEmailProperties(emailProps);
		WSUtils.setBaseUrl(webserviceProps.getProperty("webservice.services.url"));

		// Instanciation des clients webservice
		TokenService tokenService = new TokenServiceService(WSUtils.getUrl("/token?wsdl")).getTokenServicePort();
		LoanService loanService = new LoanService_Service(WSUtils.getUrl("/loan?wsdl")).getLoanServicePort();

		List<Loan> notReturnedLoans = null;
		try {
			// Récupération du token admin
			String token = tokenService.getLoginToken(webserviceProps.getProperty("webservice.admin.email"),
					webserviceProps.getProperty("webservice.admin.password"));
			// Récupération des prêts en retard
			notReturnedLoans = loanService.findNotReturned(new FindNotReturned(), token).getReturn();
		} catch (LibraryWebserviceException_Exception e) {
			e.printStackTrace();
		}
		// Envois des email de relance
		if (notReturnedLoans != null) {
			sendEmail(notReturnedLoans);
		}
	}
}
