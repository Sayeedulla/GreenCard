package com.mindtree.controller.superAdminControllerTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.mindtree.greencard.controller.SuperAdminController;
import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class UpdateUserTest {

	@Mock
	SuperAdminServiceImpl superAdminServiceImpl;
	
	@InjectMocks
	SuperAdminController superAdminController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
	}

	@Test
	public void updateUserTest() throws Exception
	{
		User user=new User();
		user.setMid("M1047048");
		user.setName("Dillan");
		user.setEmailId("Dillan.Fernando2@mindtree.com");
		user.setType("User");
		user.setPassword("Pass@123");
		
		when(superAdminServiceImpl.updateUser(user)).thenReturn(user.getMid());
		
		Gson gson=new Gson();
		String body=gson.toJson(user);
		
       mockMvc.perform(put("/GreenCard/updateUser")
       .contentType(MediaType.APPLICATION_JSON).content(body))
       .andExpect(status().isOk());
       assertEquals("M1047048",superAdminController.updateUser(user));
	} 
	@Test
	public void updateUserTest1() throws Exception
	{
		User user=new User();
		user.setMid("I1047048");
		user.setName("Dillan");
		user.setEmailId("Dillan.Fernando2@mindtree.com");
		user.setType("User");
		user.setPassword("Pass@123");
		
		when(superAdminServiceImpl.updateUser(user)).thenThrow(new SuperAdminServiceException("Invalid Mid"));
		
		Gson gson=new Gson();
		String body=gson.toJson(user);
		
       mockMvc.perform(put("/GreenCard/updateUser")
       .contentType(MediaType.APPLICATION_JSON).content(body))
       .andExpect(status().isOk());
       assertEquals("Invalid Mid",superAdminController.updateUser(user));
	} 
		}

