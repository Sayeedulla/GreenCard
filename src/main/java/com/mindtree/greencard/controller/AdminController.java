package com.mindtree.greencard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.service.AdminService;

@RestController
@RequestMapping(value = "/GreenCard/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	AdminService adminservice;

	@GetMapping("/newgreencards")
	public List<NewGreenCard> newComplaints() {

		return this.adminservice.newComplaints();
	}

	@GetMapping("/getCard")
	public Optional<NewGreenCard> getCard(@PathVariable int gid) {
		
		return this.adminservice.getCard(gid);
	}

	@GetMapping("/getprogress")
	public List<InProgressGreenCard> viewprogress() {
		
		return this.adminservice.viewprogress();

	}

	@PostMapping("/assigncard")
	public String assigncard(InProgressGreenCard card) {
		
		return this.adminservice.assigncard(card);

	}

}
