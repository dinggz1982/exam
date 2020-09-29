package gzhu.edu.cn;

import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: exam
 * @description: 试题基本信息测试
 * @author: 丁国柱
 * @create: 2020-09-21 00:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class ProblemBaseInfomationTest {

    @Autowired
    private IProblemBaseInformationService problemBaseInformationService;

    @Test
    public void test(){
        ProblemBaseInformation problemBaseInformation = this.problemBaseInformationService.findById(1);
        System.out.println(problemBaseInformation.getChoiceItem());
    }


}