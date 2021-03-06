package com.mindtree.greencard.service;

import java.util.List;
import java.util.Optional;

import com.mindtree.greencard.entity.GreenCardHistory;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;


public interface AdminService{
	
	public List<NewGreenCard> newComplaints();
	public Optional<NewGreenCard> getCard(int gid);
	public List<InProgressGreenCard> viewprogress();
	public Optional<InProgressGreenCard> getprogressCard(int gid);
	public String assigncard(InProgressGreenCard card);
	public List<GreenCardHistory> getAllFromHistory();
	public Optional<GreenCardHistory> getByGid(int gId);
	public List<SubAdminCategory> getSubAdmins();
	public List<GreenCardHistory> getForSubadmin(String mid);
	public String rejectGreenCard(int gid);
	public String resolveCard(int gid, String rootcause, String correctiveaction);
	public int newcount();
	public int assignedcount();
	public int closedcount();
	public int rejectcount();
	public int fullcount();
	public List<NewGreenCard> getBeware();
}
