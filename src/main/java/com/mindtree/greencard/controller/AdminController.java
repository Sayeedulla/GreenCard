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

import com.mindtree.greencard.exception.adminexceptions.AdminException;
import com.mindtree.greencard.entity.GreenCardHistory;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;
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
	
	@GetMapping("/newcount")
	public int newcount() {
		return this.adminservice.newcount();
	}

	@GetMapping("/getCard/{gid}")
	public Optional<NewGreenCard> getCard(@PathVariable int gid) {
		
		Optional<NewGreenCard> newgreencard = null;
		
		try {
			newgreencard= this.adminservice.getCard(gid);
		} catch (AdminException e) {
			
			
			
		}
		
		return newgreencard;
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
	

	
	@GetMapping("/historysubadmin/{mid}")
	public List<GreenCardHistory> getForSubAdmin(@PathVariable String mid)
	{
		return this.adminservice.getForSubadmin(mid);
		
	}
	
	@GetMapping("/getSubAdminCategory")
	public List<SubAdminCategory> getSubAdmins(){
		return this.adminservice.getSubAdmins();
	}
	

	@GetMapping("/reject/{gid}")
	public String rejectGreenCard(@PathVariable int gid) {
		return this.adminservice.rejectGreenCard(gid);
	}
	
	@GetMapping("/resolve/{gid}/{rootcause}/{correctiveaction}")
	public String resolveCard(@PathVariable int gid,@PathVariable String rootcause,@PathVariable String correctiveaction) {
		return this.adminservice.resolveCard(gid,rootcause,correctiveaction);
	}

	@GetMapping("/assignedcount")
	public int assignedcount() {
		return this.adminservice.assignedcount();
		
	}
	@GetMapping("/closedcount")
	public int closedcount() {
		return this.adminservice.closedcount();
	}
	@GetMapping("/rejectcount")
	public int rejectcount() {
		return this.adminservice.rejectcount();
	}
	
	@GetMapping("/fullcount")
	public int fullcount() {
		return this.adminservice.fullcount();
	}


}
