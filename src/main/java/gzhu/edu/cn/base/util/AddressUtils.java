package gzhu.edu.cn.base.util;

import javax.servlet.http.HttpServletRequest;

public class AddressUtils {

	/**
	 * 请求的ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取请求地址
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request){
		return request.getRequestURL().toString();
	}
	
	

}
