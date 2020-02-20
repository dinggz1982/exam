package gzhu.edu.cn.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**   
* Title: exam
* Description: 这个网络教育软件设计与开发项目    
* Copyright: Copyright (c) 2020
* 开发机构:广州大学-教育技术 
* 创建时间:2020年2月16日 下午7:03:42 
* @author:丁国柱
* @version 1.0
* @since 1.0 
**/
@Controller
public class IndexController {

	@GetMapping({"/","","/index"})
	public String index(Model model) {
		String test= "123";
		model.addAttribute("name", test);
		return "index";
	}
	
	
}
