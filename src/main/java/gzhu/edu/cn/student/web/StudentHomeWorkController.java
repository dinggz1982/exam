package gzhu.edu.cn.student.web;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.base.util.UserUtils;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.homework.service.impl.HomeWorkService;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: exam
 * @description: 作业控制器
 * @author: 丁国柱
 * @create: 2020-03-27 16:19
 */
@Controller
@RequestMapping("/student")
public class StudentHomeWorkController {
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IMyHomeWorkService myhomeworkService;
	@Autowired
	private HomeWorkService homeworkService;

    @Autowired
    private HttpSession session;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @GetMapping("/myhomework/index")
	public String list(Integer pageIndex,Integer pageSize,Model model) throws SQLException{
		List<Course> courses = this.courseService.findAll();
		model.addAttribute("courses", courses);
	return  "student/myhomework/index";	
	}
	
	/**
	 * 作业信息分页
	 */
	@GetMapping("/myhomework/list.json")
	@ResponseBody
	public JsonData<MyHomeWork> userList1(Integer page, Integer limit,String title,Integer course_id,Integer creator_id) {
		page = page == null ? 1 : page < 1 ? 1 : page;
		limit = limit == null ? 10 : limit < 1 ? 1 : limit;
		
		String hql = "select h.title,h.content,h.";
		if(title!=null&&title!=""){
			hql = " title like '%"+title+"%' and";
		}
		if(course_id!=null&&course_id>0){
			hql =hql +  " course_id = "+course_id +" and";
		}
		if(creator_id!=null&&creator_id>0){
			hql =hql +  " creator_id = "+creator_id +" and";
		}
		if(hql.length()>0){
			hql = hql.substring(0, hql.length() - 4);
		}
		PageData<MyHomeWork> pageData = this.myhomeworkService.getPageData(page, limit, hql);
		JsonData<MyHomeWork> pageJson = new JsonData<MyHomeWork>();
		pageJson.setCode(0);
		pageJson.setCount(pageData.getTotalCount());
		pageJson.setMsg("作业列表");
		pageJson.setData(pageData.getPageData());
		return pageJson;
	}

	
    /**
     * 上传作业附件，保存作业
     * @return
     */
    @PostMapping("/teacher/homework/upload")
    @ResponseBody
    public Map<String, Object> updateImg(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(multipartFile!=null&&!multipartFile.isEmpty()){
            //保存当前用户（教师）的作业
            User user = (User) session.getAttribute("currentUser");
            String fileName = multipartFile.getOriginalFilename();
            String homeWorkFile = UserUtils.getUploadHomeworkPath(uploadFolder, user) + fileName;
            File newfile = new File(homeWorkFile);
            try {
                //保存作业附件
                multipartFile.transferTo(newfile);
                map.put("result","success");//文件上传上传
                //存在数据库中的附件地址
                String savePath = staticAccessPath + user.getId()+"/homework/"+fileName;
                map.put("filePath",savePath);
            } catch (IOException e) {
                map.put("result","error");//文件上传上传
            }
        }
        return map;
    }


    /**
     * 教师新增或修改作业
     * @param id
     * @param title
     * @param homeworkContent
     * @param files
     * @return
     */
    @PostMapping("/homework/edit")
    @ResponseBody
    public Map<String, Object> edit(Integer id, String title, String homeworkContent, String[] files) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (id == null) {
                System.out.println("id="+id);
                System.out.println("title="+title);
                System.out.println("homeworkContent="+homeworkContent);
                System.out.println("files="+files);
                map.put("msg", "保存成功");
            } else {
                // 修改
                System.out.println("title="+title);
                System.out.println("homeworkContent="+homeworkContent);
                System.out.println("files="+files);
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
	 * 软删除作业
	 * @param id
	 * @return
	 */
	@PostMapping("/homework/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer homeworkId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = this.homeworkService.softDelete(homeworkId);
			if(result==1){
				map.put("msg", "删除成功！");
			}else{
				map.put("msg", "未能正常删除！");
			}
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 200);
			map.put("msg", "出现错误：" + e);
		}
		return map;
	}
	
	/**
	 * 根据课程id获取作业信息
	 * @param homework_id
	 * @return
	 */
	@PostMapping("/homework/getHomeworkByCourseId/{course_id}")
	@ResponseBody
	public List<HomeWork> getHomeworkByCourseId(@PathVariable int course_id){
		return this.homeworkService.find(" course.id=" + course_id);
	}


}