package com.mindtree.greencard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.SuperAdminService;

@RestController
@RequestMapping(value = "/GreenCard")
@CrossOrigin
public class SuperAdminController {
	@Autowired
	private SuperAdminService service;

	@RequestMapping(value = "/getUsers")
	private List<User> get() {
		return this.service.getUsers();
	}

	@RequestMapping(value = "/getUser/{mid}")
	private Optional<User> getUser(@PathVariable String mid) {
		return this.service.getUser(mid);
	}

	@DeleteMapping(value = "/deleteUser/{mid}")
	private String deleteUser(@PathVariable String mid) {
		this.service.deleteUser(mid);
		return "The records of mid: " + mid + " has been deleted";
	}

	@RequestMapping(value = "/updateUser")
	private String add(@RequestBody User user) {
		this.service.updateUser(user);
		return user.getMid();
	}

	@RequestMapping(value = "/addCategory")
	private String add(@RequestBody Category category) {
		this.service.addCategory(category);
		return category.getCategory_Name();
	}

	@RequestMapping(value = "/deleteCategory/{category_name}")
	private String delete(@PathVariable String category_name) {
		this.service.deleteCategory(category_name);
		return category_name;
	}

	@RequestMapping(value = "/getCategories")
	private List<Category> getCategories() {
		return this.service.getCategories();
	}

	@RequestMapping(value = "/mapSubAdminToCategory")
	private void mapSubAdminToCategory(@RequestBody SubAdminCategory subAdminCategory) {
		this.service.mapSubAdminToCategory(subAdminCategory);
	}

}
