package com.github.cunvoas.mail;

/**
 * Contact avec libell� (nom) et adresse mail).
 * @author CUNVOAS
 */
public class MailContact {
	private String libelleContact; // email label
	private String mailContact; // email address
	
	/**
	 * Constructeur par defaut.
	 */
	public MailContact() {
		super();
	}

	/**
	 * @param inLinekey
	 * @param inLineRessource
	 */
	public MailContact(String libelleContact, String mailContact) {
		this.libelleContact = libelleContact; 
		this.mailContact = mailContact; 
	}
	
	/**
	 * Getter for libelleContact.
	 * @return the libelleContact
	 */
	public String getLibelleContact() {
		return libelleContact;
	}
	/**
	 * Setter for libelleContact.
	 * @param libelleContact the libelleContact to set
	 */
	public void setLibelleContact(String libelleContact) {
		this.libelleContact = libelleContact;
	}
	/**
	 * Getter for mailContact.
	 * @return the mailContact
	 */
	public String getMailContact() {
		return mailContact!=null?mailContact.toLowerCase():"";
	}
	
	/**
	 * Setter for mailContact.
	 * @param mailContact the mailContact to set
	 */
	public void setMailContact(String mailContact) {
		this.mailContact = mailContact;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return libelleContact+" :"+mailContact;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mailContact == null) ? 0 : mailContact.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MailContact other = (MailContact) obj;
		if (mailContact == null) {
			if (other.mailContact != null) {
				return false;
			}
		} else if (!mailContact.equals(other.mailContact)) {
			return false;
		}
		return true;
	}
	
	
}
