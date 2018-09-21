package com.mindtree.greencard.service;

import java.util.List;

import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.entity.Category;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.entity.SuperAdminHistory;
import com.mindtree.greencard.entity.User;

public interface SuperAdminService {

	public List<User> getUsers();

	public String updateUser(User user) throws SuperAdminServiceException; 

	public String addCategory(Category category) throws SuperAdminServiceException;

	public String deleteCategory(String categoryName) throws SuperAdminServiceException;

	public List<Category> getCategories();

	public String mapSubAdminToCategory(SubAdminCategory subAdminCategory) throws SuperAdminServiceException;

	public String deleteMappedSubAdmin(String mid) throws SuperAdminServiceException;

	public List<SubAdminCategory> getMappedSubAdmins();
	
	public List<SuperAdminHistory> getSuperAdminHistory();

}
