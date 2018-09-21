package com.mindtree.greencard.service.serviceimpl;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.exception.userserviceexception.FeedbackException;
import com.mindtree.greencard.exception.userserviceexception.GetInfoByMidException;
import com.mindtree.greencard.exception.userserviceexception.UserByMidPassNotExistsException;
import com.mindtree.greencard.jprepository.greencardrepository.Feedbackrepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.entity.FeedBack;
import com.mindtree.greencard.entity.User;
import com.mindtree.greencard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userrepository;
	@Autowired
	Feedbackrepository feedbackrepository;

	@Transactional
	@Override
	public User getUserInfoByMidAndPassword(User user) throws GreenCardException {

		try {
			User userByMidPassword = userrepository.findUserbymidPassword(user.getMid());
			if (userByMidPassword == null) {
				throw new UserByMidPassNotExistsException("User by mid and password does not exist");
			}
		} catch (UserByMidPassNotExistsException e) {
			throw new GreenCardException(e);
		}
		return userrepository.findUserbymidPassword(user.getMid());
	}

	@Override
	public String saveFeedBack(FeedBack feedback) throws GreenCardException {
		try {
			if (feedback.getComment() == null || feedback.getRating() == null) {

				throw new FeedbackException("can't save feedback");
			}
			feedbackrepository.save(feedback);
			return "Thank you for providing Feedback";
		} catch (FeedbackException e) {
			throw new GreenCardException(e);
		}

	}

	@Override
	public String getUserInfoByMid(User user) throws GreenCardException {

		try {
			User checkUser = this.userrepository.getUserByMid(user.getMid());
			if (checkUser == null) {
				String sha256hex = DigestUtils.sha256Hex(user.getPassword());
				user.setPassword(sha256hex);
				this.userrepository.save(user);

				return "User";
			} else if (checkUser.getType().equals("User")) {
				return "User";
			} else if (checkUser.getType().equals("Admin")) {
				return "Admin";
			} else if (checkUser.getType().equals("SubAdmin")) {
				return "SubAdmin";
			} else if (checkUser.getType().equals("SuperAdmin")) {
				return "SuperAdmin";
			} else {
				throw new GetInfoByMidException("sorry can't return the status");
			}
		} catch (GetInfoByMidException e) {
			throw new GreenCardException(e);
		}

	}
}
