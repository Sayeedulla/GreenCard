package com.mindtree.greencard.jprepository.superadminrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.SuperAdminHistory;

@Repository
public interface SuperAdminHistoryRepo extends JpaRepository<SuperAdminHistory,Integer>{
	


}
