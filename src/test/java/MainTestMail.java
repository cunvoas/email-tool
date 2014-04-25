import java.util.Arrays;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 */

/**
 * @author Cunvoas
 *
 */
public class MainTestMail {
	public static void main(String[] args) {
		String name = "aaaa";
		String email = "myjavatest@gmail.com";
		System.out.println(Arrays.asList(args));
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("myjavatest","XXXXXXXXXX");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("www.empeiria.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Testing Subject");
			message.setText("Dear "+name+"," +
					"\n\n No spam to my email, please!");
 
			Transport.send(message);
			System.out.println("Done");
 
		} catch (Exception e) {
		}
	}

}
