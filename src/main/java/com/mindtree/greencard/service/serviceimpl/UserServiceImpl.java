package com.mindtree.greencard.service.serviceimpl;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
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
	String sha256hex = DigestUtils.sha256Hex(user.getPassword());
		return userrepository.findUserbymidPassword(user.getMid(), sha256hex);
	}

}
