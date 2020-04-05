package gzhu.edu.cn.user.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.util.UserUtils;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IUserService;

/**
 * @author : 丁国柱
 * @email : dinggz@gzhu.edu.cn
 * @version : 2020年3月8日 下午6:57:51
 * @description : 用户信息维护
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Value("${file.uploadFolder}")
	private String uploadFolder;

	@Value("${file.staticAccessPath}")
	private String staticAccessPath;

	

	@Autowired
	private IUserService userService;

	@Autowired
	private HttpSession session;

	@GetMapping("/userInfo")
	public String userInfo(Model model) {
		User user = (User) session.getAttribute("currentUser");
		model.addAttribute("currentUser", user);
		return "user/userInfo";
	}

	/**
	 * 上传用户的头像
	 * 
	 * @param res
	 * @return
	 */
	@PostMapping("/uploadImg")
	@ResponseBody
	public Map<String, Object> updateImg(String res) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("code", 200);
		try {
			String uuid = UUID.randomUUID().toString();
			User user = (User) session.getAttribute("currentUser");
			
			String userImgPath = UserUtils.getUploadImgPath(uploadFolder, user) + uuid + ".png";
			// 上传头像
			getImageFromBase64(res, userImgPath);
			// 更新用户头像
			user.setImg(staticAccessPath + user.getId()+"/images/"+uuid+".png");
			this.userService.update(user);
			map.put("msg", "保存成功");

		} catch (Exception e) {
			map.put("msg", "出错了！");

		}
		return map;
	}

	public void getImageFromBase64(String data, String fileName) {
		/**
		 * 1.获取到后面到数据
		 */
		String base64Data = data.split(",")[1];
		/**
		 * 2.解码成字节数组
		 */
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(base64Data);
		/**
		 * 3.字节流转文件
		 */
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
