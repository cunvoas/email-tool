/**
 * 
 */
package com.github.cunvoas.eml;

/**
 * @author CUNVOAS
 */
class EmlContentElement implements Comparable<EmlContentElement> {
	private String contentType;
	private String encoding;
	private String content;
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EmlContentElement)) {
			return false;
		}
		EmlContentElement other = (EmlContentElement) obj;
		if (contentType == null) {
			if (other.contentType != null) {
				return false;
			}
		} else if (!contentType.equals(other.contentType)) {
			return false;
		}
		return true;
	}
	/**
	 * Getter for contentType.
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * Setter for contentType.
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * Getter for encoding.
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}
	/**
	 * Setter for encoding.
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	/**
	 * Getter for content.
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * Setter for content.
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(EmlContentElement o) {
		return this.getContentType().compareTo( o.getContentType());
	}
	
	

}
