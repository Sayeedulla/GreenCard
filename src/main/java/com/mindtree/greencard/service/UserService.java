package com.mindtree.greencard.service;

import org.springframework.stereotype.Service;

import com.mindtree.greencard.model.FeedBack;
import com.mindtree.greencard.model.User;

@Service
public interface UserService {

	public User getUserInfoByMidAndPassword(User user);

	public String saveFeedBack(FeedBack feedback);

	public User getUserInfoByMid(String mid);

}
