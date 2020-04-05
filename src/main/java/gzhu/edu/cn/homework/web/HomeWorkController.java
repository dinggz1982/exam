package gzhu.edu.cn.homework.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: exam
 * @description: 作业控制器
 * @author: 丁国柱
 * @create: 2020-03-27 16:19
 */
@Controller
public class HomeWorkController {

    @GetMapping("/its/homework")
    public String index(){

        return "homework/index";
    }

}