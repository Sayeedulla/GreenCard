package com.mindtree.greencard.service.serviceimpl;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.greencardrepository.Feedbackrepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.FeedBack;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userrepository;
	@Autowired
	Feedbackrepository feedbackrepository;

	@Transactional
	@Override
	public User getUserInfoByMidAndPassword(User user) {
		String sha256hex = DigestUtils.sha256Hex(user.getPassword());
		return userrepository.findUserbymidPassword(user.getMid(), sha256hex);
	}

	@Override
	public String saveFeedBack(FeedBack feedback) {
		// TODO Auto-generated method stub
		feedbackrepository.save(feedback);
		return "Thank you for providing Feedback";
	}

	@Override
	public User getUserInfoByMid(String mid) {
		return this.userrepository.getUserByMid(mid);
	}

}
