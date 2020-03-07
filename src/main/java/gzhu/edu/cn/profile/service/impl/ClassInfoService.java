package gzhu.edu.cn.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.service.IClassInfoService;
/**
 * 班级信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 */
@Service("classInfoService")
public class ClassInfoService extends BaseDAOImpl<ClassInfo, Integer> implements IClassInfoService{

	private final static Logger logger = LoggerFactory.getLogger(ClassInfoService.class);



}
