package gzhu.edu.cn.base.log.service.impl;

import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.base.log.entity.LogInfo;
import gzhu.edu.cn.base.log.service.ILogInfoService;

@Service("logInfoService")
public class LogInfoService extends BaseDAOImpl<LogInfo, Long> implements ILogInfoService {

}
