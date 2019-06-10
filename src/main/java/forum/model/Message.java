package forum.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;

	@Column(name="topic_id",nullable=false)
	private int topic_id;
	
	@Column(name="content",nullable=false, length=2500)
	private String content;
	
	@Column(name="atime",nullable=false)
	private Date createTime; //format should match database format

	@Column(name="email",nullable=false, length=100)
	private String email;
	
	@Column(name="nickname",nullable=false, length=100)
	private String nickname;
	
	@Column(name="secret",nullable=false, length=20)
	private String secret;
	
	/*@ManyToOne(optional=false)
	private Topic topic;*/

	public Message(long id) { //function to get parameters
		this.id = id;
		this.createTime = new Date();
		this.content = content;
		this.topic_id = topic_id;
		this.nickname = nickname;
		this.email = email;
		this.secret = secret;
	}

	Message() { }
//topic.getId()

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTopicId() {
		int topic_id = (int) Topic.getId();
		return topic_id;
	}

	public void setTopicId(int topic_id) {
		this.topic_id = topic_id;
	}

}
