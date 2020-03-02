package gzhu.edu.cn.system.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.log.annotation.Log;
/**
 * 用户退出登录处理器
 * @author dingguozhu
 *
 */
@Service
public class ExamLogoutHandler implements LogoutHandler{

	@Override
	@Log("退出登录")
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		
	}

}
