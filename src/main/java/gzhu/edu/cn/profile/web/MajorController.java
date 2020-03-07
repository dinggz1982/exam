package gzhu.edu.cn.profile.web;

import java.sql.SQLException;
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
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.IMajorService;
import gzhu.edu.cn.profile.service.ISchoolService;

/**
 * 专业管理器
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/profile")
public class MajorController {
	
	@Autowired
	private ICollegeService collegeService;
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private IMajorService majorService;
	
	/**
	 * 专业index
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	@GetMapping("/major/index")
	public String index(Integer pageIndex,Integer pageSize,Model model) throws SQLException{
		List<School> schools = this.schoolService.findAll();
		model.addAttribute("schools", schools);
		return  "profile/major/index";	
	}
	
	/**
	 * 专业信息分页
	 * @param page
	 * @param limit
	 * @param schoolId
	 * @param name
	 * @return
	 */
	@GetMapping("/major/list.json")
	@ResponseBody
	public JsonData<Major> userList1(Integer page, Integer limit,Integer schoolId,String name,Integer school_id) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "";
		if(name!=null&&name!=""){
			hql = " name like '%"+name+"%' and";
		}
		if(school_id!=null&&school_id>0){
			hql = " school_id = "+school_id +" and";
		}
		if(hql.length()>0){
			hql = hql.substring(0, hql.length() - 4);
		}
		PageData<Major> pageData = this.majorService.getPageData(page, limit, hql);
		JsonData<Major> pageJson = new JsonData<Major>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("专业列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}

	/**
	 * 编辑或修改专业
	 * @param id
	 * @param name
	 * @param url
	 * @param description
	 * @return
	 */
	@PostMapping("/major/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, String name, int college_id,String description) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				Major major = new Major();
				major.setName(name);
				College college = new College();
				college.setId(college_id);
				major.setCollege(college);
				major.setDescription(description);
				this.majorService.save(major);
				map.put("msg", "保存成功");
			} else {
				Major major = new Major();
				major.setId(id);
				major.setName(name);
				College college = new College();
				college.setId(college_id);
				major.setCollege(college);
				major.setDescription(description);
				this.majorService.update(major);
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
	 * 软删除专业
	 * @param id
	 * @return
	 */
	@PostMapping("/major/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer majorId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = this.majorService.softDelete(majorId);
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
