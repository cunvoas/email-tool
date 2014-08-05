package com.github.cunvoas.eml;

import java.util.ArrayList;
import java.util.List;

public class EmlContent {
	
	private List<EmlContentElement> body = new ArrayList<EmlContentElement>();
	private List<EmlContentElement> attachements = new ArrayList<EmlContentElement>();
	
	/**
	 * Getter for body.
	 * @return the body
	 */
	public List<EmlContentElement> getBody() {
		return body;
	}
	/**
	 * Setter for body.
	 * @param body the body to set
	 */
	public void setBody(List<EmlContentElement> body) {
		this.body = body;
	}
	/**
	 * Getter for attachements.
	 * @return the attachements
	 */
	public List<EmlContentElement> getAttachements() {
		return attachements;
	}
	/**
	 * Setter for attachements.
	 * @param attachements the attachements to set
	 */
	public void setAttachements(List<EmlContentElement> attachements) {
		this.attachements = attachements;
	}

}
