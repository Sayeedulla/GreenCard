package com.mindtree.greencard.jprepository.AdminRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.greencard.model.GreenCardHistory;

public interface GreenCardHistoryRepository extends JpaRepository<GreenCardHistory,Integer>{
	

}
