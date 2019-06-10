package forum.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
public class Topic {

	@Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
	private static long id;
	
	@Column(name="title", nullable=false, length=200)
	private String title;
	
	@Column(name="secret", nullable=false, length=20)
	private String secret;
	
	@Column(name="content", nullable=false, length=2500)
	private String content;
	
	@Column(name="atime", nullable=false)
	private Date createDate; //format should match database format
	
	@Column(name="nickname", nullable=false, length=100)
	private String nickname;
	
	@Column(name="email", nullable=false, length=100)
	private static String email;

	public Topic(long id) { //function to get parameters
		this.id = id;
		this.title = title;
		this.secret = secret; //user to add secret: makes more sense than the Web providing it. This way the user can use same one for all and don't forget the one provided
		this.content = content;
		this.createDate = new Date();
		this.nickname = nickname;
		this.email = email; 
	}

	Topic() { }

	public static long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public static String getEmail() {
		return email;
	}

}
