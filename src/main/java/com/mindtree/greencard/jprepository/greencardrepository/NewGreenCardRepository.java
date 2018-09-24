package com.mindtree.greencard.jprepository.greencardrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mindtree.greencard.entity.NewGreenCard;

public interface NewGreenCardRepository extends JpaRepository<NewGreenCard, Integer> {
	
	@Query(value="select greenCardId,whatHappened,landmark,submittedDate from NewGreenCard where status='Open'")
	public List<NewGreenCard> getNewCards();
	
	@Query(value="select g from NewGreenCard g where g.greenCardId=:gid")
	public NewGreenCard getNewCard(@Param("gid") int g);
	
	@Query(value="select image,landmark from NewGreenCard g where g.status='Open' order by g.greenCardId desc")
	public List<NewGreenCard> getBeware();
	
}
