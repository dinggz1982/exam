package gzhu.edu.cn.base.util;

import java.io.File;
import java.util.Set;

import gzhu.edu.cn.system.entity.Role;
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

	/**
	 * 获取作业用户上传的图片
	 * @param uploadFolder
	 * @param user
	 * @return
	 */
	public static String getUploadHomeworkImagePath(String uploadFolder, User user) {
		String path = uploadFolder + user.getId() + "/homework/images/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 判断用户是管理员或教师
	 * @param user
	 * @return
	 */
	public static boolean isTeacherOrAdmim(User user){
		Set<Role> roles = user.getRoles();
		for (Role role: roles
			 ) {
			if(role.getName().equals("教师")||role.getName().equals("管理员")){
				return true;
			}
		}
		return false;
	}


}
