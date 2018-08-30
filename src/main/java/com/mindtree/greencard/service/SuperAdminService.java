package com.mindtree.greencard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.User;

@Service
public interface SuperAdminService {

	public void updateUser(User user);

	public List<User> getUsers();

	public Optional<User> getUser(String mid);

	public void deleteUser(String mid);

	public void addCategory(Category category);

	public void deleteCategory(String Category_name);

	public List<Category> getCategories();

	public void mapSubAdminToCategory(SubAdminCategory subAdminCategory);

}
