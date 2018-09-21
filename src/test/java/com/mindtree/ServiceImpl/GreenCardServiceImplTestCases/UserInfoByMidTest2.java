package com.mindtree.ServiceImpl.GreenCardServiceImplTestCases;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class UserInfoByMidTest2 {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	UserRepository userrepository;
	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);
		User user=new User();
		String sha256hex = DigestUtils.sha256Hex("Pass@123");
		user.setPassword(sha256hex);
		user.setMid("M1046908");
		user.setType("Admin");
		when(userrepository.save(user)).thenReturn(user);
		when(userrepository.getUserByMid(user.getMid())).thenReturn(user);
	}
	@Test
	public void getUserInfoByMid() throws GreenCardException {
		User user=new User();
		user.setMid("M1046908");
		user.setPassword("Pass@123");
		
		assertEquals(userServiceImpl.getUserInfoByMid(user),"Admin");
		
	}
}
