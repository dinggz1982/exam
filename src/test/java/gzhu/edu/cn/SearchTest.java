package gzhu.edu.cn;

import java.util.List;

import gzhu.edu.cn.homework.entity.MyHomeWorkProblem;
import gzhu.edu.cn.homework.service.IMyHomeWorkProblemService;
import gzhu.edu.cn.problem.entity.ProblemProgrammingDeatil;
import gzhu.edu.cn.problem.service.IProblemProgrammingDeatilService;
import gzhu.edu.cn.problem.service.impl.ProblemProgrammingDeatilService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.profile.entity.Major;
import gzhu.edu.cn.profile.service.IMajorService;

/**
 * 检索查询测试
 *
 * @author dingguozhu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class SearchTest {

    @Autowired
    private IMajorService majorService;
    @Autowired
    private IMyHomeWorkProblemService myHomeWorkProblemService;

    @Test
    public void searchBySchoolId() {

        List<Major> majors = this.majorService.find(" college.school.id=1");
        System.out.println(majors.size());


    }

    @Autowired
    private IProblemProgrammingDeatilService problemProgrammingDeatilService;

    @Test
    public void contextLoads() {
        try {
            MyHomeWorkProblem myHomeWorkProblem = this.myHomeWorkProblemService.getByHql(" and problem_id=888 and myhomework_id=1175");
            System.out.println("=========================="+myHomeWorkProblem.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
