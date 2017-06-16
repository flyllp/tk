package net.historm.tk.encode;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5相关工具类
 */
public class MD5Util {

	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

	/**
	 * 给出指定字符串的MD5值
	 * 
	 * @param inStr 任意字符串
	 * @return 字符串的MD5值，如果字符串是null或者过程中有异常则返回null
	 */
	public static String md5(String inStr) {
		if (inStr == null) {
			return null;
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder(1024);
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
