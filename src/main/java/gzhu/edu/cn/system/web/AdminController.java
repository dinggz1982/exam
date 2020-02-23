package gzhu.edu.cn.system.web;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IResourceService;

@Controller
public class AdminController  {
	
	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/system/index")
	public String admin(Model model){
		User currentUser=  (User) session.getAttribute("currentUser");
		Set<Resource> resources =  resourceService.getMenuByUser(currentUser);
		model.addAttribute("resources", resources);
		return "system/index";
	}
	
	@GetMapping("/admin/userList")
	public String userList(){
		
		return "system/user/userList";
	}

}
