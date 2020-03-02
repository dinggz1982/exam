package gzhu.edu.cn.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	/**
	 * 访问被拒绝
	 * @return
	 */
	@GetMapping("/accessDenied")
	public String accessDenied(){
		
		return "error/accessDenied";
	}
	
	/*@GetMapping("/error")
	public String error(Exception e,String msg){
		switch (msg) {
		case "404":
			
			break;
		case "500":
			
			break;

		default:
			break;
		}
		return "error/error";
	}*/
}
