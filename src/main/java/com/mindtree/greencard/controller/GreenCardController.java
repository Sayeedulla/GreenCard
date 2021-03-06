package com.mindtree.greencard.controller;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.entity.FeedBack;
import com.mindtree.greencard.entity.GreenCardLifeCycle;
import com.mindtree.greencard.entity.User;
import com.mindtree.greencard.service.GreenCardService;
import com.mindtree.greencard.service.UserService;

@RestController
@RequestMapping(value = "/GreenCard")
@CrossOrigin

public class GreenCardController {

	@Autowired
	UserService userservice;
	@Autowired
	GreenCardService greenCardService;
	private static final Logger LOGGER=LoggerFactory.getLogger(GreenCardController.class);
	
	@PostMapping(value = "/add/greenCard")
	public String saveNewGreenCardService(@RequestParam("file") CommonsMultipartFile fileupload, String what, String location,String mid) {
		try {
			return this.greenCardService.saveNewGreenCard(fileupload, what, location, mid);
			
		}catch(GreenCardException e) {
			LOGGER.error(e.getMessage());
		}
		return "Sorry can't save the green card";
		
	}
	@PostMapping(value="/getuserinfo")
	public User returnUser(@RequestBody User user) {
		User userByMidPass=new User();
		try {
			userByMidPass= userservice.getUserInfoByMidAndPassword(user);
		} catch (GreenCardException e) {	
				LOGGER.error(e.getMessage());
		}
		return userByMidPass;
	}
	@PostMapping(value = "/add/greenCardByGuest")
	public String saveNewGreenCardServiceByGuest(@RequestParam("file") CommonsMultipartFile fileupload,String what, String location,String name,BigInteger phone) {
		try {
		  	return this.greenCardService.saveNewGreenCardByGuest(fileupload, what, location, name, phone);
		 
		}catch(GreenCardException e) {
			LOGGER.error(e.getMessage());
		}
		return "Sorry can't save the green card";
	}
	@GetMapping(value = "viewGreenCard/{id}")
	public GreenCardLifeCycle getGreenCardById(@PathVariable int id) {
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		try {
			greenCardLifeCycle= this.greenCardService.getGreenCardById(id);
		}catch(GreenCardException e) {
			LOGGER.error(e.getMessage());
		}
		return greenCardLifeCycle;
		
	}
	@PostMapping(value="/feedback")
	public String sendFeedback(@RequestBody FeedBack feedback) {
		try {
			return userservice.saveFeedBack(feedback);
			
		}catch(GreenCardException e) {
			LOGGER.error(e.getMessage());
			return "can't save feedback";
		}
		 
	}
	
	@PostMapping(value="/userInfoByMid")
	public String getUserInfoByMid(@RequestBody User user) {
		try {
			return userservice.getUserInfoByMid(user);
			
			 
		}catch(GreenCardException e) {
			LOGGER.error(e.getMessage());
			return "sorry can't return the status";
		}
		
	}
}
