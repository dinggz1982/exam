package gzhu.edu.cn.profile.web;

import java.util.Date;
import java.util.HashMap;
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
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.service.ISchoolService;

/**
 * 学校控制器
 * @author dingguozhu
 * @Controller
 *
 */
@Controller
@RequestMapping("/profile")
public class SchoolController {
	
	@Autowired
	private ISchoolService schoolService;
	
	/**
	 * 学校index
	 * @return
	 */
	@GetMapping("/school/index")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		return "profile/school/index";
	}
	
	/**
	 * 学校分页信息
	 * @param page
	 * @param limit
	 * @param sex
	 * @param username
	 * @param realname
	 * @return
	 */
	@GetMapping("/school/list.json")
	@ResponseBody
	public JsonData<School> userList1(Integer page, Integer limit,String name) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "";
		if(name!=null&&name!=""){
			hql = " name like '%"+name+"%' ";
		}
		PageData<School> pageData = this.schoolService.getPageData(page, limit, hql);
		JsonData<School> pageJson = new JsonData<School>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("学校列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}
	
	/**
	 * 编辑或修改学校信息
	 * @param id
	 * @param name
	 * @param address
	 * @return
	 */
	@PostMapping("/school/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, String name, String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				School school = new School();
				school.setName(name);
				school.setAddress(address);
				school.setCreateTime(new Date());
				this.schoolService.save(school);
				map.put("msg", "保存成功");
			} else {
				School school = new School();
				school.setId(id);
				school.setName(name);
				school.setAddress(address);
				school.setCreateTime(new Date());
				this.schoolService.update(school);
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
	 * 软删除学校
	 * @param id
	 * @return
	 */
	@PostMapping("/school/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer schoolId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = this.schoolService.softDelete(schoolId);
			if(result>=1){
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
	
	
	/**
	 * 新增学校
	 * @return
	 */
	@GetMapping("/school/add")
	public String add(){
		return "school/add";
	}
	
	/**
	 * 保存学校
	 * @return
	 */
	@PostMapping("/school/save")
	public String save(School school){
		this.schoolService.save(school);
		return "school/saveSuccess";
	}

}
