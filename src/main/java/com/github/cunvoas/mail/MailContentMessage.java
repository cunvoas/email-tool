package com.github.cunvoas.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Contenu du message.
 * @author CUNVOAS
 */
public class MailContentMessage {
	
	public List<MailContact> getListCcContactMail() {
		return listCcContactMail;
	}
	public void setListCcContactMail(List<MailContact> listCcContactMail) {
		this.listCcContactMail = listCcContactMail;
	}
	private String from;
	//private String toMail;
	//private String toContact;
	private String sujet;
	private String corpsTexte;
	
	private StringBuffer debugBouchonDest;
	
	private List<MailContact> listToContactMail = new ArrayList<MailContact>();
	private List<MailContact> listCcContactMail = new ArrayList<MailContact>();
	
	private List<File> listFile = new ArrayList<File>();
	private List<MailInLine> listInLine = new ArrayList<MailInLine>();
	
	/**Bouchonnage des destinataires.
	 * @param originalTo
	 * @param toCcBcc
	 */
	public void appendDestinatireOriginalMaisBouchon(List<MailContact> originalTo, String toCcBcc) {
		if (debugBouchonDest==null) {
			debugBouchonDest = new StringBuffer();
		}
		for (Iterator<MailContact> iterator = originalTo.iterator(); iterator.hasNext();) {
			MailContact contact = iterator.next();
			debugBouchonDest.append("Send ");
			debugBouchonDest.append(toCcBcc);
			debugBouchonDest.append(" : ");
			debugBouchonDest.append(contact.toString());
			debugBouchonDest.append("<br />\n");
		}
	}
	
	
	/**
	 * Getter for from.
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * Setter for from.
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * Getter for sujet.
	 * @return the sujet
	 */
	public String getSujet() {
		return sujet;
	}
	/**
	 * Setter for sujet.
	 * @param sujet the sujet to set
	 */
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	/**
	 * Getter for corpsTexte.
	 * @return the corpsTexte
	 */
	public String getCorpsTexte() {
		return corpsTexte;
	}
	/**
	 * Setter for corpsTexte.
	 * @param corpsTexte the corpsTexte to set
	 */
	public void setCorpsTexte(String corpsTexte) {
		this.corpsTexte = corpsTexte;
	}
	/**
	 * Getter for listFile.
	 * @return the listFile
	 */
	public List<File> getListFile() {
		return listFile;
	}
	/**
	 * Getter for listInLine.
	 * @return the listInLine
	 */
	public List<MailInLine> getListInLine() {
		return listInLine;
	}
	/**
	 * Setter for listFile.
	 * @param listFile the listFile to set
	 */
	public void appendListFile(File file) {
		this.listFile.add(file);
	}
	/**
	 * Setter for listInLine.
	 * @param listInLine the listInLine to set
	 */
	public void appendListInLine(String key, String ressource) {
		this.listInLine.add(new MailInLine(key, ressource));
	}
	/**
	 * Getter for listToContactMail
	 * @return the listToContactMail
	 */
	public List<MailContact> getListToContactMail() {
		return listToContactMail;
	}
	/**
	 * Setter for listToContactMail
	 * @param listToContactMail the listToContactMail to set
	 */
	public void setListToContactMail(List<MailContact> listToContactMail) {
		this.listToContactMail = listToContactMail;
	}
	
	/**Getter for debugBouchonDest.
	 * @return the debugBouchonDest
	 */
	public String getDebugBouchonDest() {
		if (debugBouchonDest!=null) {
			return debugBouchonDest.toString();
		}
		return "";
	}
	
	
}
