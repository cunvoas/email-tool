package com.github.cunvoas.mail;

/**
 * Attachement de media dans le HTML.
 * Usage : 
 *  JAVA : MailInLine("logo", "/mail/myLogo.gif")
 *  HTML : <img src="cid:logo" />
 * @author CUNVOAS
 */
class MailInLine {
	private String inLinekey;
	private String inLineRessource;
	
	/**
	 * @param inLinekey
	 * @param inLineRessource
	 */
	public MailInLine(String inLinekey, String inLineRessource) {
		this.inLinekey = inLinekey; 
		this.inLineRessource = inLineRessource; 
	}
	
	/**
	 * Getter for inLinekey.
	 * @return the inLinekey
	 */
	public String getInLinekey() {
		return inLinekey;
	}
	/**
	 * Setter for inLinekey.
	 * @param inLinekey the inLinekey to set
	 */
	public void setInLinekey(String inLinekey) {
		this.inLinekey = inLinekey;
	}
	/**
	 * Getter for inLineRessource.
	 * @return the inLineRessource
	 */
	public String getInLineRessource() {
		return inLineRessource;
	}
	/**
	 * Setter for inLineRessource.
	 * @param inLineRessource the inLineRessource to set
	 */
	public void setInLineRessource(String inLineRessource) {
		this.inLineRessource = inLineRessource;
	}
	
	
}
