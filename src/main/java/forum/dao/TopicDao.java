package forum.dao;

import forum.model.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

//DAO is class with operations
public interface TopicDao extends JpaRepository<Topic, Long>, PagingAndSortingRepository<Topic, Long> {

	@Query("SELECT t FROM Topic t ORDER BY t.time ASC") //would make more sense to do it from most active to least using DESC.
	List<Topic> findAllOrdered(Pageable PageReq); // could include sort parameters too

	//find by id
	@Query("SELECT t FROM Topic t WHERE t.id= :id")
	Topic findOne(@Param("id") long id);

	//update function
	@Query("UPDATE Topic SET content= :content and time= :time WHERE id= :id")
	void UpdateTopic(@Param("content") String content, @Param("time") Date createDate, @Param("id") long id);

}
