package gzhu.edu.cn.problem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemSubmissions;
import gzhu.edu.cn.problem.service.IProblemProgrammingDeatilService;
import gzhu.edu.cn.problem.service.IProblemSubmissionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

/**
 * @program: exam
 * @description: 代码提交服务
 * @author: 丁国柱
 * @create: 2020-09-28 23:05
 */
@Service("problemSubmissionsService")
public class ProblemSubmissionsService extends BaseDAOImpl<ProblemSubmissions, Long> implements IProblemSubmissionsService {
    private final static Logger logger = LoggerFactory.getLogger(ProblemSubmissionsService.class);

    @Autowired
    private IProblemProgrammingDeatilService programmingDeatilService;

    @Override
    @Transactional
    public void doJudge(Long submissionId) {
        //1.更新状态为正在测评中
        this.executeSql("update problem_programming_submissions set status=2 where id=" + submissionId);

        //2.获取测评数据
        ProblemSubmissions problemSubmissions = this.findById(submissionId);

        //4.生成测评文件
        String filePath = "/tmp/ljudge";
        String judgeFile = this.generateUserCodeById(submissionId, filePath);

        //5.获取测试数据

        logger.info("获取测试数据...");
        List<String[]> inAndOutFiles = this.programmingDeatilService.getInAndOutTestCaseByProblemId(problemSubmissions.getProblemBase().getId());

        //6.进行测评...
        logger.info("进行测评...");
        //ljudge --max-cpu-time 1.0 --max-memory 32m --user-code a.py --testcase --input 1.in --output 1.out --testcase --input 2.in --output 2.out
        String compilation = "";
        String resultInfos = "";

        //生成测试文件
        String test_cases = "";
        int acceptNum = 0;
        int resultNum = inAndOutFiles.size();
        for (String[] inAndOutFile : inAndOutFiles) {
            test_cases = test_cases + " --testcase --input " + inAndOutFile[0] + "  --output " + inAndOutFile[1];
        }

        try {
            String command = "ljudge --max-cpu-time 1.0 --max-memory 32m --user-code " + judgeFile + " " + test_cases;
            String[] cmd = new String[]{"/bin/sh", "-c", command};
            //打印命令
            logger.info("命令：" + command);
            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("");
            }
            br.close();
            String resultInfo = sb.toString();
            logger.info(resultInfo);
            JSONObject jsonObject;
            jsonObject = JSONObject.parseObject(resultInfo);
            //处理结果
            compilation = String.valueOf(jsonObject.get("compilation"));//编译的结果

            //处理testcases
            JSONArray testAarray = JSONArray.parseArray(jsonObject.get("testcases").toString());
            for (Object o : testAarray) {
                JSONObject testcase = JSONObject.parseObject(o.toString());
                //测评ACCEPTED
                String time = testcase.get("time").toString();
                String memory = testcase.get("memory").toString();
                String result = testcase.get("result").toString();
                //测评机此处有问题，先这样解决
                if (result.equals("ACCEPTED") || result.equals("PRESENTATION_ERROR")) {
                    //测评OK
                    result = "Accept";
                    acceptNum++;
                }
                resultInfos = resultInfos + result + ":" + time + ":" + memory + ";";
            }

        } catch (Exception e) {
            logger.error("----error-----" + e);
            for (int i = 0; i < resultNum; i++) {
                resultInfos = resultInfos + "error:0:0;";
            }
            e.printStackTrace();
        }
        this.updateJudgeResult(submissionId, resultInfos, 3, String.valueOf(acceptNum), compilation);

    }

    @Override
    public String generateUserCodeById(long id, String filePath) {
        ProblemSubmissions problemSubmissions = this.findById(id);
        File uploadFile2 = new File(filePath);
        String file = null;
        // 判断目录是否存在
        if (!uploadFile2.exists()) {
            uploadFile2.mkdirs();// 不存在则新建
        }
        FileOutputStream fop = null;
        try {
            if (problemSubmissions.getCode() != null) {
                String code = problemSubmissions.getCode();
                file = filePath + "/" + problemSubmissions.getId() + "." + problemSubmissions.getLanguage();
                fop = new FileOutputStream(file);
                byte[] contentInBytes = code.getBytes();
                fop.write(contentInBytes);
                fop.flush();
                fop.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    @Override
    public void updateJudgeResult(long id, String judgeResult, int status, String acceptNumber, String compileInfo) {
        this.executeSql("update problem_programming_submissions set status=" + status + ",judgeResult='" + judgeResult + "',acceptNumber='" + acceptNumber + "',compileInfo='" + compileInfo + "' where id=" + id);
    }
}