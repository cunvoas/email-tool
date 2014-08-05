package com.github.cunvoas.util;

import java.util.ResourceBundle;

class MimeBundle {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("MIME");
	
	/**
	 * get property value if found, key else.
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		String msg = null;
		try {
			msg = BUNDLE.getString(key);
		} catch (Exception notFount) {
			msg = key;
		}
		
		return msg;
	}
}
