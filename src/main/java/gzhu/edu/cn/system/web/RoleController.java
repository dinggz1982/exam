package gzhu.edu.cn.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.service.IRoleService;
/**
 * 角色控制器
 * @author dingguozhu
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;

	@GetMapping("/system/role")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		return "system/role/list";
	}
	
	/**
	 * 返回角色json数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/role/list.json")
	@ResponseBody
	public JsonData<Role> roleList(Integer page, Integer limit,String name) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "";
		if(name!=null&&name!=""){
			hql = "and name like '%"+name+"%'";
		}
		PageData<Role> pageData = this.roleService.getPageData(page, limit, hql);
		JsonData<Role> pageJson = new JsonData<Role>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("用户列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}

}
