package gzhu.edu.cn.experiment.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.system.entity.User;


/**
 * 标签编辑历史
 * @author dingguozhu
 *
 */
@Entity(name="tag_edit_history")
public class TagEditHistory extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String wordString;
	
	@ManyToOne
	@JoinColumn(name="word_id")
	private Word word;
	
	@ManyToOne
	@JoinColumn(name="topic_id")
	private Topic topic;
	
	@Column(name = "operateInfo", columnDefinition = "text")
	private String operateInfo;
	
	//修改的类型
	private String changeType;
	
	private Date createTime;
	
	private int positionX;
	
	private int positionY;
	
	private String size;
	
	private String font;
	
	private String color;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getWordString() {
		return wordString;
	}

	public void setWordString(String wordString) {
		this.wordString = wordString;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public String getOperateInfo() {
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}
	
}
