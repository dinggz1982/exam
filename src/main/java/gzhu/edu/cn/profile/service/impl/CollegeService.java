package gzhu.edu.cn.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.service.ICollegeService;

@SuppressWarnings("unchecked")
@Service("collegeService")
public class CollegeService extends BaseDAOImpl<College, Integer> implements ICollegeService{

	private final static Logger logger = LoggerFactory.getLogger(CollegeService.class);

}
