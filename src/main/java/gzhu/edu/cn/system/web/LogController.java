package gzhu.edu.cn.system.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gzhu.edu.cn.base.log.entity.LogInfo;
import gzhu.edu.cn.base.log.service.ILogInfoService;
import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
/**
 * 日志记录
 * @return
 */
@Controller
@RequestMapping("/system")
public class LogController {
	
	@Resource
	private ILogInfoService logInfoService;

	
	@GetMapping("/log/index")
	public String index(){
		
		return "system/log/index";
	}
	
	/**
	 * 返回用户json数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/log/list.json")
	@ResponseBody
	public JsonData<LogInfo> userList1(Integer page, Integer limit,String username,String operation) {
		String hql = "";
		if(username!=null&&username.length()>0){
			hql = " username='"+username+"' and";
		}
		if(operation!=null&&operation.length()>0){
			hql = " operation='"+operation+"' and";
		}
		if(hql.length()>0){
			hql = hql.substring(0, hql.length() - 4);
		}
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		PageData<LogInfo> pageData = this.logInfoService.getPageData(page, limit, hql);
		JsonData<LogInfo> pageJson = new JsonData<LogInfo>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("日志列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}
	
}
