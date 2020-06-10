package gzhu.edu.cn.profile.web;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.entity.Major;
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.service.IClassInfoService;
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级管理
 *
 * @author dingguozhu
 * @date 2020年3月11日 上午4:11:05
 * @description
 */
@Controller
@RequestMapping("/profile")
public class ClassInfoController {

    @Autowired
    private IClassInfoService classInfoService;

    @Autowired
    private ICollegeService collegeService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 列出班级信息
     *
     * @return
     */
    @GetMapping("/classinfo/index")
    public String index(Model model) throws SQLException {
        //学校信息
        List<School> schools = this.schoolService.findAll();
        model.addAttribute("schools", schools);

        return "profile/classinfo/index";
    }

    /**
     * 班级具体信息
     * @param model
     * @param classInfoId
     * @return
     */
    @GetMapping("/classInfo/detail/{classInfoId}")
    public String classInfoDetail(Model model,@PathVariable  Integer classInfoId) throws SQLException {
        //取得当前这个班级的全部学生数据
        List<School> schools = this.schoolService.findAll();
        model.addAttribute("schools", schools);
        return "profile/classinfo/classInfoDetail";
    }



    /**
     * 班级信息分页
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/classInfo/list.json")
    @ResponseBody
    public JsonData<ClassInfo> userList1(Integer page, Integer limit,  Integer school_id,Integer college_id,Integer major_id) {
        page = page == null ? 1 : page < 1 ? 1 : page;
        limit = limit == null ? 10 : limit < 1 ? 1 : limit;

        String hql = "";
        if(major_id!=null&&major_id>0){
            hql = " major.id="+major_id;
        }else if(college_id!=null&&college_id>0){
            hql =" major.college.id = "+college_id ;
        }else if(school_id!=null&&school_id>0){
            hql = " major.college.school.id = "+school_id ;
        }
        PageData<ClassInfo> pageData = this.classInfoService.getPageData(page, limit, hql);
        JsonData<ClassInfo> pageJson = new JsonData<ClassInfo>();
        pageJson.setCode(0);
        pageJson.setCount(pageData.getTotalCount());
        pageJson.setMsg("学院列表");
        pageJson.setData(pageData.getPageData());
        return pageJson;
    }

    /**
     * 编辑或新增班级
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/classInfo/edit")
    @ResponseBody
    public Map<String, Object> edit(Integer id,String grade, String name, Integer majorId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (id == null) {
                // 新增
                Major major = new Major();
                major.setId(majorId);
                ClassInfo classInfo = new ClassInfo();
                classInfo.setMajor(major);
                classInfo.setGrade(grade);
                classInfo.setName(name);
                this.classInfoService.save(classInfo);
                map.put("msg", "保存成功");
            } else {
                Major major = new Major();
                major.setId(majorId);
                ClassInfo classInfo = new ClassInfo();
                classInfo.setId(id);
                classInfo.setMajor(major);
                classInfo.setGrade(grade);
                classInfo.setName(name);
                this.classInfoService.update(classInfo);
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
     * 软删除班级
     * @param id
     * @return
     */
    @PostMapping("/classInfo/delete")
    @ResponseBody
    public Map<String,Object> delete(Integer classInfoId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = this.classInfoService.softDelete(classInfoId);
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
     * 班级树结构
     * @param course_id
     * @return
     */
    @PostMapping("/classInfo/classTree")
    @ResponseBody
    public Map<String, Object> edit(Integer course_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("msg", "修改成功");
            map.put("code", 200);
            map.put("data", this.classInfoService.getClassTreeByCourseId(course_id));
        } catch (Exception e) {
            map.put("code", 200);
            map.put("msg", "出现错误：" + e);
        }
        return map;
    }


}
