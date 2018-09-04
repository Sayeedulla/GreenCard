package com.mindtree.greencard.service;

import java.util.List;
import java.util.Optional;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.User;


public interface SuperAdminService {

	public List<User> getUsers();

	public Optional<User> getUser(String mid);

	public String deleteUser(String mid);

	public void updateUser(User user);

	public String addCategory(Category category);

	public String deleteCategory(String categoryName);

	public List<Category> getCategories();

	public void mapSubAdminToCategory(SubAdminCategory subAdminCategory);

	public void deleteMappedSubAdmin(String mid);

	public String getMappedCategory(String mid);

}
