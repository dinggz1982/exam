package gzhu.edu.cn.system.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import gzhu.edu.cn.base.log.annotation.Log;
import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.base.util.UploadUserUtils;
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.ResourceButton;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IResourceButtonService;
import gzhu.edu.cn.system.service.IResourceService;
import gzhu.edu.cn.system.service.IRoleService;
import gzhu.edu.cn.system.service.IUserService;

@Controller
@RequestMapping("/system")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceButtonService resourceButtonService;
	@Autowired
	private IResourceService resourceService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 通过文件导入用户
	 * 
	 * @return
	 */
	@GetMapping("/addFromFile")
	public String addFromFile() {
		return "system/user/addFromFile";
	}

	/**
	 * 用户列表
	 * <p>
	 * 方法名:list
	 * </p>
	 * <p>
	 * Description :
	 * </p>
	 * <p>
	 * Company :
	 * </p>
	 * 
	 * @author 丁国柱
	 * @date 2018年1月30日 下午11:15:01
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	@Log("用户列表页面")
	@GetMapping("/user/index")
	public String list(Model model) throws SQLException {
		/*pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<User> pageData = this.userService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);*/

		// 取得用户的按钮权限
	//	User currentUser = (User) session.getAttribute("currentUser");
		
		//给出角色列表
		List<Role> roles = this.roleService.findAll();
		model.addAttribute("roles", roles);

	/*	//System.out.println(request.getRequestURI());
		String url = request.getRequestURI();
		Resource resource = resourceService.getResourceByURL(url);
		Set<ResourceButton> buttons = this.resourceButtonService.getResourceButtonByUserId(resource, currentUser);
		model.addAttribute("buttons", buttons);*/

		return "system/user/index";
	}

	/**
	 * 返回用户json数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/user/list.json")
	@ResponseBody
	public JsonData<User> userList1(Integer page, Integer limit,String sex,String username,String realname) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "";
		if(username!=null&&username!=""){
			hql = " username like '%"+username+"%' and";
		}
		if(realname!=null&&realname!=""){
			hql = hql +  " realname like '%"+realname+"%' and";
		}
		if(sex!=null&&sex!=""){
			hql = hql + " sex = '" + sex+"' and";
		}
		if(hql.length()>0){
			hql = hql.substring(0, hql.length() - 4);
		}
		PageData<User> pageData = this.userService.getPageData(page, limit, hql);
		JsonData<User> pageJson = new JsonData<User>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("用户列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}
	/**
	 * 编辑或修改用户
	 * @param id
	 * @param name
	 * @param url
	 * @param description
	 * @return
	 */
	@PostMapping("/user/edit")
	@ResponseBody
	public Map<String, Object> edit(Long id, String username, String realname, String sex,Integer[] roleIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id == null) {
				// 新增
				User user = new User();
				user.setUsername(username);
				user.setRealname(realname);
				user.setCreateTime(new Date());
				user.setSex(sex);
				Set<Role> roles = new HashSet<Role>();
				if(roleIds!=null){
					for (int i = 0; i < roleIds.length; i++) {
						Role role = new Role();
						role.setId(roleIds[i]);
						roles.add(role);
					}
				}
				user.setRoles(roles);
				this.userService.save(user);
				map.put("msg", "保存成功");
			} else {
				//用户修改后期补上
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
	 * 实现文件上传
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@PostMapping("/user/saveUserFromFile")
	public String saveUserFromFile(@RequestParam("file") MultipartFile file, Model model)
			throws IllegalStateException, IOException {

		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/WEB-INF/uploads/");
			// 上传文件名
			String filename = file.getOriginalFilename();
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator + filename));
			// 输出文件上传最终的路径 测试查看
			List<User> users = UploadUserUtils.getUsers(path + File.separator + filename,"/t");
			List<User> newUsers = new ArrayList<User>();
			List<User> exitsUser = new ArrayList<User>();

			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				User user1 = this.userService.findByName(user.getUsername());
				if(user1==null){
					newUsers.add(user);
				}else{
					exitsUser.add(user1);
				}
			}
			this.userService.batchSave(newUsers);
			model.addAttribute("newUsers", newUsers);
			model.addAttribute("exitsUser", exitsUser);
		}

		return "system/user/uploadSuccess";
	}

}
