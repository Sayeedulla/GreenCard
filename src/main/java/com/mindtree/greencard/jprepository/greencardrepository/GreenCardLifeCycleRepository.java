package com.mindtree.greencard.jprepository.greencardrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.entity.GreenCardLifeCycle;
import com.mindtree.greencard.entity.NewGreenCard;

@Repository
public interface GreenCardLifeCycleRepository extends JpaRepository<GreenCardLifeCycle, Integer> {

	@Query(value = "select g from GreenCardLifeCycle g where g.newgreencard=:newGreenCard")
	public GreenCardLifeCycle getGreenCardById(@Param("newGreenCard") NewGreenCard newgreen);

	@Query(value = "select g from GreenCardLifeCycle g where g.status='Open'")
	public List<GreenCardLifeCycle> getOpenGreenCard();

	@Query(value = "select g from GreenCardLifeCycle g where g.status='Closed'")
	public List<GreenCardLifeCycle> getClosedGreenCard();

	@Query(value = "select g from GreenCardLifeCycle g where g.status='Assigned'")
	public List<GreenCardLifeCycle> getAssignedGreenCard();

	@Query(value = "select g from GreenCardLifeCycle g where g.status='rejected'")
	public List<GreenCardLifeCycle> getRejectedGreenCard();

	@Query(value = "select g from GreenCardLifeCycle g")
	public List<GreenCardLifeCycle> getFullGreenCard();

}
