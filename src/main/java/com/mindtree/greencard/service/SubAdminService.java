package com.mindtree.greencard.service;

import java.util.List;

import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;

public interface SubAdminService {

	public List<InProgressGreenCard> getComplaints(String mid);

	public NewGreenCard getData(int gcid);

	public InProgressGreenCard getSubadmin(int gcid);

	public String updateComplaint(InProgressGreenCard sub);

}

