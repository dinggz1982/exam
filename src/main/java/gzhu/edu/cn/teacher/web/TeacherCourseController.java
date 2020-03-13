package gzhu.edu.cn.teacher.web;

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
 * @author : 丁国柱
 * @email : dinggz@gzhu.edu.cn
 * @version : 2020年3月10日 下午10:31:31
 * @description : 教师端--我的课程
 */
@Controller
@RequestMapping("/teacher")
public class TeacherCourseController {

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
	 * 
	 * @Description
	 * @author 丁国柱
	 * @date 2020年3月10日 下午10:34:35
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/course/index")
	public String index(Model model) throws SQLException {
		// 获取到课元
		List<Subject> subjects = this.SubjectService.findAll();
		List<StudyTeam> studyTeams = this.studyTeamService.findAll();

		model.addAttribute("subjects", subjects);
		model.addAttribute("studyTeams", studyTeams);

		return "teacher/course/index";
	}

	/**
	 * 专业信息分页
	 * 
	 * @param page
	 * @param limit
	 * @param schoolId
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
	 * 编辑或修改教师的课程
	 * 
	 * @param id
	 * @param name
	 * @param url
	 * @param description
	 * @return
	 */
	@PostMapping("/course/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, String classHour, String classInfoIds, String name, int subject_id,
			int study_team_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				Course course = new Course();
				course.setName(name);
				// 学期
				StudyTeam studyTeam = new StudyTeam();
				studyTeam.setId(study_team_id);
				course.setStudyTeam(studyTeam);
				// 课元
				Subject subject = new Subject();
				subject.setId(subject_id);
				// 授课老师
				User user = (User) session.getAttribute("currentUser");
				course.setTeacher(user);

				// 拿到班级信息
				if (classInfoIds != null && classInfoIds.length() > 0) {
					classInfoIds = classInfoIds.replace("[", "").replace("]", "");
					String[] cids = classInfoIds.split(",");
					Set<ClassInfo> classInfos = new HashSet<ClassInfo>();
					for (int i = 0; i < cids.length; i++) {
						if (cids[i].indexOf("classinfo_") != -1) {
							ClassInfo classInfo = new ClassInfo();
							classInfo.setId(Integer.parseInt(cids[i].replace("classinfo_", "").replace("\"", "")));
							classInfos.add(classInfo);
						}
					}
					course.setClassInfos(classInfos);
				}
				this.courseService.save(course);
				map.put("msg", "保存成功");
			} else {

			}
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
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
