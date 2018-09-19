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
	public String getUserInfoByMid(User user) {
		User checkUser= this.userrepository.getUserByMid(user.getMid());
		if(checkUser==null) {
			System.out.println("Hello");
			String sha256hex = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(sha256hex);
			this.userrepository.save(user);
			return "User";
		}
		else if(checkUser.getType().equals("User")) {
			return "User";
		}
		else if(checkUser.getType().equals("Admin")){
			return "Admin";
		}
		else if(checkUser.getType().equals("SubAdmin")) {
			return "SubAdmin";
		}
		else  {
			return "SuperAdmin";
		}
		
	}

}
