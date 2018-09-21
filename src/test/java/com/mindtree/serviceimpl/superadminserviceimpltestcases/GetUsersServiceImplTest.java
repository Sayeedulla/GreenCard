package com.mindtree.serviceimpl.superadminserviceimpltestcases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.entity.User;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetUsersServiceImplTest {

	@Mock
	UserRepository userRepo;
	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Spy
	List<User> userList;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		
		userList=getUsersList();
	}
	
	private List<User> getUsersList() {
		
		List<User> list=new ArrayList<User>();
		User user=new User();
		
		user.setMid("M1047048");
		user.setEmailId("Dillan.Fernando@mindtree.com");
		user.setName("Dillan");
		user.setType("User");
		user.setPassword("Pass@123");
		list.add(user);
		
		user.setMid("M1047049");
		user.setEmailId("Dishan.Fernando@mindtree.com");
		user.setName("Dishan");
		user.setType("User");
		user.setPassword("Pass@123");
		list.add(user);
		
		user.setMid("M1047050");
		user.setEmailId("Anish.Fernando@mindtree.com");
		user.setName("Anish");
		user.setType("SubAdmin");
		user.setPassword("Pass@123");
		list.add(user);
		return list;
	}

	@Test
	public void getUsersServiceImplTest() {
		when(userRepo.findAll()).thenReturn(userList);
		assertEquals(3,superAdminServiceImpl.getUsers().size());
	}
}
