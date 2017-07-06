package net.historm.tk.encode;

import java.security.MessageDigest;

public class EncryptUtil {

	public static String encryptKey(String key) {
		String md5Str = md5(key);
		StringBuilder sb = new StringBuilder(50);
		sb.append(md5Str.substring(4, 9));
		sb.append(md5Str.substring(13, 18));
		sb.append(md5Str.substring(22, 26));
		sb.append(md5Str.substring(29, 32));
		return md5(sb.toString()).substring(3, 19);
	}

	public static String encryptReq(String req, String key) {
		String ret = null;
		try {
			ret = AESUtil.encrypt(encryptKey(key), req);
		} catch (Exception e) {

		}
		return ret;
	}

	public static String decryptReq(String req, String key) throws Exception {
		return AESUtil.decrypt(encryptKey(key), req);
	}

	private static String md5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static void main(String[] args) throws Exception {
		String s = EncryptUtil.decryptReq(
				"9x8VcSXmw/y7mLChgtmrlClKYv0Wjigk0poNBpYEYMPMzRrlycrOIlo+7k0HA1MPR6QgjBr1MDLSKgnWzkrm+3F9/RYnPUds5LEH0iAhrs1hH9SfF/5V5hcpBaK+skUiKwZvK6oVqazdx33X03tGi2bkAEq/3M1W5WL51BLd4nM2raemCbQVAa/KZrTMg5TivY3IayzP8DITKLqRs+4So8nMNffQdlO2BcXFE1EbC23SXjW6MB+xT5LH7DEJn+ByUf7BNKO6L2tc0jXKeN4FcJW9cOQsnd0L6zCpa0Fmkm/amwWeO7xb0xGaobJ9I6mfg9Z73OoejfMNrJrlkznz8JuOMtraJLl/vaiGVooxhONAP0maiZov4iexn0Lfq4PLUHarfTCQgQCesUR3i68iTsDUB+I868uBFFso3U20eem7NfjeRqTA1WdfCWLinMKASQ6UWCnOrvHQ6IueOLCLaumoL6aLIu8G/dCFlgS7BKWa2ABUO6su1VDbvFI0lGhnqiGWLn+hF/xjalPXva5cxueaxn/aR5f3JFFq5KduuSeLgzQC+R8+7We6gXXtxIiBmYQVZjWuN8kbGVEd70J9tX3J2L3TykjQwLOHAykxkL9bY3OkxFPMvYNdqdW2QBHZOK1NmuWBbMMwke17mQECmn6Wp94tI7VrAYIkm8NmaNIKkoDhsXZSVmzdY0RBsXo9yhXG4V2yMhhcaYN2fi4a028fq3UHo6idZXKnNlNgkQrljDhYgAPRA9TpmTvVbJoxt1njsZ9PbkEJSYkq4jNvexJPRF49JWIKR60Sqxjk8RQjIUfGJ4kBqzt79863BYVThoLFRBV4EGWbNeQ70c/1JfNN0JXNf3BhUoogKxx9sCBzwH1nkCMl+6/pWRCv3IolvX4GQDe2ps79MN3tqqVZBaFjf4WNkL6Qscw90QAlvCn318s2QV4I1UVvxqPl8QFtQWXDSNgJE52BvSU6fcaxBS59Zqj2dgW0tYkpnRtQ2myjy/ER7QfjpB1N0L4xVYmhdbje8YiDOGC0Y+YfP2JWQ1wC8mYwW5lRYp9etBNRgY/p6LRXrO7VvbNZSbHTkz5uRra++YoQ/6hMfvPuCITY6uxu0HW+OJEFN+Z2YptvZsMextXJrOVRtCOFzzRajEUCQmgFEVvhbaw0vVY6gZRFwPGZkAeKHKtRUMa6d1g7hhie4iShvv9b08SQS9nyE+6MP4PrwSJi6zJQ2OrTjjyqzA==",
				"0e29226db10f4166821650693e546ce2");
		System.out.println(s);
	}

}
