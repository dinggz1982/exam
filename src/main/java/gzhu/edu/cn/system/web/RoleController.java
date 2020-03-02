package gzhu.edu.cn.system.web;

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
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.service.IRoleService;

/**
 * 角色控制器
 * 
 * @author dingguozhu
 */
@Controller
@RequestMapping("/system")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	/**
	 * 角色首页
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/role", "/role/index", "/role/" })
	public String list(Model model) {
		return "system/role/index";
	}

	/**
	 * 返回角色json数据
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/role/list.json")
	@ResponseBody
	public JsonData<Role> roleList(Integer page, Integer limit, String name) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;

		String hql = "";
		if (name != null && name != "") {
			hql = "and name like '%" + name + "%'";
		}
		PageData<Role> pageData = this.roleService.getPageData(page, limit, hql);
		JsonData<Role> pageJson = new JsonData<Role>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("角色列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}

	/**
	 * 软删除角色
	 * 
	 * @param id
	 * @param parentId
	 * @param name
	 * @param url
	 * @param isMenu
	 * @param orderNumber
	 * @return
	 */
	@GetMapping("/role/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.roleService.softDelete(id);
			map.put("code", 200);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}

	/**
	 * 编辑角色
	 * 
	 * @param id
	 * @param name
	 * @param url
	 * @param description
	 * @return
	 */
	@PostMapping("/role/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, String name, String url, String description) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				Role role = new Role();
				role.setName(name);
				role.setDescription(description);
				this.roleService.save(role);
				map.put("msg", "保存成功");
			} else {
				// 修改
				Role role = new Role();
				role.setId(id);
				role.setName(name);
				role.setDescription(description);
				this.roleService.update(role);
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
	 * 根据角色id和资源id，更新角色对应的权限
	 * 
	 * @param roleId
	 * @param resourceIds
	 * @return
	 */
	@PostMapping("/role/updateAuth")
	@ResponseBody
	public Map<String, Object> updateAuth(Integer roleId, String resourceIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (resourceIds != null && resourceIds.length() > 0) {
				resourceIds = resourceIds.replace("[", "").replace("]", "");
				String[] rids = resourceIds.split(",");
				int[] ids = convIntArray(rids);
				this.roleService.updateAuth(roleId, ids);
			} else {
				this.roleService.updateAuth(roleId, null);
			}

			// this.roleService.updateAuth(roleId, resourceIds);
			map.put("code", 200);
			map.put("msg", "权限配置成功！");
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}

	/**
	 * 将字符串数组转成整型数组
	 * 
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
