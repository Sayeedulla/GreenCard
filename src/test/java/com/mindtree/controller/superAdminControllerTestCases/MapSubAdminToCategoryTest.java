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
import com.mindtree.greencard.exception.superAdminExceptions.SuperAdminServiceException;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class MapSubAdminToCategoryTest {

	@InjectMocks
	SuperAdminController superAdminController;
	
	@Mock
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build(); 
	}
	
	@Test
	public void mapSubAdminToCategoryTest() throws Exception {
		SubAdminCategory subAdminCategory = new SubAdminCategory();
		subAdminCategory.setCategoryName("Safety");
		subAdminCategory.setMid("M1047048");
		
		Gson gson = new Gson();
		String body = gson.toJson(subAdminCategory);
		
		when(superAdminServiceImpl.mapSubAdminToCategory(subAdminCategory)).thenReturn("SubAdmin Mapped");
		
		mockMvc.perform(put("/GreenCard/mapSubAdminToCategory")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(body))
		        .andExpect(status().isOk());
		
		assertEquals("SubAdmin Mapped",superAdminController.mapSubAdminToCategory(subAdminCategory));
	}
	
	@Test
	public void mapSubAdminToCategoryTest1() throws Exception {
		SubAdminCategory subAdminCategory = new SubAdminCategory();
		subAdminCategory.setCategoryName("Safety");
		subAdminCategory.setMid("M1047048");
		
		Gson gson = new Gson();
		String body = gson.toJson(subAdminCategory);
		
		when(superAdminServiceImpl.mapSubAdminToCategory(subAdminCategory)).thenThrow(new SuperAdminServiceException("User not Found"));
		
		mockMvc.perform(put("/GreenCard/mapSubAdminToCategory")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(body))
		        .andExpect(status().isOk());
		
		assertEquals("User not Found",superAdminController.mapSubAdminToCategory(subAdminCategory));
	}
}
