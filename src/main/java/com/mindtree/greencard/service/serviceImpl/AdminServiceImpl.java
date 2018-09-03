package com.mindtree.greencard.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.AdminRepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.GreenCardRepository.NewGreenCardRepository;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	NewGreenCardRepository newgreencard;
	
	@Autowired
	InProgressGreenCardRepository inprogresscard;

	@Override
	public List<NewGreenCard> newComplaints() {
		// TODO Auto-generated method stub
		
		return this.newgreencard.findAll();
	}

	@Override
	public Optional<NewGreenCard> getCard(int gid) {
		// TODO Auto-generated method stub
		return this.newgreencard.findById(gid);
	}

	@Override
	public String assigncard(InProgressGreenCard card) {
		// TODO Auto-generated method stub
		this.inprogresscard.save(card);
		return "Assigned";
	}

	@Override
	public List<InProgressGreenCard> viewprogress() {
		// TODO Auto-generated method stub
		return this.inprogresscard.findAll();
	}



}
