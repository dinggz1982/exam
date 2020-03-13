package gzhu.edu.cn.profile.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.ISchoolService;

/**
 * 学院管理器
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/profile")
public class CollegeController {
	
	@Autowired
	private ICollegeService collegeService;
	@Autowired
	private ISchoolService schoolService;
	
	/**
	 * 学院index
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	@GetMapping("/college/index")
	public String list(Integer pageIndex,Integer pageSize,Model model) throws SQLException{
		List<School> schools = this.schoolService.findAll();
		model.addAttribute("schools", schools);
	return  "profile/college/index";	
	}
	
	/**
	 * 学院信息分页
	 * @param page
	 * @param limit
	 * @param schoolId
	 * @param name
	 * @return
	 */
	@GetMapping("/college/list.json")
	@ResponseBody
	public JsonData<College> userList1(Integer page, Integer limit,Integer schoolId,String name,Integer school_id) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "";
		if(name!=null&&name!=""){
			hql = " name like '%"+name+"%' and";
		}
		if(school_id!=null&&school_id>0){
			hql =hql +  " school_id = "+school_id +" and";
		}
		if(hql.length()>0){
			hql = hql.substring(0, hql.length() - 4);
		}
		PageData<College> pageData = this.collegeService.getPageData(page, limit, hql);
		JsonData<College> pageJson = new JsonData<College>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("学院列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}

	/**
	 * 编辑或修改学院
	 * @param id
	 * @param name
	 * @param url
	 * @param description
	 * @return
	 */
	@PostMapping("/college/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, String name, int school_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				College college = new College();
				college.setName(name);
				School school = new School();
				school.setId(school_id);
				college.setSchool(school);
				this.collegeService.save(college);
				map.put("msg", "保存成功");
			} else {
				College college = new College();
				college.setId(id);
				college.setName(name);
				School school = new School();
				school.setId(school_id);
				college.setSchool(school);
				this.collegeService.update(college);
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
	 * 软删除学院
	 * @param id
	 * @return
	 */
	@PostMapping("/college/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer collegeId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = this.collegeService.softDelete(collegeId);
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
	
	
	@PostMapping("/college/getCollegeBySchoolId/{school_id}")
	@ResponseBody
	public List<College> getCollegeBySchoolId(@PathVariable int school_id){
		return this.collegeService.find(" school.id=" + school_id);
	}
}
