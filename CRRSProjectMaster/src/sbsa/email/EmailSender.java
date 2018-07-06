package sbsa.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {

	public static boolean sendEmail(String to, String msg) {
		final String username = "sbsavzap@gmail.com";
		final String password = "SbsaVzap1";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sbsavzap@gmail.com"));
			//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("stephan.potgieter@standardbank.co.za"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Testing Java E-Mail");
			//String msg = "Sweet, this works. Can I stay home tomorrow?\nJust Joking, I'm not asking I'm telling. :-)\n\nP.S This mailbox is not monitored\nPotter out.";
			message.setText(msg);
			System.out.println(msg);
			Transport.send(message);
			return true;
			//System.out.println("Done");

		} catch (MessagingException e) {
			return false;
			//throw new RuntimeException(e);
		}
	}

}
