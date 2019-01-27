package fr.nmocs.library.batch;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	private static Session session;

	private static Properties emailProps;

	public static void setEmailProperties(Properties emailProperties) {
		emailProps = emailProperties;
	}

	/**
	 * Instantiate and return email session
	 * 
	 * @return
	 */
	private static Session getSession() {
		if (session != null) {
			return session;
		}
		Properties props = new Properties();
		props.put("mail.smtp.host", emailProps.getProperty("mail.smtp.host")); // SMTP Host
		props.put("mail.smtp.port", emailProps.getProperty("mail.smtp.port")); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {

			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailProps.getProperty("mail.noreply.address"),
						emailProps.getProperty("mail.noreply.password"));
			}
		};
		session = Session.getInstance(props, auth);
		return session;
	}

	/**
	 * Send mail
	 * 
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(String toEmail, String subject, String body) {
		Session session = getSession();

		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("nathanael.morel@outlook.fr", "NoReply-JD"));
			msg.setReplyTo(InternetAddress.parse("nathanael.morel@outlook.fr", false));
			msg.setSentDate(new Date());

			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(msg);
		} catch (Exception e) {
			session = null;
			e.printStackTrace();
		}
	}
}
