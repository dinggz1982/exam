package gzhu.edu.cn.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.service.IResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private IResourceService resourceService;

	@GetMapping("/list")
	public String list(Integer pageIndex,Integer pageSize,Model model){
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Resource> pageData = this.resourceService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "system/resource/list";
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public String tree(){
		return this.resourceService.getResourceTree(null);
	}
	

}
