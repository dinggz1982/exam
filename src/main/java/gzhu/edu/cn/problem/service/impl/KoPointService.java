package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.KoPoint;
import gzhu.edu.cn.problem.service.IKoPointService;

import java.util.Iterator;
import java.util.List;

/**
 * @program: exam
 * @description: 知识点
 * @author: 丁国柱
 * @create: 2020-10-10 15:05
 */
public class KoPointService extends BaseDAOImpl<KoPoint,Integer> implements IKoPointService {
    @Override
    public String getKoTreeByCourseId(int courseId) {
        List<Object[]> objects = this.findBySql(
                "select r.id as id,r.parentId as pId,r.name as name from its_knowledge_point r where r.delFlag=0");
        if (objects != null && objects.size() > 0) {
            StringBuffer bf = new StringBuffer();
            bf.append("[");
            for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
                Object[] object = (Object[]) iterator.next();
                bf.append("{id:\"" + object[0] + "\",pId:\"" + object[1] + "\",name:\"" + object[2] + "\"");
                int childNum = this.getCountBySql("select  count(*) from its_knowledge_point where parentId=" + object[0]);
                if (childNum > 0) {
                    // 有子树的情况
                    bf.append(",open:true");
                }
                bf.append("},");
            }
            return bf.deleteCharAt(bf.length() - 1).toString() + "]";
        } else {
            return "";
        }
    }
}