package gzhu.edu.cn.profile.web;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.entity.Major;
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.entity.Subject;
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.IMajorService;
import gzhu.edu.cn.profile.service.ISchoolService;
import gzhu.edu.cn.profile.service.ISubjectService;

/**
 * 课元信息处理
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/profile")
public class SubjectController {
	
	@Autowired
	private ICollegeService collegeService;
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private IMajorService majorService;
	
	@Autowired
	private ISubjectService subjectService;
	
	/**
	 * 课元index
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	@GetMapping("/subject/index")
	public String index(Integer pageIndex,Integer pageSize,Model model) throws SQLException{
		List<Major> majors = this.majorService.findAll();
		model.addAttribute("majors", majors);
		return  "profile/subject/index";	
	}
	
	/**
	 * 课元信息分页
	 * @param page
	 * @param limit
	 * @param schoolId
	 * @param name
	 * @return
	 */
	@GetMapping("/subject/list.json")
	@ResponseBody
	public JsonData<Subject> userList1(Integer page, Integer limit,Integer major_id,String name) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "";
		if(name!=null&&name!=""){
			hql = " name like '%"+name+"%' and";
		}
		if(major_id!=null&&major_id>0){
			hql = hql + " major_id = "+major_id +" and";
		}
		if(hql.length()>0){
			hql = hql.substring(0, hql.length() - 4);
		}
		PageData<Subject> pageData = this.subjectService.getPageData(page, limit, hql);
		JsonData<Subject> pageJson = new JsonData<Subject>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("课元列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}

	/**
	 * 编辑或修改课元
	 * @param id
	 * @param name
	 * @param url
	 * @param description
	 * @return
	 */
	@PostMapping("/subject/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, String name, int major_id,String description) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				Major major = new Major();
				major.setId(major_id);
				Subject subject = new Subject();
				subject.setCode("");
				subject.setCreateTime(new Date());
				subject.setDescription(description);
				subject.setMajor(major);
				subject.setName(name);
				this.subjectService.save(subject);
				map.put("msg", "保存成功");
			} else {
				Major major = new Major();
				major.setId(major_id);
				Subject subject = new Subject();
				subject.setId(id);
				subject.setCode("");
				subject.setCreateTime(new Date());
				subject.setDescription(description);
				subject.setMajor(major);
				subject.setName(name);
				this.subjectService.save(subject);
				map.put("msg", "修改成功");
			}
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}
	
	/**
	 * 软删除课元
	 * @param id
	 * @return
	 */
	@PostMapping("/subject/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer subjectId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = this.subjectService.softDelete(subjectId);
			if(result==1){
				map.put("msg", "删除成功！");
			}else{
				map.put("msg", "未能正常删除！");
			}
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}
	
}
