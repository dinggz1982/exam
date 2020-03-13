package gzhu.edu.cn.profile.service;

import java.sql.SQLException;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.profile.entity.ClassInfo;
/**
 * 
 * @author dingguozhu
 *
 */
public interface IClassInfoService extends BaseService<ClassInfo, Integer>{
	
	/**
	 * 通过课程id返回授课的班级树
	 * @Description
	 * @author 丁国柱
	 * @date 2020年3月11日 上午3:56:22
	 * @param courseId
	 * @return
	 */
	public String getClassTreeByCourseId(Integer courseId) throws SQLException;

}
