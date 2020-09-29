package gzhu.edu.cn.experiment.service.impl;

import gzhu.edu.cn.experiment.service.IMyWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.experiment.entity.MyWord;
/**
 * @author dinggz
 */
@Service("myWordService")
@Transactional
public class MyWordService extends BaseDAOImpl<MyWord, Integer> implements IMyWordService {
	private final static Logger logger = LoggerFactory.getLogger(MyWordService.class);
	
}
