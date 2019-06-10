package forum.dao;

import forum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

//DAO is class with operations
public interface MessageDao extends JpaRepository<Message, Long> {

	//function to sort messages for specific topic
	@Query("SELECT u FROM Message u WHERE u.topic_id= :topic_id ORDER BY u.time ASC")
		List<Message> Ordermessages(@Param("topic_id") int topic_id);
	
	//function to update reply
	@Query("UPDATE Message SET content= :content and time= :time WHERE id= :id")
		void UpdateMessage(@Param("content") String content, @Param("time") Date createTime, @Param("id") long id);
	
	//function to update "activity" time in topic
	@Query("UPDATE Topic SET time= :time WHERE id= :id")
		void UpdateTopicTime(@Param("time") Date createDate, @Param("id") int id);
		
	//function to count replies before pivot
	@Query("SELECT count(id) from Topic where time<(select time from Topic where id= :pivot) order by time ASC")
		int countreply(@Param("pivot") int pivot);

	//find by id
	@Query("SELECT t FROM Message t WHERE t.id= :id")
	Message findOne(@Param("id") long id);

	//Paginate with pivot
	@Procedure(procedureName = "paginate")//Created a procedure (check resources V_3 file) to allow to use variable limit and offset. Direct set as variable does not work
	List<Message> paginate(@Param("topic_id") int topic_id, @Param("offset") int offset, @Param("limit") int limit);
}

