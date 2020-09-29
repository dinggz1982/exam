package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemProgrammingDeatil;
import gzhu.edu.cn.problem.service.IProblemProgrammingDeatilService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: exam
 * @description: 编程题具体内容服务
 * @author: 丁国柱
 * @create: 2020-09-21 00:59
 */
@Service("problemProgrammingDeatilService")
public class ProblemProgrammingDeatilService extends BaseDAOImpl<ProblemProgrammingDeatil,Integer> implements IProblemProgrammingDeatilService {
    @Override
    public ProblemProgrammingDeatil getByProblemId(int problemId) {
        return this.getByHql(" and problemId="+problemId);
    }

    @Override
    public List<String> getOutTestCaseByProblemId(int problemId) {
        String url="/data/testMachine/testData/"+problemId+"/";
        List<String> inTestList = new ArrayList<String>();
        ProblemProgrammingDeatil problemProgramming=this.getByProblemId(problemId);
        if(problemProgramming != null && problemProgramming.getTestpointinfo() != null) {
            String[] testPointInfoArrsy=problemProgramming.getTestpointinfo().split(";");
            for(String str:testPointInfoArrsy) {
                String[] outTestArray=str.split(":");
                inTestList.add(url+outTestArray[3]);
            }
        }
        return inTestList;
    }

    @Override
    public List<String[]> getInAndOutTestCaseByProblemId(int problemId) {
        String url="/data/testMachine/testData/"+problemId+"/";
        List<String[]> inOutTestList = new ArrayList<String[]>();
        ProblemProgrammingDeatil problemProgramming=this.getByProblemId(problemId);
        if(problemProgramming != null && problemProgramming.getTestpointinfo() != null) {
            String[] testPointInfoArrsy=problemProgramming.getTestpointinfo().split(";");
            for(String str:testPointInfoArrsy) {
                String[] inOutArray= {url+str.split(":")[2],url+str.split(":")[3]};
                inOutTestList.add(inOutArray);
            }
        }
        return inOutTestList;
    }
}