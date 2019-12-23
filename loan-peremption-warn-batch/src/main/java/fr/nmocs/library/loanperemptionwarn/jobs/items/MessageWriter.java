package fr.nmocs.library.loanperemptionwarn.jobs.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MessageWriter implements ItemWriter<SimpleMailMessage> {

	private JavaMailSender emailSender;

	public MessageWriter (JavaMailSender pEmailSender) {
		emailSender = pEmailSender;
	}

	public void write(List<? extends SimpleMailMessage> items) throws Exception {
		for (SimpleMailMessage email : items) {
			emailSender.send(email);
		}
	}

}
