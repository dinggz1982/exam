package gzhu.edu.cn.knowledge.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.system.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * 知识关联
 */
@Entity(name = "its_myknowledgegraph")
public class MyKnowledgeGraph extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "myhomework_id")
    private MyHomeWork myHomeWork;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 对象A
    @ManyToOne
    @JoinColumn(name = "from_id")
    private Knowledge fromKnowledge;

    private String fromType;

    @Transient
    private String from;

    @Transient
    private String to;

    // 对象B
    @ManyToOne
    @JoinColumn(name = "to_id")
    private Knowledge toKnowledge;

    private String toType;

    // 对象A和对象B的关系
    private String relation;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", nullable = false, columnDefinition = "Text")
    private String description;

    private Date createTime;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyHomeWork getMyHomeWork() {
        return myHomeWork;
    }

    public void setMyHomeWork(MyHomeWork myHomeWork) {
        this.myHomeWork = myHomeWork;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public Knowledge getFromKnowledge() {
        return fromKnowledge;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFromKnowledge(Knowledge fromKnowledge) {
        this.fromKnowledge = fromKnowledge;
    }

    public Knowledge getToKnowledge() {
        return toKnowledge;
    }

    public void setToKnowledge(Knowledge toKnowledge) {
        this.toKnowledge = toKnowledge;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
