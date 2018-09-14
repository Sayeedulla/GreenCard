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
	public Optional<InProgressGreenCard> getprogressCard(int gid);
	public String assigncard(InProgressGreenCard card);
	public List<GreenCardHistory> getAllFromHistory();
	public void generateXl();
	public Optional<GreenCardHistory> getByGid(int gId);
	public List<SubAdminCategory> getSubAdmins();
	public String rejectGreenCard(int gid);
	public String resolveCard(int gid, String rootcause, String correctiveaction);

}
