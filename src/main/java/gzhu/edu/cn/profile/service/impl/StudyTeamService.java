package gzhu.edu.cn.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.StudyTeam;
import gzhu.edu.cn.profile.service.IStudyTeamService;

/**
 * 学期服务层实现
 * @author dingguozhu
 * @date 2020年3月10日 下午10:45:51
 * @description
 */
@SuppressWarnings("unchecked")
@Service("studyTeamService")
public class StudyTeamService extends BaseDAOImpl<StudyTeam, Integer> implements IStudyTeamService{

	private final static Logger logger = LoggerFactory.getLogger(StudyTeamService.class);

}
