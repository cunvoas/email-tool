package com.github.cunvoas.util;

/**
 * @author CUNVOAS
 * @see https://svn.ckeditor.com/FCKeditor.Java/branches/2.6.x/java-core/src/main/java/net/fckeditor/tool/UtilsFile.java
 */
public class FileHelper {
	public static String sanitizeFileName(final String filename) {

		// Remove \ / | : ? * " < > 'Control Chars' with _
		return filename.replaceAll("\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
	}
}
