package gzhu.edu.cn.experiment.service.impl;

import gzhu.edu.cn.experiment.entity.Topic;
import gzhu.edu.cn.experiment.service.ITopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
/**
 * @author dinggz
 */
@Service("topicService")
@Transactional
public class TopicService extends BaseDAOImpl<Topic, Integer> implements ITopicService {
	private final static Logger logger = LoggerFactory.getLogger(TopicService.class);
	
}
