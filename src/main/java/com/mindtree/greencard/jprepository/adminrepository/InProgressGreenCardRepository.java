package com.mindtree.greencard.jprepository.adminrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.InProgressGreenCard;


@Repository
public interface InProgressGreenCardRepository extends JpaRepository<InProgressGreenCard, Integer>{
	@Query("SELECT i FROM InProgressGreenCard i where assignedPersonId=?1")
	public List<InProgressGreenCard> getComplaints(String mid);
	
	@Query("SELECT i FROM InProgressGreenCard i where gcId=?1")
	public InProgressGreenCard getSubadmin(int data);
}
