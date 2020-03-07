package gzhu.edu.cn.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.Subject;
import gzhu.edu.cn.profile.service.ISubjectService;

@Service("subjectService")
public class SubjectService extends BaseDAOImpl<Subject, Integer> implements ISubjectService{

	private final static Logger logger = LoggerFactory.getLogger(SubjectService.class);



}
