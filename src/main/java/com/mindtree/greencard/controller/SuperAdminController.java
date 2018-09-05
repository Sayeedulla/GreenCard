package com.mindtree.greencard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(value = "/addUser")
	private String addUser(@RequestBody User user) {
		return this.service.addUser(user);
	}

	@RequestMapping(value = "/getUsers")
	private List<User> get() {
		return this.service.getUsers();
	}

	@RequestMapping(value = "/getUser/{mid}")
	private User getUser(@PathVariable String mid) {
		return this.service.getUser(mid);
	}

	@DeleteMapping(value = "/deleteUser/{mid}")
	private String deleteUser(@PathVariable String mid) {
		return this.service.deleteUser(mid);
	}

	@RequestMapping(value = "/updateUser")
	private String add(@RequestBody User user) {
		this.service.updateUser(user);
		return user.getMid();
	}

	@RequestMapping(value = "/addCategory")
	private String add(@RequestBody Category category) {

		return this.service.addCategory(category);
	}

	@RequestMapping(value = "/deleteCategory/{categoryName}")
	private String delete(@PathVariable String categoryName) {
		return this.service.deleteCategory(categoryName);
	}

	@RequestMapping(value = "/getCategories")
	private List<Category> getCategories() {
		return this.service.getCategories();
	}

	@RequestMapping(value = "/mapSubAdminToCategory")
	private void mapSubAdminToCategory(@RequestBody SubAdminCategory subAdminCategory) {
		this.service.mapSubAdminToCategory(subAdminCategory);
	}

	@RequestMapping(value = "/deleteMappedSubAdmin/{mid}")
	private String deleteMappedSubAdmin(@PathVariable String mid) {
		this.service.deleteMappedSubAdmin(mid);
		return "Success";
	}

	@RequestMapping(value = "/getMappedCategory/{mid}")
	private String getMappedCategory(@PathVariable String mid) {
		return this.service.getMappedCategory(mid);
	}

}
