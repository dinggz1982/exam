package gzhu.edu.cn.student.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.entity.StudyTeam;
import gzhu.edu.cn.profile.entity.Subject;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.profile.service.IStudyTeamService;
import gzhu.edu.cn.profile.service.ISubjectService;
import gzhu.edu.cn.system.entity.User;

/**
 * 学生端--我的课程
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/student")
public class StudentCourseController {

	@Autowired
	private ICourseService courseService;

	@Autowired
	private ISubjectService SubjectService;

	@Autowired
	private IStudyTeamService studyTeamService;

	@Autowired
	private HttpSession session;

	/**
	 * 我的课程
	 */
	@GetMapping("/course/index")
	public String index(Model model) throws SQLException {
		// 获取到课元
		List<Subject> subjects = this.SubjectService.findAll();
		List<StudyTeam> studyTeams = this.studyTeamService.findAll();

		model.addAttribute("subjects", subjects);
		model.addAttribute("studyTeams", studyTeams);

		return "student/course/index";
	}

	/**
	 * 专业信息分页
	 * 
	 * @param page
	 * @param limit
	 * @param
	 * @param name
	 * @return
	 */
	@GetMapping("/course/list.json")
	@ResponseBody
	public JsonData<Course> userList1(Integer page, Integer limit, Integer team_id, String name) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;

		String hql = "";
		if (name != null && name != "") {
			hql = " name like '%" + name + "%' and";
		}
		if (team_id != null && team_id > 0) {
			hql = hql + " studyTeam.id = " + team_id + " and";
		}
		if (hql.length() > 0) {
			hql = hql.substring(0, hql.length() - 4);
		}
		PageData<Course> pageData = this.courseService.getPageData(page, limit, hql);
		JsonData<Course> pageJson = new JsonData<Course>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("教师课程列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}


	/**
	 * 将字符串数组转成整型数组
	 * 
	 * @author dingguozhu
	 * @param as
	 * @return
	 */
	public static int[] convIntArray(String[] as) {
		if (as == null) {
			return new int[0];
		}
		int[] ia = new int[as.length];
		for (int i = 0; i < as.length; i++) {
			ia[i] = Integer.parseInt(as[i]);
		}
		return ia;
	}

}
