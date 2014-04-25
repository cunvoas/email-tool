package com.github.cunvoas.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Compte d'authentification de messagerie.
 * @author CUNVOAS
 * @see javax.mail.Authenticator
 */
public class MailSenderAccount extends Authenticator {
	
	private String user;
	private String password;
	
	/**
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
	
	/**
	 * Constructeur.
	 * @param user
	 * @param password
	 */
	public MailSenderAccount(String user, String password) {
		this.user=user;
		this.password=password;
	}

}
