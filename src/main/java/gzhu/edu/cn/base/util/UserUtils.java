package gzhu.edu.cn.base.util;

import java.io.File;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import gzhu.edu.cn.system.entity.User;

/**
 * 获取当前用户信息
 * 
 * @author dingguozhu
 *
 */
public class UserUtils {

	/**
	 * 利用spring security获取当前用户名
	 * 
	 * @return
	 */
	public static String getCurrentUserName() {
		String username = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
					.equals("anonymousUser")) {
				return null;
			} else {
				username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
						.getUsername();
			}
		}
		// String username =
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (username != null && username.length() > 0) {
			return username;
		} else {
			return null;
		}
	}

	/**
	 * 获取用户照片的上传地址
	 */
	public static String getUploadImgPath(String uploadFolder, User user) {
		String path = uploadFolder + user.getId() + "/images/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 获取用户上传作业附件地址
	 */
	public static String getUploadHomeworkPath(String uploadFolder, User user) {
		String path = uploadFolder + user.getId() + "/homework/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

}
