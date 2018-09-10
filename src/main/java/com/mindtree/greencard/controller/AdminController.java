package com.mindtree.greencard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
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

	@GetMapping("/getCard/{gid}")
	public Optional<NewGreenCard> getCard(@PathVariable int gid) {
		
		return this.adminservice.getCard(gid);
	}

	@GetMapping("/getprogress")
	public List<InProgressGreenCard> viewprogress() {
		
		return this.adminservice.viewprogress();

	}
	
	@GetMapping("/getprogresscard/{gid}")
	public Optional<InProgressGreenCard> getprogresscard(@PathVariable int gid) {
		return this.adminservice.getprogressCard(gid);
	}

	@PostMapping("/assigncard")
	public String assigncard(@RequestBody InProgressGreenCard card) {
		
		return this.adminservice.assigncard(card);

	}
	
	@GetMapping("/gethistory")
	public List<GreenCardHistory> getAllFromHistory()
	{
		return this.adminservice.getAllFromHistory();
	}
	
	@GetMapping("/getCardhistory/{gId}")
	public Optional<GreenCardHistory> getByGid(@PathVariable int gId) {
		
		return this.adminservice.getByGid(gId);
	}
	
	@GetMapping("/downloadxl")
	public void downloadXl()
	{
		this.adminservice.generateXl();
		
	}
	
	@GetMapping("/getSubAdminCategory")
	public List<SubAdminCategory> getSubAdmins(){
		return this.adminservice.getSubAdmins();
	}

}
