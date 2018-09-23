package com.mindtree.greencard.jprepository.adminrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.greencard.entity.GreenCardHistory;

public interface GreenCardHistoryRepository extends JpaRepository<GreenCardHistory,Integer>{
	
	@Query(value="select gId,landmark,submittedDateTime,closedDateTime,assignedPersonId1,status1,priority,correctiveAction1,rootCause1,whatHappened,category from GreenCardHistory")
	public List<GreenCardHistory> getAllExceptImg();
	
	@Query(value="select gId,landmark,submittedDateTime,closedDateTime,assignedPersonId1,status1,priority,correctiveAction1,rootCause1,whatHappened,category from GreenCardHistory where  assignedPersonId1=?1")
	public List<GreenCardHistory> getExceptImgForSubadmin(String mid);
	
}
