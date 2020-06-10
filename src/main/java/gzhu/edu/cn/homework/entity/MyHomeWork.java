package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.system.entity.User;

import javax.persistence.*;

/**
 * @program: exam
 * @description: 我的作业
 * @author: 丁国柱
 * @create: 2020-05-27 22:46
 */
@Entity(name="myhomework")
public class MyHomeWork extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //谁的作业
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User user;

    private float score;

	@ManyToOne
    @JoinColumn(name = "homework_id")
    private HomeWork homeWork;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }
    public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}