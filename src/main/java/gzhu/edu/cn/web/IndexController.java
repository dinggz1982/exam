package gzhu.edu.cn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import gzhu.edu.cn.base.log.annotation.Log;

@Controller
public class IndexController {
	/**
	 * 主页入口
	 * @return
	 */
	@Log("考试系统主页")
	@RequestMapping(value={"","/","index"})
	public String index() {
		return "index";
	}
	
      
}
