package gzhu.edu.cn.experiment.service.impl;

import gzhu.edu.cn.experiment.entity.TagEditHistory;
import gzhu.edu.cn.experiment.service.ITagEditHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
/**
 * @author dinggz
 */
@Service("tagEditHistoryService")
@Transactional
public class TagEditHistoryService extends BaseDAOImpl<TagEditHistory, Integer> implements ITagEditHistoryService {
	private final static Logger logger = LoggerFactory.getLogger(TagEditHistoryService.class);
	
}
