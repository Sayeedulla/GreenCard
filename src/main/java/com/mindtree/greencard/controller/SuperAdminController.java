package com.mindtree.greencard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.SuperAdminHistory;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.SuperAdminService;


@RestController
@RequestMapping(value = "/GreenCard")
@CrossOrigin
public class SuperAdminController {
	@Autowired
	private SuperAdminService service;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(SuperAdminController.class);

	@RequestMapping(value = "/getUsers")
	public List<User> getUsers() {
		return this.service.getUsers();
	}

	@RequestMapping(value = "/updateUser")
	public String updateUser(@RequestBody User user) {
		try {
			String returnMessage=this.service.updateUser(user);
			LOGGER.info("Successfully updated User");
		return returnMessage;
		}catch(SuperAdminServiceException e) {
           LOGGER.error(e.getMessage());
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/addCategory")
	public String addCategory(@RequestBody Category category) {

		try {
			String returnMessage=this.service.addCategory(category);
			LOGGER.info("Successfully added Category ");
			return returnMessage;
		} catch (SuperAdminServiceException e) {
			LOGGER.error(e.getMessage());
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/deleteCategory/{categoryName}")
	public String deleteCategory(@PathVariable String categoryName) {
		try {
			String returnMessage=this.service.deleteCategory(categoryName);
			LOGGER.info("Successfully deleted Category");
			return returnMessage;
		} catch (SuperAdminServiceException e) {
			LOGGER.error(e.getMessage());
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getCategories")
	public List<Category> getCategories() {
		return this.service.getCategories();
	}

	@RequestMapping(value = "/mapSubAdminToCategory")
	public String mapSubAdminToCategory(@RequestBody SubAdminCategory subAdminCategory) {
		try {
			String returnMessage=this.service.mapSubAdminToCategory(subAdminCategory);
			LOGGER.info("Successfully mapped Subadmin to Category");
		return returnMessage;
		} catch (SuperAdminServiceException e) {
			LOGGER.error(e.getMessage());
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/deleteMappedSubAdmin/{mid}")
	public String deleteMappedSubAdmin(@PathVariable String mid) {
	   try {
		   String returnMessage=this.service.deleteMappedSubAdmin(mid);
		   LOGGER.info("Successfully deleted Mapping between subAdmin and Category");
		   return returnMessage;
	   }catch(SuperAdminServiceException e) {
		   LOGGER.error(e.getMessage());
		   return e.getMessage();
	   }
		
	}
	
	@RequestMapping(value="/getMappedSubAdmins")
	public List<SubAdminCategory> getMappedSubAdmins(){
		return this.service.getMappedSubAdmins();
	}
	
	@GetMapping(value="/getSuperAdminHistory")
	public List<SuperAdminHistory> getSuperAdminHistory()
	{
		return this.service.getSuperAdminHistory();
	}

}
