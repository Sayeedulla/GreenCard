package com.mindtree.greencard.service;

import org.springframework.stereotype.Service;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.entity.FeedBack;
import com.mindtree.greencard.entity.User;

@Service
public interface UserService {

	public User getUserInfoByMidAndPassword(User user) throws GreenCardException;

	public String saveFeedBack(FeedBack feedback) throws GreenCardException;

	public String getUserInfoByMid(User user)  throws GreenCardException;

}
