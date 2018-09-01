package com.mindtree.greencard.service.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.dto.DTOClass;
import com.mindtree.greencard.jprepository.UserRepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.UserRepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.SaveNewGreenCardService;


@Service
public class SaveNewGreenCardServiceImpl implements SaveNewGreenCardService {

	
	@Autowired
	UserRepository userRepository;
	@Autowired
	NewGreenCardRepository newGreenCardRepository;
	@Autowired
	GreenCardLifeCycleRepository greenCardLifeCycleRepository;
	
	@Override
	public String saveNewGreenCard(DTOClass dtoClass) {

		User user=this.userRepository.findUser(dtoClass.getMid());
		NewGreenCard newGreenCard=new NewGreenCard();
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		
		newGreenCard.setWhatHappened(dtoClass.getWhatHappened());
		newGreenCard.setLandmark(dtoClass.getLandmark());
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		
		greenCardLifeCycle.setStatus(dtoClass.getStatus());
		greenCardLifeCycle.setTimePeriod(dtoClass.getTimePeriod());
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		
		newGreenCard.setUser(user);
		(user.getNewGreenCards()).add(newGreenCard);
		this.newGreenCardRepository.save(newGreenCard);
		this.userRepository.save(user);
		greenCardLifeCycle.setNewGreenCard(newGreenCard);

		this.greenCardLifeCycleRepository.save(greenCardLifeCycle);
		return "Success";
	}

}