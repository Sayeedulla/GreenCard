package com.mindtree.greencard.service;

import java.util.List;

import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;

public interface SubAdminService {

	public List<InProgressGreenCard> getComplaints(String mid);

	public NewGreenCard getData(int gcid);

	

	public String updateComplaint(InProgressGreenCard sub);

	public String reassignComplaint(InProgressGreenCard sub);
	
	public List<Category> getCategory();

	public List<SubAdminCategory> getSubadmins(String category);
public String sendHelpEmail(String mid,int gc_id,String desc);
}

