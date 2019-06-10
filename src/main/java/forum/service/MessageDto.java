package forum.service;

//DTO is just an object that contains data
import java.util.Date;

//If we need to change date format:
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class MessageDto {

	private long id;

	private String content;

	private Date createTime;

	private String secret;

	private String nickname;

	private String email;

	private int topic_id;
	 
  	/*public static void main(String[] args) { 
		LocalDateTime myDateObj = LocalDateTime.now(); 
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		Date DateNow = myDateObj.format(myFormatObj); 
	  } 
	public static void main(String args[]) {
      Date dNow = new Date( );
      SimpleDateFormat ft = 
      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	  Date DateNow = ft.format(dNow)
   }*/ //IF DATE FORMAT DOES NOT MATCH DATABASE FORMAT, WE SHOULD MAKE IT MATCH

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getTopicId() {
		return topic_id;
	}

	public void setTopicId(int topic_id) {
		this.topic_id = topic_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
