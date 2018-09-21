package com.mindtree.greencard.service;

import org.springframework.stereotype.Service;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.exception.userserviceexception.UserByMidPassNotExistsException;
import com.mindtree.greencard.model.FeedBack;
import com.mindtree.greencard.model.User;

@Service
public interface UserService {

	public User getUserInfoByMidAndPassword(User user) throws GreenCardException;

	public String saveFeedBack(FeedBack feedback) throws GreenCardException;

	public String getUserInfoByMid(User user)  throws GreenCardException;

}
