package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWorkProblem;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.homework.service.IMyHomeWorkProblemService;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁国柱
 * 第三步：创建实现类
 * 1.要标注@service
 * 2.extends BaseDAOImpl
 * 3.implement IPaperService
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-03-20 16:39
 */
@Service("myHomeWorkService")
public class MyHomeWorkService extends BaseDAOImpl<MyHomeWork,Long> implements IMyHomeWorkService {

    @Autowired
    private IMyHomeWorkProblemService myHomeWorkProblemService;

    @Override
    @Transactional
    public void batchSaveMyHomeWork(List<MyHomeWork> myHomeWorks,HomeWork homeWork) {
        //批量保存
        this.batchSave(myHomeWorks);

        //如果是编程作业
        if(homeWork.getType()==3){
           String problemIds = homeWork.getProblemIds();
            List<MyHomeWorkProblem> myHomeWorkProblems = new ArrayList<>();
           for (MyHomeWork myHomeWork:myHomeWorks){
                if(problemIds!=null&&problemIds.length()>0){
                    String[] ids = problemIds.split(";");

                    int i=1;
                    for (String id:ids){
                        ProblemBaseInformation problem = new ProblemBaseInformation();
                        problem.setId(Integer.parseInt(id));
                        MyHomeWorkProblem myHomeWorkProblem = new MyHomeWorkProblem();
                        myHomeWorkProblem.setMyHomeWork(myHomeWork);
                        myHomeWorkProblem.setProblem(problem);
                        myHomeWorkProblem.setSort(i++);
                        myHomeWorkProblem.setPass(false);
                        myHomeWorkProblem.setSubmissionTimes(0);
                        myHomeWorkProblems.add(myHomeWorkProblem);
                }
           }

        }
        this.myHomeWorkProblemService.batchSave(myHomeWorkProblems);
    }
}}