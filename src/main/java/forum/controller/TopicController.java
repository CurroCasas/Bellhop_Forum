package forum.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import forum.service.TopicDto;
import forum.service.TopicService;

import java.util.List;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	//Browse & Paginate topics at the same time
    @RequestMapping(method=RequestMethod.GET)
	@ResponseBody
    public List<TopicDto> getAllTopics(@PathVariable("page") int offset,@PathVariable("size") int limit) { // could include sort parameters too
        return topicService.getAllTopics(offset,limit);
    }

	//Create Topic
	@RequestMapping(method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<?> addTopic(@RequestBody TopicDto dto) { //return message with ok. If parameters are required for front end, we could simply return toTopicDto(topic);
		return topicService.addTopic(dto);
	}
	
	//Edit Topic
	@RequestMapping(value="/id/{id}", method=RequestMethod.PUT) //since we are updating partial information, we could also use patch
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateTopic(@PathVariable long id, @RequestBody TopicDto dto) {
		return topicService.updateTopic(id,dto);
	}
	
	//Delete Topic
	@RequestMapping(value="/id/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteTopic(@PathVariable long id, @RequestBody TopicDto dto) {
		return topicService.deleteTopic(id,dto);
	}
	
/*	//Paginate Topics
	@RequestMapping(value="/topic", method=RequestMethod.GET)
	public TopicDto paginateTopic(@RequestBody TopicDto dto) {//!!!!!!!!!!!!!!!!!!!!!!!!
		return topicService.updateTopic(dto);//!!!!!!!!!!!!!!!!!!!!!!!!
	}*/

}
