package gzhu.edu.cn.knowledge.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.knowledge.entity.ItsTag;
import gzhu.edu.cn.knowledge.service.ITagService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 标签服务
 * @author: 丁国柱
 * @create: 2020-10-20 18:18
 */
@Service("tagService")
public class TagService extends BaseDAOImpl<ItsTag, Integer> implements ITagService {
}