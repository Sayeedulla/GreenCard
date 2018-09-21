package com.mindtree.greencard.service;

import java.util.List;
import java.util.Optional;

import com.mindtree.greencard.exception.AdminExceptions.AdminException;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;


public interface AdminService{
	
	public List<NewGreenCard> newComplaints();
	public Optional<NewGreenCard> getCard(int gid)throws AdminException;
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
}
