package com.mindtree.greencard.service;

import java.util.List;
import java.util.Optional;

import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;


public interface AdminService {
	
	public List<NewGreenCard> newComplaints();
	public Optional<NewGreenCard> getCard(int gid);
	public List<InProgressGreenCard> viewprogress();
	public String assigncard(InProgressGreenCard card);
	public List<GreenCardHistory> getAllFromHistory();
	public void generateXl();
	public List<SubAdminCategory> getSubAdmins();

}