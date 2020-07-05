package gzhu.edu.cn.knowledge.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.knowledge.entity.Knowledge;
import gzhu.edu.cn.knowledge.service.IKnowledgeService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description:知识节点服务层
 * @author: 丁国柱
 * @create: 2020-06-11 19:40
 */
@Service("knowledgeService")
public class KnowledgeService extends BaseDAOImpl<Knowledge, Long> implements IKnowledgeService {
}