package com.github.cunvoas.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Classe utilitaire pour l'envoi de mails.
 * @author CUNVOAS
 */
public class MailerUtil {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailerUtil.class);

	private static final String ERREUR_ENVOI_MAIL ="Erreur lors de l'envoi du mail : ";
	
	// Injecteurs spring
	private JavaMailSender mailSender = null;

	private String mailFrom;
	private Boolean mailBouchon;
	private String mailFromLabel;
	private String mailBouchonDestinataire;

	/**
	 * Produit l'email.
	 * 
	 * @param content
	 */
	public void produceEMailMessage(final MailContentMessage content)
			throws MailerException {
		try {
			final MimeMessage message = mailSender.createMimeMessage();
			final MimeMessageHelper msgHelper = new MimeMessageHelper(message,
					true, "UTF-8");

			if (content.getFrom() == null) {
				msgHelper.setFrom(mailFrom, mailFromLabel);
			} else {
				msgHelper.setFrom(content.getFrom());
			}

			if (Boolean.TRUE.equals(mailBouchon)) {
				String[] destBouchon = mailBouchonDestinataire.split(" |,|;");
				for (int i = 0; i < destBouchon.length; i++) {
					if (!MailerUtil.checkEmail(destBouchon[i])) {
						String msg = "invalid email : " + destBouchon[i];
						LOGGER.error(msg);
						throw new MessagingException(msg);
					}
					String libelle = destBouchon[i];
					if (content.getListToContactMail() != null
							&& content.getListToContactMail().size() == 1) {
						libelle = ((MailContact) content.getListToContactMail()
								.get(0)).getMailContact();
					}
					msgHelper.addTo(destBouchon[i],
							"BOUCHON email destinataire : " + libelle);
				}
			} else {
				for (Iterator<MailContact> iterator = content
						.getListToContactMail().iterator(); iterator.hasNext();) {
					MailContact contactDest = iterator.next();
					if (!MailerUtil.checkEmail(contactDest.getMailContact())) {
						String msg = "invalid email : "
								+ contactDest.getMailContact();
						LOGGER.error(msg);
						throw new MessagingException(msg);
					}
					msgHelper.addTo(contactDest.getMailContact(),
							contactDest.getLibelleContact());
				}

				for (Iterator<MailContact> iterator = content
						.getListCcContactMail().iterator(); iterator.hasNext();) {
					MailContact contactDest = (MailContact) iterator.next();
					if (!MailerUtil.checkEmail(contactDest.getMailContact())) {
						String msg = "invalid email : "
								+ contactDest.getMailContact();
						LOGGER.error(msg);
						throw new MessagingException(msg);
					}
					msgHelper.addCc(contactDest.getMailContact(),
							contactDest.getLibelleContact());
				}
			}

			msgHelper.setSubject(content.getSujet());
			msgHelper.setText(
					content.getDebugBouchonDest() + content.getCorpsTexte(),
					true);

			for (Iterator<MailInLine> iterator = content.getListInLine()
					.iterator(); iterator.hasNext();) {
				MailInLine inLine = iterator.next();
				msgHelper.addInline(inLine.getInLinekey(),
						new ClassPathResource(inLine.getInLineRessource()));
			}
			for (Iterator<File> iterator = content.getListFile().iterator(); iterator
					.hasNext();) {
				File file = iterator.next();
				msgHelper.addAttachment(file.getName(), file);
			}

			LOGGER.debug("Tentative d'envoi du mail");
			mailSender.send(message);
			LOGGER.info("Envoi du mail OK");

		} catch (MailException e) {
			LOGGER.error(ERREUR_ENVOI_MAIL + e.getMessage(),
					e);
			throw new MailerException(e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(ERREUR_ENVOI_MAIL + e.getMessage(),
					e);
			throw new MailerException(e);
		} catch (MessagingException e) {
			LOGGER.error(ERREUR_ENVOI_MAIL + e.getMessage(),
					e);
			throw new MailerException(e);
		} catch (Exception e) {
			LOGGER.error(ERREUR_ENVOI_MAIL + e.getMessage(),
					e);
			throw new MailerException(e);
		}
	}

	/**
	 * Génération de l'email.
	 * @param content
	 * @throws MailerException
	 */
	public void produceEMail(final MailContentMessage content)
			throws MailerException {
		produceEMailMessage(content);
	}

	/**
	 * Setter for mailFrom.
	 * 
	 * @param mailFrom
	 *            the mailFrom to set
	 */
	public void setMailFrom(final String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * Getter for mailBouchon.
	 * 
	 * @return the mailBouchon
	 */
	public Boolean getMailBouchon() {
		return mailBouchon;
	}

	/**
	 * Setter for mailBouchon.
	 * 
	 * @param mailBouchon
	 *            the mailBouchon to set
	 */
	public void setMailBouchon(final Boolean mailBouchon) {
		this.mailBouchon = mailBouchon;
	}

	/**
	 * Getter for mailBouchonDestinataire.
	 * 
	 * @return the mailBouchonDestinataire
	 */
	public String getMailBouchonDestinataire() {
		return mailBouchonDestinataire;
	}

	/**
	 * Setter for mailBouchonDestinataire.
	 * 
	 * @param mailBouchonDestinataire
	 *            the mailBouchonDestinataire to set
	 */
	public void setMailBouchonDestinataire(final String mailBouchonDestinataire) {
		this.mailBouchonDestinataire = mailBouchonDestinataire;
	}

	/**
	 * Setter for mailSender.
	 * 
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Setter for mailFromLabel.
	 * 
	 * @param mailFromLabel
	 *            the mailFromLabel to set
	 */
	public void setMailFromLabel(final String mailFromLabel) {
		this.mailFromLabel = mailFromLabel;
	}

	/**
	 * Email validation RFC 2822.
	 * 
	 * @param email
	 * @return
	 * @see http://www.regular-expressions.info/email.html
	 */
	public static boolean checkEmail(String email) {
		boolean chk = false;
		if (email != null) {
			chk = email.matches(REGEX_EMAIL);
		}
		return chk;
	}

	/**
	 * The Official Standard: RFC 2822.
	 * 
	 * @see http://www.regular-expressions.info/email.html
	 */
	private static final String REGEX_EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[a-z]{2}|com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|museum)\\b";
}
