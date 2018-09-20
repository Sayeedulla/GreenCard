package com.mindtree.greencard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.exception.subadminserviceexception.ComplaintNotFoundException;
import com.mindtree.greencard.exception.subadminserviceexception.EmptyListException;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
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
		List<InProgressGreenCard> compList = new ArrayList<InProgressGreenCard>();
		try {
			compList = subserv.getComplaints(mid);
		} catch (EmptyListException e) {
			System.out.println("List is empty");
		}
		return compList;
	}

	@GetMapping("/getComplaintData/{gcid}")
	public NewGreenCard getData(@PathVariable int gcid) {
		try {
			return subserv.getData(gcid);
		} catch (Exception e) {
			System.out.println("Particular Complaint not exist");
		}
		return null;
	}

	@PostMapping("/updateComplaint")
	public String updateComplaint(@RequestBody InProgressGreenCard sub) {
		try {
			return subserv.updateComplaint(sub);
		} catch (ComplaintNotFoundException e) {
			return "Requested Complaint not exist";
		}
	}

	@PostMapping("/reassignComplaint")
	public String reassignComplaint(@RequestBody InProgressGreenCard sub) {
		try {
			return subserv.reassignComplaint(sub);
		} catch (ComplaintNotFoundException e) {
			return "Requested Complaint not exist";
		}
	}

	@GetMapping("/getCategory")
	public List<Category> getCategories() {
		List<Category> cate = new ArrayList<Category>();
		try {
			cate = subserv.getCategory();
		} catch (EmptyListException e) {
			System.out.println("List is empty");
		}
		return cate;
	}

	@GetMapping("/getSubadmins/{category}")
	public List<SubAdminCategory> getSubadmins(@PathVariable String category) {
		List<SubAdminCategory> subad = new ArrayList<SubAdminCategory>();
		try {
			subad = subserv.getSubadmins(category);
		} catch (EmptyListException e) {
			System.out.println("List is empty");
		}
		return subad;
	}

	@PostMapping("/mailadmin")
	public String SendEmail(@RequestParam("mid") String mid, int gc_id, String desc) {
		return subserv.sendHelpEmail(mid, gc_id, desc);
	}
}