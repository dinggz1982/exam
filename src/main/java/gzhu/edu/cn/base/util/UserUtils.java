package gzhu.edu.cn.base.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前用户信息
 * @author dingguozhu
 *
 */
public class UserUtils {
	
	public static String getCurrentUserName(){
		String username=null;
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			 username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		}
		//String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(username!=null&&username.length()>0){
			return username;
		}else{
			return null;
		}
	}
	

}
