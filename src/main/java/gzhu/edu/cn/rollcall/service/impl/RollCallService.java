package gzhu.edu.cn.rollcall.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.rollcall.entity.RollCall;
import gzhu.edu.cn.rollcall.entity.RollCallInfo;
import gzhu.edu.cn.rollcall.service.IRollCallInfoService;
import gzhu.edu.cn.rollcall.service.IRollCallService;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 点名信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("rollCallService")
public class RollCallService extends BaseDAOImpl<RollCall, Integer> implements IRollCallService {

	@Resource
	private IRollCallInfoService callInfoService;
	@Resource
	private IUserService userService;
	
	private final static Logger logger = LoggerFactory.getLogger(RollCallService.class);

	@Override
	@Transactional
	public void saveRollCall(String name, String[] xuehaos, String[] types) {
		RollCall call = new RollCall();
		call.setAddress("文逸楼254");
		ClassInfo classInfo = new ClassInfo();
		classInfo.setId(1);
		call.setClassInfo(classInfo);
		User teacher = new User();
		teacher.setId(1l);
		call.setTeacher(teacher);
		call.setName(name);
		call.setDate(new Date());
		this.save(call);
		for (int i = 0; i < xuehaos.length; i++) {
			RollCallInfo callInfo = new RollCallInfo();
			callInfo.setType(Integer.parseInt(types[i]));
			User user = this.userService.getUserByXuehao(xuehaos[i]);
			callInfo.setUser(user);
			callInfo.setRollCall(call);
			this.callInfoService.save(callInfo);
		}
	}

}
