package gzhu.edu.cn.teacher.web;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.base.util.UserUtils;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.knowledge.entity.Knowledge;
import gzhu.edu.cn.knowledge.entity.MyKnowledgeGraph;
import gzhu.edu.cn.knowledge.service.IMyKnowledgeGraphService;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.system.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: exam
 * @description: 作业控制器
 * @author: 丁国柱
 * @create: 2020-03-27 16:19
 */
@Controller
@RequestMapping("/teacher")
public class TeacherHomeWorkController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IHomeWorkService homeworkService;
    @Autowired
    private HttpSession session;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Autowired
    private IMyHomeWorkService myHomeWorkService;

    @Autowired
    private IMyKnowledgeGraphService myKnowledgeGraphService;

    @Autowired
    private HttpServletRequest request;
    //    @GetMapping("/its/homework/index")
//    public String index(){
//
//        return "homework/index";
//    }

    private final static Logger logger = LoggerFactory.getLogger(TeacherHomeWorkController.class);


    @GetMapping("/homework/index")
    public String list(Integer pageIndex, Integer pageSize, Model model) throws SQLException {
        List<Course> courses = this.courseService.findAll();
        model.addAttribute("courses", courses);
        return "teacher/homework/index";
    }

    /**
     * 作业信息分页
     */
    @GetMapping("/homework/list.json")
    @ResponseBody
    public JsonData<HomeWork> userList1(Integer page, Integer limit, String title, Integer course_id, Integer creator_id) {
        page = page == null ? 1 : page < 1 ? 1 : page;
        limit = limit == null ? 10 : limit < 1 ? 1 : limit;

        String hql = "";
        if (title != null && title != "") {
            hql = " title like '%" + title + "%' and";
        }
        if (course_id != null && course_id > 0) {
            hql = hql + " course_id = " + course_id + " and";
        }
        if (creator_id != null && creator_id > 0) {
            hql = hql + " creator_id = " + creator_id + " and";
        }
        if (hql.length() > 0) {
            hql = hql.substring(0, hql.length() - 4);
        }
        PageData<HomeWork> pageData = this.homeworkService.getPageData(page, limit, hql);
        JsonData<HomeWork> pageJson = new JsonData<HomeWork>();
        pageJson.setCode(0);
        pageJson.setCount(pageData.getTotalCount());
        pageJson.setMsg("作业列表");
        pageJson.setData(pageData.getPageData());
        return pageJson;
    }


    /**
     * 上传作业附件，保存作业
     *
     * @return
     */
    @PostMapping("/homework/upload")
    @ResponseBody
    public Map<String, Object> updateImg(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            //保存当前用户（教师）的作业
            User user = (User) session.getAttribute("currentUser");
            String fileName = multipartFile.getOriginalFilename();
            String homeWorkFile = UserUtils.getUploadHomeworkPath(uploadFolder, user) + fileName;
            File newfile = new File(homeWorkFile);
            try {
                //保存作业附件
                multipartFile.transferTo(newfile);
                map.put("result", "success");//文件上传上传
                //存在数据库中的附件地址
                String savePath = staticAccessPath + user.getId() + "/homework/" + fileName;
                map.put("filePath", savePath);
            } catch (IOException e) {
                logger.error(e.toString());
                map.put("result", "error");//文件上传上传
            }
        }
        return map;
    }

    /**
     * 显示作业信息
     * @param id
     * @return
     */
    @GetMapping("/homework/{id}")
    public String homework(@PathVariable Long id, Model model) {
        HomeWork homeWork = this.homeworkService.findById(id);
        model.addAttribute("homeWork", homeWork);
        return "teacher/homework/homework";
    }

    /**
     * 教师新增或修改作业
     *
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
                System.out.println("id=" + id);
                System.out.println("title=" + title);
                System.out.println("homeworkContent=" + homeworkContent);
                System.out.println("files=" + files);
                map.put("msg", "保存成功");
            } else {
                // 修改
                System.out.println("title=" + title);
                System.out.println("homeworkContent=" + homeworkContent);
                System.out.println("files=" + files);
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
     *
     * @param homeworkId
     * @return
     */
    @PostMapping("/homework/delete/{homeworkId}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long homeworkId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = this.homeworkService.softDelete(homeworkId);
            if (result == 1) {
                map.put("msg", "删除成功！");
            } else {
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
     *
     * @param course_id
     * @return
     */
    @PostMapping("/homework/getHomeworkByCourseId/{course_id}")
    @ResponseBody
    public List<HomeWork> getHomeworkByCourseId(@PathVariable int course_id) {
        return this.homeworkService.find(" course.id=" + course_id);
    }

    /**
     * 发布作业
     *
     * @return
     */
    @GetMapping("/homework/publishHomeWork/{course_id}")
    public String publishHomeWork(@PathVariable Integer course_id, Model model, Long homework_id) {
        Course course = this.courseService.findById(course_id);
        model.addAttribute("course", course);
        if (homework_id != null && homework_id > 0) {
            HomeWork homeWork = this.homeworkService.findById(homework_id);
            model.addAttribute("homeWork", homeWork);
        }
        return "teacher/homework/publishHomework";
    }


    @PostMapping("/homework/saveHomework")
    @ResponseBody
    public Map<String, Object> saveHomeWork(String title, String content, Long id, int type, String classInfos,String date, Integer course_id,String problemIds) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = (User) session.getAttribute("currentUser");
            String[] classes = classInfos.split(",");
            Date startTime = new Date();
            Date endTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (date != null && date.length() > 0) {
                //2020-06-11 - 2020-07-22
                String[] times = date.split(" - ");
                startTime = sdf.parse(times[0]);
                endTime = sdf.parse(times[1]);
            }
            HomeWork homeWork = new HomeWork();
            Set<ClassInfo> classInfoSet = new HashSet<>();
            for (String c : classes) {
                if (c != null && c.length() > 0) {
                    ClassInfo classInfo = new ClassInfo();
                    classInfo.setId(Integer.parseInt(c));
                    classInfoSet.add(classInfo);
                }
            }
            homeWork.setTitle(title);
            homeWork.setCreateTime(new Date());
            homeWork.setClassInfos(classInfoSet);
            Course course = new Course();
            course.setId(course_id);
            homeWork.setCourse(course);
            homeWork.setTeacher(user);
            homeWork.setStartTime(startTime);
            homeWork.setEndTime(endTime);
            homeWork.setContent(content);
            homeWork.setType(type);
            homeWork.setProblemIds(problemIds.trim());
            if (id != null && id > 0) {
                homeWork.setId(id);
                map.put("msg", "修改成功");
            } else {
                map.put("msg", "保存成功");
            }
        this.homeworkService.saveOrUpdateHomeWork(homeWork);
            map.put("code", 200);
        } catch (Exception e) {
            logger.error(e.toString());
            map.put("msg", "出现错误：" + e);
        }
        return map;
    }

    @GetMapping("/homework/details/{homework_id}")
    public String details(@PathVariable Long homework_id, Model model) {
        HomeWork homework = this.homeworkService.findById(homework_id);
        model.addAttribute("homework", homework);
        return "teacher/homework/homeworkDetails";
    }

    /**
     * 知识建构过程分析
     *
     * @param homework_id
     * @param model
     * @return
     */
    @GetMapping("/homework/showDetails/{homework_id}")
    public String showDetails(@PathVariable Long homework_id, Model model) {
        HomeWork homework = this.homeworkService.findById(homework_id);
        model.addAttribute("homework", homework);
        return "teacher/homework/showDetails";
    }

    /**
     * 学生作业信息分页
     */
    @GetMapping("/homework/detailInfo/{homework_id}/list.json")
    @ResponseBody
    public JsonData<MyKnowledgeGraph> detailInfo(Integer page, Integer limit, @PathVariable Long homework_id) {
        page = page == null ? 1 : page < 1 ? 1 : page;
        limit = limit == null ? 10 : limit < 1 ? 1 : limit;
        PageData<MyKnowledgeGraph> pageData = this.myKnowledgeGraphService.getPageData(page, limit, " myHomeWork.homeWork.id=" + homework_id);
        JsonData<MyKnowledgeGraph> pageJson = new JsonData<>();
        pageJson.setCode(0);
        pageJson.setCount(pageData.getTotalCount());
        pageJson.setMsg("知识建构列表");
        pageJson.setData(pageData.getPageData());
        return pageJson;
    }

    /**
     * 学生作业信息分页
     */
    @GetMapping("/homeworkdetailInfo/{homework_id}/list.json")
    @ResponseBody
    public JsonData<MyHomeWork> homeworkdetailInfo(Integer page, Integer limit, @PathVariable Long homework_id) {
        page = page == null ? 1 : page < 1 ? 1 : page;
        limit = limit == null ? 10 : limit < 1 ? 1 : limit;
        PageData<MyHomeWork> pageData = this.myHomeWorkService.getPageData(page, limit, " homework_id=" + homework_id);
        JsonData<MyHomeWork> pageJson = new JsonData<>();
        pageJson.setCode(0);
        pageJson.setCount(pageData.getTotalCount());
        pageJson.setMsg("学生作业列表");
        pageJson.setData(pageData.getPageData());
        return pageJson;
    }


    @GetMapping("/homework/showAll/{homework_id}")
    public String showAll(@PathVariable long homework_id, Model model) {
        HomeWork homeWork = this.homeworkService.findById(homework_id);
        model.addAttribute("homework", homeWork);
        List<MyKnowledgeGraph> myKnowledgeGraphs = this.myKnowledgeGraphService.find(" myHomeWork.homeWork.id=" + homework_id);
        //拿到全部节点
//	nodes	{ id: 1, label: 'Eric Cartman', age: 'kid', gender: 'male' },
//{ from: 1, to: 2, relation: 'friend', arrows: 'to, from', color: { color: 'red'} },
        StringBuffer nodes = new StringBuffer();
        StringBuffer edges = new StringBuffer();
        Set<Knowledge> knowledges = new HashSet<>();
        for (MyKnowledgeGraph graph : myKnowledgeGraphs
        ) {
            //处理节点
            Knowledge fromKnowledge = graph.getFromKnowledge();
            Knowledge toKnowledge = graph.getToKnowledge();
            if (!knowledges.contains(fromKnowledge)) {
                nodes.append("{ id: " + fromKnowledge.getId() + ", label: \'" + fromKnowledge.getKnowledge() + "\'},");
                knowledges.add(fromKnowledge);
            }
            if (!knowledges.contains(toKnowledge)) {
                nodes.append("{ id: " + toKnowledge.getId() + ", label: \'" + toKnowledge.getKnowledge() + "\'},");
                knowledges.add(toKnowledge);
            }
            //处理边
            edges.append("{ from:" + fromKnowledge.getId() + ",to:" + toKnowledge.getId() + ",label:\'" + graph.getRelation() + "\',arrows: 'to' },");
        }
        model.addAttribute("edges", edges.toString());
        model.addAttribute("nodes", nodes.toString());

        return "teacher/homework/showAll";
    }


}