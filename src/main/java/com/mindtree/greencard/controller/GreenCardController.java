package com.mindtree.greencard.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.mindtree.greencard.model.User;
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

	@GetMapping("/")
	public String get() {
		return "hello world";
	}

	
	@PostMapping(value = "/add/greenCard")
	public String saveNewGreenCardService(@RequestParam("file") CommonsMultipartFile fileupload, String what, String location,String mid) {
		return this.greenCardService.SaveNewGreenCard(fileupload, what, location, mid);
	}
	@PostMapping(value="/getuserinfo")
	public User returnUser(@RequestBody User user) {
		return userservice.getUserInfoByMidAndPassword(user);
	}
	@PostMapping(value = "/add/greenCardByGuest")
	public String saveNewGreenCardServiceByGuest(@RequestParam("file") CommonsMultipartFile fileupload, String what, String location,String name,BigInteger phone) {
		return this.greenCardService.SaveNewGreenCardByGuest(fileupload, what, location, name, phone);
	}
	
}
