package gzhu.edu.cn.student.web;

import gzhu.edu.cn.base.util.UploadUserUtils;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 实现文件上传
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveUserFromFile")
    public String saveUserFromFile(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        // 如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            // 上传文件名
            String filename = file.getOriginalFilename();
            String path = uploadFolder+"studentFile/";
            File filepath = new File(path, filename);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            // 输出文件上传最终的路径 测试查看
            List<User> users = UploadUserUtils.getUsers(path + File.separator + filename,"/t");
            List<User> newUsers = new ArrayList<User>();
            List<User> exitsUser = new ArrayList<User>();

            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                User user = (User) iterator.next();
                User user1 = this.userService.findByName(user.getUsername());
                if(user1==null){
                    newUsers.add(user);
                }else{
                    exitsUser.add(user1);
                }
            }
            this.userService.batchSave(newUsers);
        }

        return "system/user/uploadSuccess";
    }
}