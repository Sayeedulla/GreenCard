package com.mindtree.greencard.service.serviceimpl;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.GreenCardService;
@Service
public class GreenCardServiceImpl implements GreenCardService {
	@Autowired
	NewGreenCardRepository newgreencardrepository;
	@Autowired
	UserRepository userrepository;
	@Autowired
	GreenCardLifeCycleRepository greencardlifecyclerepository;
@Transactional

	@Override
	public String SaveNewGreenCard(CommonsMultipartFile fileupload, String what, String location, String mid) {
	
	NewGreenCard newgreencard=new NewGreenCard();
	newgreencard.setImage(fileupload.getBytes());
	newgreencard.setLandmark(location);
	newgreencard.setWhatHappened(what);
	newgreencard.setSubmittedDate(LocalDateTime.now());
	newgreencardrepository.save(newgreencard);
	User user=userrepository.findUser(mid);
	user.getNewGreenCards().add(newgreencard);
	userrepository.save(user);
	GreenCardLifeCycle greencardlifecycle=new GreenCardLifeCycle();
	greencardlifecycle.setStatus("Open");
	greencardlifecycle.setNewgreencard(newgreencard);
	greencardlifecycle.setSubmittedTime(LocalDateTime.now());
	greencardlifecyclerepository.save(greencardlifecycle);
	
	
		return "Your GreenCard Id is "+newgreencard.getGreenCardId()+" Note it down for future Reference";
	}
@Override
public String SaveNewGreenCardByGuest(CommonsMultipartFile fileupload, String what, String location, String name,
		BigInteger phone) {
	
	User user=new User();
	user.setName(name);
	user.setPhoneNo(phone);
	user.setType("Guest");
	userrepository.save(user);
	NewGreenCard newgreencard=new NewGreenCard();
	newgreencard.setImage(fileupload.getBytes());
	newgreencard.setLandmark(location);
	newgreencard.setWhatHappened(what);
	newgreencard.setSubmittedDate(LocalDateTime.now());
	newgreencardrepository.save(newgreencard);
	user.getNewGreenCards().add(newgreencard);
	userrepository.save(user);
	GreenCardLifeCycle greencardlifecycle=new GreenCardLifeCycle();
	greencardlifecycle.setStatus("Open");
	greencardlifecycle.setNewgreencard(newgreencard);
	greencardlifecycle.setSubmittedTime(LocalDateTime.now());
	greencardlifecyclerepository.save(greencardlifecycle);
	
	
		return "Your GreenCard Id is "+newgreencard.getGreenCardId()+" Note it down for future Reference";
}

}
