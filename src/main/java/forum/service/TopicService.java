package forum.service;

import forum.dao.TopicDao;
import forum.model.Topic;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
//import java.util.*;

@Service
public class TopicService {

	@Autowired
	private TopicDao topicDao;

	//check email
	static boolean isValid(@NotNull String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
   
   //check secret
   static boolean StrongSecret(@NotNull String secret) {
	   String Regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,20})"; // must contain at least 1 uppercase, 1 lowercase, 1 numeric character and must be 8 characters or longer
	   return secret.matches(Regex);
	}

	//Add topic function
	@Transactional
	public ResponseEntity<?> addTopic(TopicDto dto) {
		if (dto.getTitle()== null) return new ResponseEntity<>("Please add a Title", HttpStatus.BAD_REQUEST);
		else if (dto.getSecret()== null || StrongSecret(dto.getSecret())) return new ResponseEntity<>("Your secret must contain at least 1 uppercase, 1 lowercase, 1 numeric character and must be 8 characters or longer", HttpStatus.BAD_REQUEST); // We could split the conditions to have personalize response messages depending on what's missing in the secret from the conditions
		else if (dto.getContent()== null) return new ResponseEntity<>("Please add your Post Content", HttpStatus.BAD_REQUEST);
		else if (dto.getNickname()== null) return new ResponseEntity<>("Please add a Nickname", HttpStatus.BAD_REQUEST);
		else if (dto.getEmail()== null || isValid(Topic.getEmail())) return new ResponseEntity<>("Please enter a valid email", HttpStatus.BAD_REQUEST);
		else {
			Topic topic = new Topic(dto.getId()); //get all parameter for this title (function in model)
			topicDao.save(topic);
			return new ResponseEntity<>("Your Topic has been successfully created", HttpStatus.CREATED);
		}
		//return toTopicDto(topic);
	}

	//Get topics function
	@Transactional(readOnly=true)
	public List<TopicDto> getAllTopics(int offset, int limit) { //offset and limit will be selected by user in front end (can be obtained from path or as direct input from user). Normally options are limit {10,25,50,100}. It could include sort parameters too
	PageRequest pageReq = PageRequest.of(offset, limit);
		return topicDao.findAllOrdered(pageReq)
			.stream()
			.map(t -> toTopicDto(t))
			.collect(Collectors.toList());
	}
	
	//Update topic function
	@Transactional
	public ResponseEntity<?> updateTopic(long id, TopicDto dto) {
		Topic dbTopic = topicDao.findOne(id); //get parameters from database/ repository
		if (dbTopic != null && dto.getSecret().equals(dbTopic.getSecret())) {//existing topic and correct secret added
			topicDao.UpdateTopic(dto.getContent(),dto.getCreateDate(),id);
			return new ResponseEntity<>("Your Topic has been successfully updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("We couldn't update your Post. Your secret is not correct.", HttpStatus.NOT_FOUND);
		}
			//return toTopicDto(dbTopic); //to return the updated values
	}
	@Transactional
	//Delete topic function
	public ResponseEntity<?> deleteTopic(@PathVariable long id, TopicDto dto) {
		Topic dbTopic = topicDao.findOne(id); //get parameters from database/ repository
		if (dbTopic != null && dto.getSecret().equals(dbTopic.getSecret())) {//existing topic and correct secret added
			try {
				topicDao.delete(dbTopic);
				return new ResponseEntity<>("Topic deleted successfully", HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("We couldn't delete your Post. Your secret is not correct.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get variable with all parameters
	private static TopicDto toTopicDto(Topic topic) {
		TopicDto dto = new TopicDto();
		dto.setId(topic.getId());
		dto.setTitle(topic.getTitle());
		//dto.setMessageCount(topic.getMessageCount());
		dto.setCreateDate(topic.getCreateDate());
		dto.setContent(topic.getContent());
		dto.setNickname(topic.getNickname());
		dto.setSecret(topic.getSecret());
		dto.setEmail(topic.getEmail());
		return dto;
	}

}