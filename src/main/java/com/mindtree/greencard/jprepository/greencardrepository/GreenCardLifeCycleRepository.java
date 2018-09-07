package com.mindtree.greencard.jprepository.greencardrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.NewGreenCard;
@Repository
public interface GreenCardLifeCycleRepository extends JpaRepository<GreenCardLifeCycle, Integer> {
	
	@Query(value="select g from GreenCardLifeCycle g where g.newgreencard=:newGreenCard")
	public GreenCardLifeCycle getGreenCardById(@Param("newGreenCard") NewGreenCard newGreenCard);
	
}
