package gzhu.edu.cn.knowledge.web;

import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.knowledge.entity.Knowledge;
import gzhu.edu.cn.knowledge.entity.MyKnowledgeGraph;
import gzhu.edu.cn.knowledge.service.IKnowledgeService;
import gzhu.edu.cn.knowledge.service.IMyKnowledgeGraphService;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-06-11 21:00
 */
@Controller
public class KnowledgeGraphController {
    @Autowired
    private HttpSession session;

    @Autowired
    private IMyKnowledgeGraphService myKnowledgeGraphService;

    @Autowired
    private IKnowledgeService knowledgeService;

    /**
     * 编辑或修改我的知识图谱
     * @param myHomework_id
     * @param graph
     * @return
     */
    @PostMapping("/myKnowledgeGraph/edit")
    @ResponseBody
    public Map<String, Object> edit(Long myHomework_id, MyKnowledgeGraph graph) {
        User user = (User) session.getAttribute("currentUser");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (graph.getId() == null) {
                //新增
                MyHomeWork myHomeWork = new MyHomeWork();
                myHomeWork.setId(myHomework_id);
                graph.setCreateTime(new Date());
                graph.setMyHomeWork(myHomeWork);
                graph.setUser(user);

                //拿到from节点
                String from  = graph.getFrom();
                Knowledge fromKnowledge = this.knowledgeService.getByHql(" and knowledge='"+from+"'");
                if(fromKnowledge==null){
                    fromKnowledge = new Knowledge();
                    fromKnowledge.setKnowledge(from);
                    this.knowledgeService.save(fromKnowledge);
                }
                //拿到to节点
                String to  = graph.getTo();
                Knowledge toKnowledge = this.knowledgeService.getByHql(" and knowledge='"+to+"'");
                if(toKnowledge==null){
                    toKnowledge = new Knowledge();
                    toKnowledge.setKnowledge(to);
                    this.knowledgeService.save(toKnowledge);
                }
                graph.setFromKnowledge(fromKnowledge);
                graph.setToKnowledge(toKnowledge);
                this.myKnowledgeGraphService.save(graph);
                map.put("msg", "保存成功");
            } else {
                this.myKnowledgeGraphService.update(graph);
                map.put("msg", "修改成功");
            }
            map.put("code", 200);
        } catch (Exception e) {
            map.put("code", 200);
            map.put("msg", "出现错误：" + e);
        }
        return map;
    }

    @GetMapping("/myKnowledgeGraph/{id}")
    @ResponseBody
    public String getKnowledgeById(@PathVariable Long id){
        Knowledge knowledge = this.knowledgeService.findById(id);
        if(knowledge!=null){
            return knowledge.getKnowledge();
        }else{
            return "";
        }
    }

}