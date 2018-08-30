package com.mindtree.greencard.jprepository.superadminrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.SubAdminCategory;

@Repository
public interface SubAdminCategoryRepository extends JpaRepository<SubAdminCategory, String> {

}
