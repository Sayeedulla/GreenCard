package com.mindtree.greencard.service;

import java.util.List;

import com.mindtree.greencard.exception.subadminserviceexception.ServiceException;
import com.mindtree.greencard.entity.Category;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;

public interface SubAdminService {

	public List<InProgressGreenCard> getComplaints(String mid) throws ServiceException;

	public NewGreenCard getData(int gcid) throws ServiceException ;

	public String updateComplaint(InProgressGreenCard sub) throws ServiceException ;

	public String reassignComplaint(InProgressGreenCard sub) throws ServiceException ;

	public List<Category> getCategory() throws ServiceException ;

	public List<SubAdminCategory> getSubadmins(String category) throws ServiceException ;

	public String sendHelpEmail(String mid, int gcId, String desc) throws ServiceException;
}
