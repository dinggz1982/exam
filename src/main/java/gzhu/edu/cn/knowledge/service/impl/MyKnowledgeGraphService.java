package gzhu.edu.cn.knowledge.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.knowledge.entity.MyKnowledgeGraph;
import gzhu.edu.cn.knowledge.service.IMyKnowledgeGraphService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-06-11 19:40
 */
@Service("myKnowledgeGraphService")
public class MyKnowledgeGraphService  extends BaseDAOImpl<MyKnowledgeGraph, Long> implements IMyKnowledgeGraphService {
}