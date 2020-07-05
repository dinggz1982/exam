package gzhu.edu.cn.system.web;

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
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.service.IResourceService;

@Controller
@RequestMapping("/system")
public class ResourceController {

	@Autowired
	private IResourceService resourceService;

	@GetMapping({ "/resource", "/resource/index", "/resource/" })
	public String index(Model model) throws SQLException {
		List<Resource> resources = this.resourceService.findAll();
		model.addAttribute("resources", resources);
		return "system/resourse/index";
	}

	/**
	 * 返回菜单信息，为了显示方便，将菜单全部信息打包
	 * 
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/resource.json")
	@ResponseBody
	public JsonData<Resource> list(String name) throws SQLException {
		JsonData<Resource> jsonData = new JsonData<Resource>();
		if (name == null || name.equals("")) {
			// 先取得菜单总数
			int count = this.resourceService.getCountBySql("select count(*) from sys_resource where delFlag=0 ");
			jsonData.setCode(0);
			jsonData.setCount(count);
			jsonData.setMsg("菜单信息");
			List<Resource> resources = this.resourceService.findAll();
			jsonData.setData(resources);
		} else {
			int count = this.resourceService
					.getCountBySql("select count(*) from sys_resource where delFlag=0 and name like '%" + name + "%' ");
			jsonData.setCode(0);
			jsonData.setCount(count);
			jsonData.setMsg("菜单信息");
			List<Resource> resources = this.resourceService.find(" name like '%" + name + "%' ");
			jsonData.setData(resources);
		}
		return jsonData;
	}

	/**
	 * 编辑菜单资源
	 * @param id
	 * @param parentId
	 * @param name
	 * @param url
	 * @param isMenu
	 * @param orderNumber
	 * @return
	 */
	@PostMapping("/resource/edit")
	@ResponseBody
	public Map<String, Object> edit(Integer id, Integer parentId, String name, String url, boolean isMenu,
			int orderNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				Resource resource = new Resource();
				resource.setMenu(isMenu);
				resource.setOrderNumber(orderNumber);
				resource.setUrl(url);
				resource.setName(name);
				if (parentId != null) {
					Resource parent = new Resource();
					parent.setId(parentId);
					resource.setParent(parent);
				}
				this.resourceService.save(resource);
				map.put("msg", "保存成功");
			} else {
				// 修改
				Resource resource = new Resource();
				resource.setId(id);
				resource.setMenu(isMenu);
				resource.setOrderNumber(orderNumber);
				resource.setUrl(url);
				resource.setName(name);
				if (parentId != null) {
					Resource parent = new Resource();
					parent.setId(parentId);
					resource.setParent(parent);
				}
				this.resourceService.update(resource);
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
	 * 软删除菜单
	 * @param id
	 * @return
	 */
	@GetMapping("/resource/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.resourceService.softDelete(id);
			map.put("code", 200);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}

	/**
	 * 根据角色id返回权限树
	 * @param roleId
	 * @return
	 */
	@GetMapping("/resource/tree")
	@ResponseBody
	public String getResourceByRoleId(Integer roleId){
		return this.resourceService.getAuthTreeByRoleId(roleId);
	}
	
	
}
