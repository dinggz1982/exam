package gzhu.edu.cn.profile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.service.ICourseService;
/**
 * 课程service
 * @author dingguozhu
 *
 */
@Service("courseService")
public class CourseService extends BaseDAOImpl<Course, Integer> implements ICourseService{
	private final static Logger logger = LoggerFactory.getLogger(CourseService.class);
}
