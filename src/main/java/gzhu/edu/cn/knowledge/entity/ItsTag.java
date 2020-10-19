package gzhu.edu.cn.knowledge.entity;

import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program: exam
 * @description: 标签系统
 * @author: 丁国柱
 * @create: 2020-10-18 16:48
 */
@Entity(name = "its_tag")
public class ItsTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}