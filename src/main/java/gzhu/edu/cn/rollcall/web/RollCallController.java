package gzhu.edu.cn.rollcall.web;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.service.IClassInfoService;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.rollcall.entity.RollCall;
import gzhu.edu.cn.rollcall.entity.RollCallInfo;
import gzhu.edu.cn.rollcall.service.IRollCallInfoService;
import gzhu.edu.cn.rollcall.service.IRollCallService;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 点名
 * <p>Title : RollCallController</p>
 * <p>Description : </p>
 * <p>Company : </p>
 *
 * @author 丁国柱
 * @date 2018年3月6日 下午2:41:16
 */
@Controller
public class RollCallController {

    @Autowired
    private HttpSession session;

    @Autowired
    private IUserService userService;


    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRollCallService rollCallService;

    @Autowired
    private IRollCallInfoService rollCallInfoService;

    /**
     * @return
     */
    @GetMapping("/rollCall/index")
    public String list(Integer pageIndex, Integer pageSize, Model model) {
        return "rollcall/index";
    }


    /**
     * 到点名页面
     * <p>方法名:rollCall </p>
     * <p>Description : </p>
     * <p>Company : </p>
     *
     * @return
     * @author 丁国柱
     */
    @GetMapping("/rollCall")
    public String rollCall(Model model, String classInfo,String week,String courseId) {
        //取得当前课程对应的学生，目前先简化处理
		/*List<User> users = this.userService.findBySql("classInfo.id", 1);
		model.addAttribute("users", users);*/
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("teacher", user);
        model.addAttribute("classInfoId", classInfo);
        if(week==null||week.length()==0){
            week="1";
        }
        model.addAttribute("week", week);
        model.addAttribute("courseId", courseId);
        //取得当前的班级信息
        Set<ClassInfo> classInfos = new HashSet<>();
        List<Course> courses = courseService.find(" teacher_id=" + user.getId());
        model.addAttribute("courses", courses);
        for (Course course : courses
        ) {
            classInfos.addAll(course.getClassInfos());
        }

        model.addAttribute("classInfos", classInfos);
        if (classInfo != null && classInfo.length() > 0) {
            List<Object[]> users = this.userService.findByNaviteSql("select u.id,u.username,u.realname,u.img from sys_user u,its_student s where s.classinfo_id=" + classInfo + " and u.id=s.user_id");
            List<Integer> ids = new ArrayList<Integer>();
            List<String> usernames = new ArrayList<String>();
            List<String> imgs = new ArrayList<String>();
            List<String> xuehaos = new ArrayList<String>();
            for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                Object[] objects = (Object[]) iterator.next();
                ids.add(Integer.parseInt(objects[0].toString()));
                xuehaos.add(objects[1].toString());//学号和用户名一致
                usernames.add(objects[2].toString());
                imgs.add(objects[3].toString());
            }

            Gson g1 = new Gson();
            String usersjson = g1.toJson(users);
            model.addAttribute("users", usersjson);

            Gson g = new Gson();
            String json1 = g.toJson(ids);
            model.addAttribute("ids", json1);

            String json2 = g.toJson(usernames);
            model.addAttribute("usernames", json2);

            String json3 = g.toJson(xuehaos);
            model.addAttribute("xuehaos", json3);

            String json4 = g.toJson(imgs);
            model.addAttribute("imgs", json4);
        }
        return "rollcall/rollCall";
    }

    @PostMapping("/saveRollCall")
    public String saveRollCall(Model model, Long teacher_id, String[] id, String[] xuehao, String[] username, Integer classInfo, String week, String course) {
        List<User> users = new ArrayList<User>();
        for (int i = xuehao.length - 1; i >= 0; i--) {
            User user = new User();
            user.setId(Long.parseLong(id[i].toString()));
            user.setUsername(username[i]);
            user.setXuehao(xuehao[i]);
            users.add(user);
        }

        RollCall call = this.rollCallService.getByHql(" and course_id=" + course + " and classInfo_id=" + classInfo + " and week=" + week);
        if (call == null) {
            call = new RollCall();
            ClassInfo classInfo2 = new ClassInfo();
            classInfo2.setId(classInfo);
            call.setClassInfo(classInfo2);
            User teacher = new User();
            teacher.setId(teacher_id);
            call.setTeacher(teacher);
            Course course1 = new Course();
            course1.setId(classInfo);
            call.setCourse(course1);
            call.setWeek(week);
            call.setName("第" + week + "周点名/提问");
            call.setDate(new Date());
            this.rollCallService.save(call);
        }
        model.addAttribute("rollcall", call);
        model.addAttribute("users", users);

        return "rollcall/submitRollCall";
    }

    /**
     * 提交点名信息
     * <p>方法名:submitRollCall </p>
     * <p>Description : </p>
     * <p>Company : </p>
     *
     * @return
     * @author 丁国柱
     */
    @PostMapping("/submitRollCall")
    @ResponseBody
    public Map<String, Object> submitRollCall(Long userid, int status, Integer rollcall) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            RollCallInfo rollCallInfo = new RollCallInfo();
            RollCall rollCall = new RollCall();
            rollCall.setId(rollcall);
            rollCallInfo.setRollCall(rollCall);
            rollCallInfo.setType(status);
            rollCallInfo.setCreateTime(new Date());
            User user = this.userService.findById(userid);
            rollCallInfo.setUser(user);
            this.rollCallInfoService.save(rollCallInfo);
            map.put("msg", "保存成功！");
        } catch (Exception e) {
            map.put("msg", "保存失败！");
            e.printStackTrace();
        }
        return map;
    }


    @GetMapping("/rollCallList")
    public String rollCallList(Integer pageIndex, Integer pageSize, Model model) {
        pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
        pageSize = 10;
        PageData<RollCall> pageData = this.rollCallService.getPageData(pageIndex, pageSize, "");
        model.addAttribute("dataList", pageData.getPageData());
        model.addAttribute("total", pageData.getTotalCount());
        model.addAttribute("pages", pageData.getTotalPage());
        model.addAttribute("pagesize", pageData.getPageSize());
        model.addAttribute("pageIndex", pageIndex);
        return "rollcall/list";
    }

    /**
     * 查看点名信息
     * <p>方法名:showRollCall </p>
     * <p>Description : </p>
     * <p>Company : </p>
     *
     * @return
     * @author 丁国柱
     */
    @GetMapping("/showRollCall/{id}")
    public String showRollCall(@PathVariable int id, Model model) {
        RollCall call = this.rollCallService.findById(id);
        List<RollCallInfo> callInfos = this.rollCallInfoService.findBySql("rollCall.id", id);
        model.addAttribute("callInfos", callInfos);
        model.addAttribute("call", call);
        return "rollcall/showRollCall";
    }

    /**
     * 返回用户json数据
     *
     * @param page
     * @param limit
     * @param sex
     * @param username
     * @param realname
     * @return
     */
    @GetMapping("/rollCall/list.json")
    @ResponseBody
    public JsonData<RollCallInfo> userList1(Integer page, Integer limit, String sex, String username, String realname) {
        page = page == null ? 1 : page < 1 ? 1 : page;
        limit = limit == null ? 10 : limit < 1 ? 1 : limit;

        String hql = "";
        if (username != null && username != "") {
            hql = " username like '%" + username + "%' and";
        }
        if (realname != null && realname != "") {
            hql = hql + " realname like '%" + realname + "%' and";
        }
        if (sex != null && sex != "") {
            hql = hql + " sex = '" + sex + "' and";
        }
        if (hql.length() > 0) {
            hql = hql.substring(0, hql.length() - 4);
        }
        PageData<RollCallInfo> pageData = this.rollCallInfoService.getPageData(page, limit, hql);
        JsonData<RollCallInfo> pageJson = new JsonData<RollCallInfo>();
        pageJson.setCode(0);
        pageJson.setCount(pageData.getTotalCount());
        pageJson.setMsg("点名/提问列表");
        pageJson.setData(pageData.getPageData());
        return pageJson;
    }

}
