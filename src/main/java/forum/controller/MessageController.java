package forum.controller;

import forum.service.MessageDto;
import forum.service.MessageService;
import forum.service.ReplyPaginate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	//Browse replies (messages)
    @RequestMapping(value="/topic_id/{topic_id}/", method=RequestMethod.GET)
    public List<MessageDto> getMessage(@PathVariable int topic_id) {
        return messageService.getMessagesForTopic(topic_id);
    }
	//Create reply (message)
    @RequestMapping(value="/topic_id/{topic_id}", method=RequestMethod.POST)
    public ResponseEntity<?> addMessage(@PathVariable int topic_id, @RequestBody MessageDto dto) {
        return messageService.addMessagesForTopic(topic_id, dto);
    }
	//Edit reply
	@RequestMapping(value="/topic_id/{topic_id}/id/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateReply(@PathVariable long id, @RequestBody MessageDto dto) {
    	return messageService.updateReply(id,dto);
	}
	//Delete reply
	@RequestMapping(value="/topic_id/{topic_id}/id/{id}", method=RequestMethod.DELETE) //even though topic_id is not needed, it is included to avoid errors from id when using toppics
	public ResponseEntity<?> deleteReply(@PathVariable long id, @RequestBody MessageDto dto) {
		return messageService.deleteReply(id, dto);
	}
	//Paginate replies (messages)
	@RequestMapping(value="/topic_id/{topic_id}/pivot/{pivot}", method=RequestMethod.GET)
    public List<MessageDto> paginateReply(@PathVariable int topic_id, @RequestBody ReplyPaginate pag ) {
        return messageService.paginateReply(topic_id, pag);
    }

}
