package net.historm.tk.encode;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	public static final String VIPARA = "0102030405060708";
	public static final String bm = "UTF-8";

	public static String encrypt(String dataPassword, String cleartext) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));

		return Base64.encode(encryptedData);
	}

	public static String decrypt(String dataPassword, String encrypted) throws Exception {
		byte[] byteMi = Base64.decode(encrypted);
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] decryptedData = cipher.doFinal(byteMi);

		return new String(decryptedData, bm);
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(encrypt("8d4015b985df407591da8e5e4174d026",
		// "{\"mac_wired\": \"aabbccddee\",\"mac_wireless\":
		// \"eeddccbbaa\",\"aid\": \"qqwweerrttyyuuii\",\"app_version\":
		// \"vs1\",\"version\": \"v1\","
		// + "\"chipset\": \"H3798c\",\"pid\": \"A5\",\"os\": \"android\"}"));
		// System.out.println(decrypt("1234567890123456",
		// "VWmwCjmmKsZZOfo/f7NwMw=="));
		System.out.println(UUID.randomUUID().toString());
	}

}
