package fr.nmocs.library.loanperemptionwarn.jobs.items;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;

import fr.nmocs.library.loanperemptionwarn.ws.LoanDTO;

public class LoanToMessageProcesor implements ItemProcessor<LoanDTO, SimpleMailMessage> {

	public SimpleMailMessage process(LoanDTO item) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		StringBuilder sb = new StringBuilder("");
		// Salutation
		sb.append("Hello ").append(item.getBorrower().getFirstName()).append(",").append("\n");
		// Body
		sb.append("Your loan of ").append(item.getBookSample().getBook().getTitle())
				.append(" is about to expire within a few days.");
		if (item.getProlongationNumber().equals(0)) {
			sb.append("You can extend it from website or ");
		} else {
			sb.append("Don't forget to ");
		}
		sb.append("return ").append(item.getBookSample().getBook().getTitle()).append(" to the library.").append("\n");
		// Signature
		sb.append("Best regards.").append("\n").append("Library");

		mail.setTo(item.getBorrower().getEmail());
		mail.setSubject("[Library] : Your loan is about to expire");
		mail.setText(sb.toString());
		return mail;
	}

}
