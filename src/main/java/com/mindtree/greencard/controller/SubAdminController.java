package com.mindtree.greencard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.service.SubAdminService;

@RestController
@CrossOrigin
public class SubAdminController {

	@Autowired
	SubAdminService subserv;

	@GetMapping("/")
	public String opening() {
		return "Welcome to GreenCard";
	}

	@GetMapping("/getAllComplaints/{mid}")
	public List<InProgressGreenCard> getComplaints(@PathVariable String mid) {
		return subserv.getComplaints(mid);
	}

	@GetMapping("/getComplaintData/{gcid}")
	public NewGreenCard getData(@PathVariable int gcid) {
		return subserv.getData(gcid);
	}

	@PostMapping("/updateComplaint")
	public String updateComplaint(@RequestBody InProgressGreenCard sub) {
		return subserv.updateComplaint(sub);
	}
	
	@PostMapping("/reassignComplaint")
	public String reassignComplaint(@RequestBody InProgressGreenCard sub) {
		return subserv.reassignComplaint(sub);
	}
}
