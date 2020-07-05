package gzhu.edu.cn.profile.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.service.IClassInfoService;
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.ISchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 班级信息
 * <p>
 * Title : ClassInfoService
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Company :
 * </p>
 * 
 * @author 丁国柱
 */
@Service("classInfoService")
public class ClassInfoService extends BaseDAOImpl<ClassInfo, Integer> implements IClassInfoService {

	@Autowired
	private ISchoolService schoolService;

	@Autowired
	private ICollegeService collegeService;

	private final static Logger logger = LoggerFactory.getLogger(ClassInfoService.class);

	@Override
	public String getClassTreeByCourseId(Integer courseId) throws SQLException {
		StringBuffer bf = new StringBuffer();
		bf.append("[");
		// 获取当前课程的授课班级
		List<Object> classids = null;
		Set<Integer> classInfoIds = new HashSet<Integer>();
		if (courseId != null && courseId > 0) {
			classids = this
					.findObjectBySql("select classInfos_id from profile_course_classinfos where Course_id=" + courseId);
			for (Iterator iterator = classids.iterator(); iterator.hasNext();) {
				Object classInfoId = (Object) iterator.next();
				classInfoIds.add(Integer.parseInt(classInfoId.toString()));
			}
			
		}
		// 先获取学校
		List<School> schools = this.schoolService.findAll();
		for (Iterator iterator = schools.iterator(); iterator.hasNext();) {
			School school = (School) iterator.next();
			bf.append("{\"id\":\"school_" + school.getId() + "\",\"open\":true,\"name\":\"" + school.getName() + "\",");
			// 查询当前学校下是否有学院
			List<College> colleges = this.collegeService.find(" school.id=" + school.getId());
			if (colleges != null && colleges.size() > 0) {
				// 有学院的情况
				bf.append("\"children\":[");
				for (Iterator iterator2 = colleges.iterator(); iterator2.hasNext();) {
					College college = (College) iterator2.next();
					bf.append("{\"id\":\"college_" + college.getId() + "\",\"open\":true,\"name\":\"" + college.getName() + "\",");
					// 查询当前学院下的班级
					List<ClassInfo> classInfos = this.find(" major.college.id=" + college.getId());
					if (classInfos != null && classInfos.size() > 0) {
						bf.append("\"children\":[");
						for (Iterator iterator3 = classInfos.iterator(); iterator3.hasNext();) {
							ClassInfo classInfo = (ClassInfo) iterator3.next();
							//判断当前的课程与班级是否建立了管理
							if(classInfoIds.contains(classInfo.getId())){
								bf.append("{\"id\":\"classinfo_" + classInfo.getId() + "\",\"checked\":true,\"name\":\""
										+ classInfo.getName() + "\"},");
							}
							else{
							bf.append("{\"id\":\"classinfo_" + classInfo.getId() + "\",\"name\":\""
									+ classInfo.getName() + "\"},");
							}
						}
						bf.deleteCharAt(bf.length() - 1).append("]},");
					} else {
						bf.deleteCharAt(bf.length() - 1).append("},");
					}
					//每一次学院循环完后需要加},
					//bf.deleteCharAt(bf.length() - 1).append("},");
				}
				bf.deleteCharAt(bf.length() - 1).append("]},");
			}
			// 当前学校没有学院的情况
			else {
				// 删掉最后的,号
				bf.deleteCharAt(bf.length() - 1).append("},");
			}
			// 每个学校的}结尾
			//bf.deleteCharAt(bf.length() - 1).append("},");
		}
		bf.deleteCharAt(bf.length() - 1).append("]");
		return bf.toString();
	}

}
