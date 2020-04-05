package gzhu.edu.cn;

import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.system.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-03-27 16:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class HomeWorkTest {

    @Resource
    private IHomeWorkService homeWorkService;

    @Test
    public void addHomeWork(){

        HomeWork homeWork = new HomeWork();
        homeWork.setContent("完成学生模块的代码编写");
        homeWork.setCreateTime(new Date());
        homeWork.setTitle("第一次作业");
        homeWork.setScore(100f);

        User user =new User();
        user.setId(1);
        homeWork.setCreator(user);

        User s1  = new User();
        s1.setId(4);
        User s2  = new User();
        s2.setId(5);
        User s3  = new User();
        s3.setId(6);
        Set<User> students = new HashSet<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        homeWork.setStudents(students);

        this.homeWorkService.save(homeWork);
    }


}