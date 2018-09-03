package com.mindtree.greencard.service;

import org.springframework.stereotype.Service;

import com.mindtree.greencard.model.User;
@Service
public interface UserService {
	
public User getUserInfoByMidAndPassword(User user);
}
