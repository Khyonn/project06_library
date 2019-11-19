package fr.nmocs.library.business.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.nmocs.library.business.model.LibraryEmail;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class LibraryEmailUtilsImpl implements LibraryEmailUtils {

	private final String HOST;

	private final String PORT;

	private final String EMAIL_ADDRESS;

	private final String EMAIL_PASSWORD;

	public LibraryEmailUtilsImpl (@Value("mail.smtp.host") String host, @Value("mail.smtp.port") String port, @Value(
		"mail.noreply.address"
	) String adress, @Value("mail.noreply.password") String password) {
		HOST = host;
		PORT = port;
		EMAIL_ADDRESS = adress;
		EMAIL_PASSWORD = password;
	}

	private Session getEmailSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST); // SMTP Host
		props.put("mail.smtp.port", PORT); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {

			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_ADDRESS, EMAIL_PASSWORD);
			}
		};
		return Session.getInstance(props, auth);
	}

	private MimeMessage getMessage(LibraryEmail email, Session session) throws LibraryTechnicalException {
		MimeMessage msg = new MimeMessage(session);
		// set message headers
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(EMAIL_ADDRESS, "NoReply-JD"));
			msg.setReplyTo(InternetAddress.parse(EMAIL_ADDRESS, false));
			msg.setSentDate(new Date());

			msg.setSubject(email.getSubject(), "UTF-8");
			msg.setText(email.getBody(), "UTF-8");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
		} catch (Exception e) {
			throw new LibraryTechnicalException(ErrorCode.EMAIL_CANNOT_CREATE_MSG);
		}
		return msg;
	}

	@Override
	public void sendEmail(LibraryEmail email) throws LibraryTechnicalException {
		try {
			Transport.send(getMessage(email, getEmailSession()));
		} catch (MessagingException e) {
			throw new LibraryTechnicalException(ErrorCode.EMAIL_CANNOT_SEND_MSG);
		}

	}

	@Override
	public void sendEmails(List<LibraryEmail> emails) throws LibraryTechnicalException {
		Session session = getEmailSession();

		for (LibraryEmail email : emails) {
			try {
				Transport.send(getMessage(email, session));
			} catch (MessagingException e) {
				throw new LibraryTechnicalException(ErrorCode.EMAIL_CANNOT_SEND_MSG);
			}
		}
		;
	}

}
