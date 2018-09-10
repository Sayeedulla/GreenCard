package com.mindtree.greencard.jprepository.greencardrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mindtree.greencard.model.NewGreenCard;

public interface NewGreenCardRepository extends JpaRepository<NewGreenCard, Integer> {
	
	@Query(value="select greenCardId,whatHappened,landmark,submittedDate from NewGreenCard")
	public List<NewGreenCard> getNewCards();
	
	@Query(value="select greenCardId,whatHappened,landmark,submittedDate from NewGreenCard where greenCardId=:gid")
	public NewGreenCard getNewCard(@Param("gid") int g);
	
}
