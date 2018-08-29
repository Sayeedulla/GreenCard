package com.mindtree.greencard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.dto.DTOClass;
import com.mindtree.greencard.service.SaveNewGreenCardService;

@RestController
@CrossOrigin
public class GreenCardController {

	@Autowired
	SaveNewGreenCardService saveNewGreenCardService;
	
	
	@GetMapping("/")
	public String get() {
		return "hello world";
	}
	
	@PostMapping(value="/add/greenCard")
	public String saveNewGreenCardService(@RequestBody DTOClass dtoClass) {
	//	System.out.println(newGreenCard.getLandmark()+"Hi");
		return this.saveNewGreenCardService.saveNewGreenCard(dtoClass);
	}
	
}