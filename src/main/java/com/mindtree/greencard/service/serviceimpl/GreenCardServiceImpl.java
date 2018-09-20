package com.mindtree.greencard.service.serviceimpl;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.exception.userserviceexception.GreenCardLifeCycleException;
import com.mindtree.greencard.exception.userserviceexception.SaveGreenCardByGuestException;
import com.mindtree.greencard.exception.userserviceexception.SaveNewGreenCardException;
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
	public String saveNewGreenCard(CommonsMultipartFile fileupload, String what, String location, String mid)
			throws GreenCardException {
		try {
			NewGreenCard newgreencard = new NewGreenCard();
			if(fileupload!=null)
			{
			newgreencard.setImage(fileupload.getBytes());
			}
			else
				newgreencard.setImage(null);
			
			newgreencard.setLandmark(location);
			newgreencard.setWhatHappened(what);
			newgreencard.setSubmittedDate(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			newgreencardrepository.save(newgreencard);
			Optional<User> user1 = userrepository.findUser(mid);
			
			User user = new User();
			if (user1.isPresent()) {
				user = user1.get();
			}
			user.getNewGreenCards().add(newgreencard);
			userrepository.save(user);
			
			GreenCardLifeCycle greencardlifecycle = new GreenCardLifeCycle();
			greencardlifecycle.setStatus("Open");
			greencardlifecycle.setNewgreencard(newgreencard);
			greencardlifecycle.setSubmittedTime(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			greencardlifecyclerepository.save(greencardlifecycle);
			
			if (newgreencard.getLandmark() == null) {
				
				throw new SaveNewGreenCardException("sorry cant save green card");
			}
			
			return "Your GreenCard Id is " + newgreencard.getGreenCardId() +" Note it down for future Reference";
		} catch (SaveNewGreenCardException e) {
			throw new GreenCardException(e);
		}

	}

	@Override
	public String saveNewGreenCardByGuest(CommonsMultipartFile fileupload, String what, String location, String name,
			BigInteger phone) throws GreenCardException {
		try {
			NewGreenCard newgreencard = new NewGreenCard();
			
			newgreencard.setImage(fileupload.getBytes());
			newgreencard.setLandmark(location);
			newgreencard.setWhatHappened(what);
			newgreencard.setSubmittedDate(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			newgreencardrepository.save(newgreencard);
			GreenCardLifeCycle greencardlifecycle = new GreenCardLifeCycle();
			greencardlifecycle.setStatus("Open");
			greencardlifecycle.setNewgreencard(newgreencard);
			greencardlifecycle.setSubmittedTime(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
			greencardlifecyclerepository.save(greencardlifecycle);
			if (newgreencard.getGreenCardId() == null) {
				throw new SaveGreenCardByGuestException("sorry can't save the green card by guest");
			}
			return "Your GreenCard Id is " + newgreencard.getGreenCardId() + " Note it down for future Reference";
		} catch (SaveGreenCardByGuestException e) {
			throw new GreenCardException(e);
		}
	}

	@Override
	public GreenCardLifeCycle getGreenCardById(int green_card_id) throws GreenCardException {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		try {
			NewGreenCard newGreenCard = this.newgreencardrepository.getOne(green_card_id);
			greenCardLifeCycle = this.greencardlifecyclerepository.getGreenCardById(newGreenCard);
			if (greenCardLifeCycle == null) {
				throw new GreenCardLifeCycleException("sorry cant return greencardlifecycle");
			}
		} catch (GreenCardLifeCycleException e) {
			throw new GreenCardException(e);
		}
		return greenCardLifeCycle;
	}

}
