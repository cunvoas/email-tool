package com.github.cunvoas.util;

import static com.github.cunvoas.util.MimeBundle.*;

/**
 * Helper de manipulation des types MIME (Multipurpose Internet Mail Extensions).
 * @see http://www.commentcamarche.net/contents/courrier-electronique/mime.php3
 * @author cunvoas
 */
public abstract class MimeHelper {
	
	
	private static final String CONTENT_TYPE = "contentType.";
	private static final String EXTENSION = "extension.";
	private static final String AUTHORIZED = "autorized";
	
	/**
	 * Test l'autorisation d'upload.
	 * @param contentType
	 * @return
	 */
	public static final boolean isAutorized(String contentType) {
		boolean ret = false;
		String auths = getMessage(AUTHORIZED);
		String ext = getExtention(contentType);
		if (auths.indexOf(ext)>=0) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Retourne l'Extention du type.
	 * @param contentType
	 * @return Extention du type
	 */
	public static final String getExtention(final String contentType) {
		return getMessage(CONTENT_TYPE + contentType);
	}
	
	/**
	 * Retourne le type de l'extention.
	 * @param extension
	 * @return type de l'extention.
	 */
	public static final String getContentType(String extension) {
		return getMessage(EXTENSION+extension);
	}
}
