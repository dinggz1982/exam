package gzhu.edu.cn.rollcall.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.rollcall.entity.RollCallInfo;
import gzhu.edu.cn.rollcall.service.IRollCallInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 点名信息
 * <p>Title : ClassInfoService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:18:08
 */
@Service("rollCallInfoService")
public class RollCallInfoService extends BaseDAOImpl<RollCallInfo, Long> implements IRollCallInfoService {
	private final static Logger logger = LoggerFactory.getLogger(RollCallInfoService.class);

}
