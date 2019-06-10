package forum.service;

import forum.dao.MessageDao;
import forum.dao.TopicDao;
import forum.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private TopicDao topicDao;

	//Get replies 
	@Transactional(readOnly=true)
	public List<MessageDto> getMessagesForTopic(int topic_id) {
		return messageDao.Ordermessages(topic_id)
		    .stream()
		    .map(m -> toMessageDto(m))
		    .collect(Collectors.toList());
	}
	
	//check email
	static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
   
   //check secret
   static boolean StrongSecret(String secret) {
	   String Regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})"; // must contain at least 1 uppercase, 1 lowercase, 1 numeric character and must be 8 characters or longer
	   return secret.matches(Regex);
	}

	//Add reply to topic
	@Transactional
	public ResponseEntity<?> addMessagesForTopic(int topic_id, MessageDto dto) {
		if (dto.getSecret()== null || StrongSecret(dto.getSecret())) return new ResponseEntity<>("Your secret must contain at least 1 uppercase, 1 lowercase, 1 numeric character and must be 8 characters or longer", HttpStatus.BAD_REQUEST); // We could split the conditions to have personalize response
		else if (dto.getContent()== null) return new ResponseEntity<>("Please add your Reply Content", HttpStatus.BAD_REQUEST);
		else if (dto.getNickname()== null) return new ResponseEntity<>("Please add a Nickname", HttpStatus.BAD_REQUEST);
		else if (dto.getEmail()== null || isValid(dto.getEmail())) return new ResponseEntity<>("Please enter a valid email", HttpStatus.BAD_REQUEST);
		else {
			Message message = new Message(dto.getId());//get parameters for this content and topic (function in model)
			messageDao.save(message);
			messageDao.UpdateTopicTime(dto.getCreateTime(),topic_id); //update activity time for topic
			return new ResponseEntity<>("Your Reply has been successfully created", HttpStatus.CREATED);
			//return toMessageDto(message); //to return the added values
		}
	}
	
	//Update reply function
	@Transactional
	public ResponseEntity<?> updateReply(long id, MessageDto dto) {
		Message dbMessage = messageDao.findOne(id); //get parameters from database/ repository
		if (dbMessage != null && dto.getSecret().equals(dbMessage.getSecret())) {//existing topic and correct secret added
			messageDao.UpdateMessage(dto.getContent(),dto.getCreateTime(),id); // simpler way through function
			messageDao.UpdateTopicTime(dto.getCreateTime(),dto.getTopicId());
			return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
			//return toMessageDto(dbMessage); //to return the updated values
		} else {
			return new ResponseEntity<>("We couldn't update your Post. Your secret is not correct.", HttpStatus.NOT_FOUND);
		}
	}
	//Delete reply function
	@Transactional
	public ResponseEntity<?> deleteReply(@PathVariable long id, MessageDto dto) {
		Message dbMessage = messageDao.findOne(id); //get parameters from database/ repository
		if (dbMessage != null && dto.getSecret().equals(dbMessage.getSecret())) {//existing topic and correct secret added
			try {
				messageDao.delete(dbMessage);
				return new ResponseEntity<>("Reply deleted successfully.", HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>("Resource not found.", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("We couldn't delete your Post. Your secret is not correct.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get variable with all parameters
	private static MessageDto toMessageDto(Message message) {
		MessageDto dto = new MessageDto();
		dto.setId(message.getId());
		dto.setTopicId(message.getTopicId());
		dto.setCreateTime(message.getCreateTime());
		dto.setContent(message.getContent());
		dto.setSecret(message.getSecret());
		dto.setNickname(message.getNickname());
		dto.setEmail(message.getEmail());
		return dto;
	}
	
	//Paginate reply with pivot method
	@Transactional
	public List<MessageDto> paginateReply(int topic_id, ReplyPaginate pag) {
		//check inputs
		//Order replies - check pivot exist - check before and after enough - get all in order
		int offset;
		int limit;
		int creply = messageDao.countreply(pag.getPivot());
		if(creply<pag.getReplybefore()){
			limit = pag.getReplyafter() + creply + 1;
			offset = 0; //avoid negative offset since sql does not support it
		} else {
			limit = pag.getReplyafter() + pag.getReplybefore() + 1; //if limit goes over maximum, sql still works
			offset = creply - pag.getReplybefore();
		}
		return messageDao.paginate(topic_id, offset, limit)
				.stream()
				.map(m -> toMessageDto(m))
				.collect(Collectors.toList());
	}

}
