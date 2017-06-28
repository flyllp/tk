package net.historm.tk.io.qrcode;

import java.io.IOException;

import com.google.zxing.WriterException;

public class QRCodeMain {

	public static void main(String[] args) {

		String content = "http://weixin.qq.com/q/025D5QZOzu8HT1k_Njxp1t";
		String logUri = "d://sfls.png";
		String outFileUri = "d://zx.jpg";
		int[] size = new int[] { 430, 430 };
		String format = "jpg";

		try {
			new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri, size);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

}
