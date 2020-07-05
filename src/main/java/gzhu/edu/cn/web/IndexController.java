package gzhu.edu.cn.web;

import gzhu.edu.cn.base.util.UserUtils;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gzhu.edu.cn.base.log.annotation.Log;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IMyHomeWorkService myHomeWorkService;
	/**
	 * 主页入口
	 * @return
	 */
	@Log("考试系统主页")
	@RequestMapping(value={"","/","index"})
	public String index() {
		return "index";
	}


	/**
	 * 主界面
	 * @return
	 */
	@GetMapping("/main")
	public String main(Model model){
		User user = (User) session.getAttribute("currentUser");
		if(UserUtils.isTeacherOrAdmim(user)){
			List<Course>  courses = this.courseService.find(" teacher_id=" + user.getId());
			model.addAttribute("courses",courses);
			return "system/teacher";
		}
		else{
			return "system/student";
		}

	}
      
}
