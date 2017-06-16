package net.historm.tk.io;

import java.io.*;

import org.apache.log4j.Logger;

/**
 * 简单文件工具类，适合小文件的全量读取和写入
 */
public class SimpleFileUtil {

	private final static Logger logger = Logger.getLogger(SimpleFileUtil.class);

	/**
	 * 写文件
	 * 
	 * @param fileName 文件名（包含路径）
	 * @param content 文件内容
	 * @return 是否写入成功
	 */
	public static boolean writeFile(String fileName, String content) {
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(fileName));
			out.write(content);
			out.flush();
		} catch (IOException e) {
			logger.error("write file error. ", e);
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("close io out error. ", e);
				}
			}
		}
		return true;
	}

	/**
	 * 读文件
	 * 
	 * @param fileName 文件名（包含路径）
	 * @return 文件内容（失败为null）
	 */
	public static String readFile(String fileName) {
		StringBuilder sb = new StringBuilder(10240);
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String str = null;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
		} catch (IOException e) {
			logger.error("read file error. ", e);
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("close io reader error. ", e);
				}
			}
		}
		return sb.toString();
	}
}
