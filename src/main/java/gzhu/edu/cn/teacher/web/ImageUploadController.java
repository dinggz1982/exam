package gzhu.edu.cn.teacher.web;

import com.sun.imageio.plugins.common.ImageUtil;
import gzhu.edu.cn.base.util.UserUtils;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: exam
 * @description: 附件上传
 * @author: 丁国柱
 * @create: 2020-06-30 14:10
 */
@Controller
@RequestMapping("/teacher/homework")
public class ImageUploadController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Autowired
    private HttpSession session;
    /**
     上传图片
     * @Title: uploadImage
     * @Description: 上传图片
     * @param request
     * @return Map<String, Object> location(图片要上传的位置)
     * @throws
     */
    @PostMapping("/homeworkUploads")
    public @ResponseBody
    Map<String, Object> uploadImage(@RequestParam("file") MultipartFile multipartFile,
                                    HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if(multipartFile!=null&&!multipartFile.isEmpty()){
            //保存当前用户（教师）的作业
            User user = (User) session.getAttribute("currentUser");
            String fileName = multipartFile.getOriginalFilename();
            String homeWorkFile = UserUtils.getUploadHomeworkImagePath(uploadFolder, user) + fileName;
            File newfile = new File(homeWorkFile);
            try {
                //保存作业图片
                multipartFile.transferTo(newfile);
                map.put("result","success");//文件上传上传
                //存在数据库中的附件地址
                String savePath = staticAccessPath + user.getId()+"/homework/images/"+fileName;
                map.put("filePath",savePath);
                map.put("location",savePath);
            } catch (IOException e) {
                map.put("result","error");//文件上传上传
            }
        }
        return map;
    }

    public  String uploadImage(MultipartFile image, String basePath, long user_id) throws Exception{
        String ret = "";

        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件类型，如果不是图片，禁止上传
        String contentType = image.getContentType();
        //获得文件的后缀名
        String suffixName = contentType.substring(contentType.indexOf("/")+1);
        //得到文件名
        String imageName = uuid+"."+suffixName;
        //获取文件夹路径
        String direPath = basePath+"homeworkUploads\\homework\\images\\"+user_id+"\\";
        File direFile = new File(direPath);
        //文件夹如果不存在，新建文件夹
        if(direFile.exists() == false || direFile.isDirectory() == false){
            direFile.mkdir();
        }
        //得到文件路径
        String path = direPath+"\\"+imageName;

        image.transferTo(new File(path));

        ret="/WEB-INF/homeworkUploads/homework/images/"+user_id+"/"+imageName;

        return ret;
    }

}