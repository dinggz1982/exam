package gzhu.edu.cn.profile.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.system.entity.User;
/**
 * 课程web
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private ICourseService courseService;

	@Autowired
	private HttpSession session;

	/**
	 * 教师的课程列表
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		
		//取得当前用户
		User currentUser=  (User) session.getAttribute("currentUser");
		
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Course> pageData = this.courseService.getPageData(pageIndex, pageSize, " and teacher_id="+currentUser.getId());
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		
		return "homework/list";
	}
	
	@GetMapping("/add")
	public String addCourse(){
		
		return "homework/add";
	}
	
	
}
