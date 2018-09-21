package com.mindtree.controller.superAdminControllerTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.controller.SuperAdminController;
import com.mindtree.greencard.entity.User;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetUsersTest {

	@InjectMocks
	SuperAdminController superAdminController;
	
	@Mock 
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	List<User> userlist;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
		
        userlist = getUsers();
	}
	
	@Test
	public void getUsersTest() throws Exception {
		
		when(superAdminServiceImpl.getUsers()).thenReturn(userlist);
		
		mockMvc.perform(get("/GreenCard/getUsers"))
		.andExpect(status().isOk());
		assertEquals(3,superAdminController.getUsers().size());
	}
	
	List<User> getUsers(){
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setMid("M1047048");
		user.setName("Dillan");
		user.setEmailId("Dillan.Fernando@mindtree.com");
		user.setPassword("Pass@123");
		user.setType("User");
		list.add(user);
		user.setMid("M1047049");
		user.setName("Dishan");
		user.setEmailId("Dishan.Fernando@mindtree.com");
		user.setPassword("Pass@123");
		user.setType("User");
		list.add(user);
		user.setMid("M1047050");
		user.setName("Anish");
		user.setEmailId("Anish.Fernando@mindtree.com");
		user.setPassword("Pass@123");
		user.setType("User");
		list.add(user);
		return list;
	}
	
}
