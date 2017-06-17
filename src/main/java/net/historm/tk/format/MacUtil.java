package net.historm.tk.format;

/**
 * Mac地址相关工具类
 */
public class MacUtil {

	private static final long MAC_LONG_MAX = 281474976710655L;

	/**
	 * 格式化mac地址成标准格式，便于代码处理和储存，只有数字+小写英文，如：00661f78ae12
	 * 
	 * @param mac 原mac，可以是带冒号的，可以是大小写混合的，也可是标准格式
	 * @return 标准格式mac，如果格式错误则返回null
	 */
	public static String formatStandard(String mac) {
		String macStr = mac.replaceAll("\\:", "");
		macStr = macStr.toLowerCase();
		String regx = "[a-f0-9]{12}";
		if (macStr.matches(regx)) {
			return macStr;
		}
		return null;
	}

	/**
	 * 格式化mac地址成日常格式，便于展示，只有数字冒号和大写英文，如：00:66:1F:78:AE:12
	 * 
	 * @param mac 原mac，可以是带冒号的，可以是大小写混合的，也可是标准格式
	 * @return 日常格式mac，如果格式错误则返回null
	 */
	public static String formatNormal(String mac) {
		String macStr = formatStandard(mac);
		if (macStr == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(1024);
		for (int i = 0; i < 12; i += 2) {
			String ss = macStr.substring(i, i + 2);
			sb.append(ss);
			sb.append(":");
		}
		String result = sb.toString();
		result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * Mac转换成数字Long
	 * 
	 * @param macStr 原mac地址
	 * @return mac转换成的long型数字，如果格式错误则为-1
	 */
	public static long macToLong(String macStr) {
		String mac = formatStandard(macStr);
		if (mac == null) {
			return -1L;
		}
		return Long.valueOf(mac, 16);
	}

	/**
	 * 数字Long转换成Mac
	 * 
	 * @param macLong 数字格式（Long）的Mac
	 * @return 转换后的Mac（标准格式），如果格式错误返回null
	 */
	public static String LongToMac(long macLong) {
		if (macLong < 0 || macLong > MAC_LONG_MAX) {
			return null;
		}
		return String.format("%012x", macLong);
	}

}
