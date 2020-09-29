package gzhu.edu.cn.problem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: exam
 * @description: 编程作业Controller
 * @author: 丁国柱
 * @create: 2020-09-26 20:14
 */
@Controller
public class HomeWorkProblemController {

    @GetMapping("/student/problem")
    public String index(){

        return "problem/index";
    }

}