package com.mindtree.greencard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.greencard.exception.subadminserviceexception.ServiceException;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.service.SubAdminService;

@RestController
@CrossOrigin
public class SubAdminController {

	private static final Logger LOGGER=LoggerFactory.getLogger(SubAdminController.class);
	@Autowired
	SubAdminService subserv;

	

	@GetMapping("/getAllComplaints/{mid}")
	public List<InProgressGreenCard> getComplaints(@PathVariable String mid) {
		List<InProgressGreenCard> compList = new ArrayList<InProgressGreenCard>();
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
	public String updateComplaint(@Valid @RequestBody InProgressGreenCard sub,BindingResult bb) {
		try {
			if(bb.hasErrors())
				throw new ServiceException("RootCause and CorrectiveAction must be in format [A-Za-z ,.:;]");
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
		List<Category> cate = new ArrayList<Category>();
		try {
			cate = subserv.getCategory();
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		return cate;
	}

	@GetMapping("/getSubadmins/{category}")
	public List<SubAdminCategory> getSubadmins(@PathVariable String category) {
		List<SubAdminCategory> subad = new ArrayList<SubAdminCategory>();
		try {
			subad = subserv.getSubadmins(category);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		return subad;
	}

	@PostMapping("/mailadmin")
	public String SendEmail(@RequestParam("mid") String mid, int gc_id, String desc) {
		return subserv.sendHelpEmail(mid, gc_id, desc);
	}
}