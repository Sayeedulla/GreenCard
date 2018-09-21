package com.mindtree.greencard.jprepository.adminrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.entity.InProgressGreenCard;


@Repository
public interface InProgressGreenCardRepository extends JpaRepository<InProgressGreenCard, Integer>{
	@Query("SELECT i FROM InProgressGreenCard i where assigned_person_id=?1")
	public List<InProgressGreenCard> getComplaints(String mid);
	
	
}
