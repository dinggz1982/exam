package gzhu.edu.cn.experiment.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.experiment.entity.Word;
import gzhu.edu.cn.experiment.service.IWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dinggz
 */
@Service("wordService")
@Transactional
public class WordService extends BaseDAOImpl<Word, Integer> implements IWordService {
	private final static Logger logger = LoggerFactory.getLogger(WordService.class);
	
}
