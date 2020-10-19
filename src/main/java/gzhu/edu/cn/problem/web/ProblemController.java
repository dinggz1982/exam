package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.base.util.UserUtils;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemChoice;
import gzhu.edu.cn.problem.entity.ProblemChoiceItem;
import gzhu.edu.cn.problem.service.IProblemChoiceService;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.system.entity.User;
import org.apache.jena.sparql.function.library.date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: exam
 * @description: 试题控制器
 * @author: 丁国柱
 * @create: 2020-10-18 18:49
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Autowired
    private HttpSession session;

    @Autowired
    private IProblemChoiceService problemChoiceService;

    @GetMapping("/add")
    private String addProblem(){
        return "problem/addProblem";
    }

    /**
     上传图片
     * @Title: uploadImage
     * @Description: 上传图片
     * @param request
     * @return Map<String, Object> location(图片要上传的位置)
     * @throws
     */
    @PostMapping("/uploadImg")
    public @ResponseBody
    Map<String, Object> uploadImage(@RequestParam("file") MultipartFile multipartFile,
                                    HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if(multipartFile!=null&&!multipartFile.isEmpty()){
            //保存当前用户（教师）的作业
            User user = (User) session.getAttribute("currentUser");
            String fileName = multipartFile.getOriginalFilename();
            String homeWorkFile = UserUtils.getUploadProblemImagePath(uploadFolder) + fileName;
            File newfile = new File(homeWorkFile);
            try {
                //保存作业图片
                multipartFile.transferTo(newfile);
                map.put("result","success");//文件上传上传
                //存在数据库中的附件地址
                String savePath = staticAccessPath +"problem/images/"+fileName;
                map.put("filePath",savePath);
                map.put("location",savePath);
            } catch (IOException e) {
                map.put("result","error");//文件上传上传
            }
        }
        return map;
    }

    @PostMapping("/saveChoice")
    @ResponseBody
    public Map<String, Object> saveHomeWork(String title, String content,int score, Integer id,Integer problemChoiceId, int type, String itemA, String itemB,String itemC,String itemD,String itemE,String itemF,String answer) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = (User) session.getAttribute("currentUser");
            if(type==3){
                problemChoiceService.saveSingleChoice(user,id,title,score,problemChoiceId,content,itemA,itemB,itemC,itemD,itemE,itemF,answer);
            }
            map.put("msg", "保存或修改成功");
            map.put("code", 200);
        } catch (Exception e) {
            map.put("msg", "出现错误：" + e);
        }
        return map;
    }


}