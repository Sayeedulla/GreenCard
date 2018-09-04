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
		 return this.service.deleteUser(mid);
	}

	@RequestMapping(value = "/updateUser")
	private String add(@RequestBody User user) {
		 this.service.updateUser(user);
		 return "The Database is Updated";
	}

	@RequestMapping(value = "/addCategory")
	private String add(@RequestBody Category category) {
		
		return this.service.addCategory(category);
	}

	@RequestMapping(value = "/deleteCategory/{category_name}")
	private String delete(@PathVariable String category_name) {
		return this.service.deleteCategory(category_name); 
	}

	@RequestMapping(value = "/getCategories")
	private List<Category> getCategories() {
		return this.service.getCategories();
	}

	@RequestMapping(value = "/mapSubAdminToCategory")
	private void mapSubAdminToCategory(@RequestBody SubAdminCategory subAdminCategory) {
		this.service.mapSubAdminToCategory(subAdminCategory);
	}
	
	@RequestMapping(value="/deleteMappedSubAdmin/{mid}")
	private String deleteMappedSubAdmin(@PathVariable String mid) {
		this.service.deleteMappedSubAdmin(mid);
		return "Success";
	}
	
	@RequestMapping(value="/getMappedCategory/{mid}")
	private String getMappedCategory(@PathVariable String mid) {
		return this.service.getMappedCategory(mid);
	}

}
