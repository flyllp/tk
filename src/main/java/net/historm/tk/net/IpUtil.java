package net.historm.tk.net;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

	public static String getRemoteHost(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String result = ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		if (result != null && result.length() > 15 && result.contains(",")) {
			String[] ips = result.split(",");
			return ips[0];
		}
		return result;
	}
}
