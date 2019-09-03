package org.jxkh.util;

import java.security.MessageDigest;


public class Md5Encoder {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String md5(String password) {
		try {
			MessageDigest messageDigest = MessageDigest
					.getInstance("MD5");
			messageDigest.update(password.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {

		}
		return null;
	};

	private static String getFormattedText(byte[] n) {
		int len = n.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(n[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[(n[j]) & 0x0f]);
		}
		return buf.toString();
	}
	

}