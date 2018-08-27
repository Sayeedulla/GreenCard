package com.mindtree.greencard.dao.daoImpl;

import java.time.LocalDateTime;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.greencard.dao.SaveNewGreenCardDao;
import com.mindtree.greencard.dto.DTOClass;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.User;

@Repository
public class SaveNewGreenCardDaoImpl implements SaveNewGreenCardDao {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public String saveNewGreenCard(DTOClass dtoClass) {
		
		User user=new User();
		NewGreenCard newGreenCard=new NewGreenCard();
		InProgressGreenCard inProgressGreenCard=new InProgressGreenCard();
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		
		user.setMid(dtoClass.getMid());
		user.setEmailId(dtoClass.getEmailId());
		user.setPassword(dtoClass.getPassword());
		user.setType(dtoClass.getType());
		user.setPhoneNo(dtoClass.getPhoneNo());
		user.setName(dtoClass.getName());
		
		newGreenCard.setWhatHappened(dtoClass.getWhatHappened());
		newGreenCard.setLandmark(dtoClass.getLandmark());
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		
		inProgressGreenCard.setPriority(dtoClass.getPriority());
		inProgressGreenCard.setCategory(dtoClass.getCategory());
		inProgressGreenCard.setAssignedPersonId(dtoClass.getAssignedPersonId());
		
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus(dtoClass.getStatus());
		greenCardLifeCycle.setTimePeriod(dtoClass.getTimePeriod());
		
		newGreenCard.setUser(user);
		inProgressGreenCard.setUser(user);
		greenCardLifeCycle.setUser(user);
		
		sessionFactory.getCurrentSession().save(newGreenCard);
		sessionFactory.getCurrentSession().save(inProgressGreenCard);
		sessionFactory.getCurrentSession().save(greenCardLifeCycle);

//		sessionFactory.getCurrentSession().save();
		return "Success";
	}

}
