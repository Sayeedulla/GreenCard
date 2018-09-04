package com.mindtree.greencard.jprepository.superadminrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.model.SubAdminCategory;

@Repository
public interface SubAdminCategoryRepository extends JpaRepository<SubAdminCategory, String> {

	@Query(value="select s from SubAdminCategory s where s.Category_Name=:categoryName")
	List<SubAdminCategory> getSubAdminCategories(@Param("categoryName")String categoryName); 
}
