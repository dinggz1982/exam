package gzhu.edu.cn.profile.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.profile.service.IClassInfoService;
import gzhu.edu.cn.system.service.IResourceButtonService;
import gzhu.edu.cn.system.service.IResourceService;
import gzhu.edu.cn.system.service.IUserService;

/**
 * 班级管理
 * @author dingguozhu
 * @date 2020年3月11日 上午4:11:05
 * @description
 */
@Controller
@RequestMapping("/profile")
public class ClassInfoController {
	
	@Autowired
	private IClassInfoService classInfoService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceButtonService resourceButtonService;
	@Autowired
	private IResourceService resourceService;
	
	/**
	 * 列出班级信息
	 * @return
	 */
	@GetMapping("/classInfo/index")
	public String index(Integer pageIndex,Integer pageSize,Model model){
		return "profile/classinfo/index";
	}
	
	@PostMapping("/classInfo/classTree")
	@ResponseBody
	public Map<String, Object> edit(Integer course_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				map.put("msg", "修改成功");
				map.put("code", 200);
				map.put("data", this.classInfoService.getClassTreeByCourseId(course_id));
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}
	

}
