package gzhu.edu.cn.teacher.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: exam
 * @description: 学情报告
 * @author: 丁国柱
 * @create: 2020-10-20 18:49
 */
@Controller
@RequestMapping("/studentAanlysis/")
public class StudentAnalysisController {

    @GetMapping("/")
    public String homeWorkResult(){

        return "";
    }


}