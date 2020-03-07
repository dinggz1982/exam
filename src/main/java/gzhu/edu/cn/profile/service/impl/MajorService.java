package gzhu.edu.cn.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.Major;
import gzhu.edu.cn.profile.service.IMajorService;

@Service("majorService")
public class MajorService extends BaseDAOImpl<Major, Integer> implements IMajorService{

	private final static Logger logger = LoggerFactory.getLogger(MajorService.class);



}
