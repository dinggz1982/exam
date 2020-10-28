package gzhu.edu.cn.rollcall.service;


import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.rollcall.entity.RollCall;

public interface IRollCallService extends BaseService<RollCall, Integer> {
	
	public void saveRollCall(String name,String[] userIds,String[] types);

}
