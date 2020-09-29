package gzhu.edu.cn.problem.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.problem.entity.ProblemSubmissions;
import org.springframework.transaction.annotation.Transactional;

public interface IProblemSubmissionsService extends BaseService<ProblemSubmissions,Long> {

    /**
     * 对submissionId进行测评
     * @param submissionId
     */
    public void doJudge(Long submissionId);

    /**
     * 根据ProblemSubmissions的id，获取到代码
     * @param id
     * @param filePath
     * @return
     */
    public String generateUserCodeById(long id,String filePath);

    /**
     * 根据id,更新测评结果，同时把状态修改为测评完成3
     * @param id 主键测评ID
     * * @param judgeResult	测评结果
     */
    @Transactional(readOnly = false)
    public void updateJudgeResult(long id,String judgeResult,int status,String acceptNumber,String compileInfo);

}
