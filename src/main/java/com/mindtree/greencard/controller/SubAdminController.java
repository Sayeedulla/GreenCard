package com.mindtree.greencard.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.entity.Category;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.exception.subadminserviceexception.ServiceException;
import com.mindtree.greencard.service.SubAdminService;

@RestController
@CrossOrigin
public class SubAdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubAdminController.class);

	@Autowired
	SubAdminService subserv;

	@GetMapping("/getAllComplaints/{mid}")
	public List<InProgressGreenCard> getComplaints(@PathVariable String mid) {
		List<InProgressGreenCard> compList = new ArrayList<>();
		try {
			compList = subserv.getComplaints(mid);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		return compList;
	}

	@GetMapping("/getComplaintData/{gcid}")
	public NewGreenCard getData(@PathVariable int gcid) {
		try {
			return subserv.getData(gcid);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@PostMapping("/updateComplaint")
	public String updateComplaint(@RequestBody InProgressGreenCard sub) {
		try {
			return subserv.updateComplaint(sub);
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}

	@PostMapping("/reassignComplaint")
	public String reassignComplaint(@RequestBody InProgressGreenCard sub) {
		try {
			return subserv.reassignComplaint(sub);
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}

	@GetMapping("/getCategory")
	public List<Category> getCategories() {
		List<Category> cate = new ArrayList<>();
		try {
			cate = subserv.getCategory();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		return cate;
	}

	@GetMapping("/getSubadmins/{category}")
	public List<SubAdminCategory> getSubadmins(@PathVariable String category) {
		List<SubAdminCategory> subad = new ArrayList<>();
		try {
			subad = subserv.getSubadmins(category);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		return subad;
	}

	@PostMapping("/mailadmin")
	public String sendEmail(@RequestParam("mid") String mid, int gcId, String desc) {
		try {
			return subserv.sendHelpEmail(mid, gcId, desc);
		} catch (Exception e) {
			return e.getMessage();
		}	
	}
}