package com.mindtree.greencard.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.UserService;
@Service
public class UserServiceImpl implements UserService {
@Autowired
UserRepository userrepository;

@Transactional
	@Override
	public User getUserInfoByMidAndPassword(User user) {
	
		return userrepository.findUserbymidPassword(user.getMid(), user.getPassword());
	}

}
