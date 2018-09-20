package com.mindtree.greencard.service;

import java.util.List;

import com.mindtree.greencard.exception.subadminserviceexception.ComplaintNotFoundException;
import com.mindtree.greencard.exception.subadminserviceexception.ElementNotFoundException;
import com.mindtree.greencard.exception.subadminserviceexception.EmptyListException;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;

public interface SubAdminService {

	public List<InProgressGreenCard> getComplaints(String mid) throws EmptyListException;

	public NewGreenCard getData(int gcid) throws ElementNotFoundException;

	public String updateComplaint(InProgressGreenCard sub) throws ComplaintNotFoundException;

	public String reassignComplaint(InProgressGreenCard sub) throws ComplaintNotFoundException;

	public List<Category> getCategory() throws EmptyListException;

	public List<SubAdminCategory> getSubadmins(String category) throws EmptyListException;

	public String sendHelpEmail(String mid, int gc_id, String desc);
}
