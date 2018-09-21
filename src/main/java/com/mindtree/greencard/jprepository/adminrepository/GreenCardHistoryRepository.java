package com.mindtree.greencard.jprepository.adminrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.greencard.model.GreenCardHistory;

public interface GreenCardHistoryRepository extends JpaRepository<GreenCardHistory,Integer>{
	
	@Query(value="select gId,landmark,submittedDateTime,closedDateTime,assignedPersonId,status,priority,correctiveAction,rootCause,whatHappened,category from GreenCardHistory")
	public List<GreenCardHistory> getAllExceptImg();
	
	@Query(value="select gId,landmark,submittedDateTime,closedDateTime,assignedPersonId,status,priority,correctiveAction,rootCause,whatHappened,category from GreenCardHistory where  assignedPersonId=?1")
	public List<GreenCardHistory> getExceptImgForSubadmin(String mid);
	
}
