package com.mindtree.greencard.jprepository.superadminrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindtree.greencard.entity.SuperAdminHistory;

@Repository
public interface SuperAdminHistoryRepo extends JpaRepository<SuperAdminHistory, Integer> {

}
