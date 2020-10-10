package gzhu.edu.cn;

import gzhu.edu.cn.problem.entity.ProblemTag;
import gzhu.edu.cn.problem.service.IProblemTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: exam
 * @description: 标签测试
 * @author: 丁国柱
 * @create: 2020-10-10 14:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class ProblemTagsTest {

    @Autowired
    private IProblemTagService problemTagsService;

    @Test
    public void addProblemTags(){
        List<ProblemTag> problemTags = new ArrayList<>();
        ProblemTag problemTag1 = new ProblemTag();
        problemTag1.setProblemId(888);
        problemTag1.setName("比大小");
        problemTag1.setDelFlag(false);

        ProblemTag problemTag2 = new ProblemTag();
        problemTag2.setProblemId(888);
        problemTag2.setName("输入输出");
        problemTag2.setDelFlag(false);

        problemTags.add(problemTag1);
        problemTags.add(problemTag2);

        this.problemTagsService.batchSave(problemTags);


    }

}