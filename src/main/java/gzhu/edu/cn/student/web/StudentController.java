package gzhu.edu.cn.student.web;

import gzhu.edu.cn.base.util.UploadUserUtils;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.knowledge.entity.MyKnowledgeGraph;
import gzhu.edu.cn.knowledge.service.IMyKnowledgeGraphService;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.student.entity.Student;
import gzhu.edu.cn.student.service.IStudentService;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: exam
 * @description: 学生操作控制器
 * @author: 丁国柱
 * @create: 2020-03-27 00:02
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private HttpSession session;
    /**
     * 实现文件上传
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveUserFromFile/{classInfo_id}")
    @ResponseBody
    public String saveUserFromFile(@RequestParam("file") MultipartFile file, @PathVariable Integer classInfo_id)
            throws IllegalStateException, IOException {
        String result = "success";
        try {
            // 如果文件不为空，写入上传路径
            if (!file.isEmpty()) {
                // 上传文件名
                String filename = file.getOriginalFilename();
                String path = uploadFolder + "studentFile/";
                File filepath = new File(path, filename);
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                // 将上传文件保存到一个目标文件当中
                file.transferTo(new File(path + File.separator + filename));
                // 输出文件上传最终的路径 测试查看
                List<User> users = UploadUserUtils.getUsers(path + File.separator + filename, "/t");

                List<User> newUsers = new ArrayList<User>();
                List<User> exitsUser = new ArrayList<User>();
                List<Student> students = new ArrayList<>();
                ClassInfo classInfo = new ClassInfo();
                classInfo.setId(classInfo_id);
                for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                    User user = (User) iterator.next();
                    User user1 = this.userService.findByName(user.getUsername());

                    if (user1 == null) {
                        newUsers.add(user);
                    } else {
                        exitsUser.add(user1);
                    }
                }
                //批量保存用户
                this.userService.batchSave(newUsers);

                //将全部学生加入
                exitsUser.addAll(newUsers);
                for (User u : exitsUser
                ) {
                    int count = this.studentService.getCountBySql("select count(*) from its_student where user_id=" + u.getId() + " and classinfo_id=" + classInfo_id);
                    if (count == 0) {
                        Student student = new Student();
                        student.setUser(u);
                        student.setClassInfo(classInfo);
                        student.setStudentId(u.getUsername());
                        students.add(student);
                    }
                }
                this.studentService.batchSave(students);
            }
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        return result;
    }

}