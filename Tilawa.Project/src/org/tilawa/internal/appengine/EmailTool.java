package org.tilawa.internal.appengine;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 
 * @author m.sharif	(mosabsalih@gmail.com)
 *
 */
public final class EmailTool {

	private static final Logger log = Logger.getLogger(EmailTool.class.getSimpleName());

	public static void send(String from, String to,String subject, String msg){

		if(ValidatorTool.isValidEmailID(from) && ValidatorTool.isValidEmailID(to)) {

			Properties properties = new Properties();

			Session emailSession = Session.getDefaultInstance(properties, null);

			Message emailMessage = new MimeMessage(emailSession);

			try {

				emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				emailMessage.setFrom(new InternetAddress(from));
				emailMessage.setSubject(subject);
				emailMessage.setText(msg);

				 emailSession.setDebug(true);
				Transport.send(emailMessage);

			} catch (AddressException e) {

				log.log(Level.SEVERE,"AddressException",e);

			} catch (MessagingException e) {

				log.log(Level.SEVERE,"MessagingException",e);

			} catch (Throwable Throwable) {

				log.log(Level.SEVERE,"Throwable",Throwable);

			}

		} else {

			log.log(Level.SEVERE,"Either email To [ " + to + "] or email from [" + from + "] are not correct !!!");
			
		}

	}

}
