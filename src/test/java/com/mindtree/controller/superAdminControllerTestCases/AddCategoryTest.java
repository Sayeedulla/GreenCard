package com.mindtree.controller.superAdminControllerTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class AddCategoryTest {

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
	public void addCategory() throws Exception {
       Category category = new Category();
       category.setCategoryName("Safety");
       
       when(superAdminServiceImpl.addCategory(category)).thenReturn(category.getCategoryName());
       
       Gson gson = new Gson();
       String body = gson.toJson(category);
       
       
       mockMvc.perform(post("/GreenCard/addCategory")
       .contentType(MediaType.APPLICATION_JSON).content(body))
       .andExpect(status().isOk());
       assertEquals(superAdminController.addCategory(category), "Safety");
	}
	
	@Test
	public void addCategory1() throws Exception {
       Category category = new Category();
       category.setCategoryName("Safety");
       
       when(superAdminServiceImpl.addCategory(category)).thenThrow(new SuperAdminServiceException("Category name already Exists"));
       
       Gson gson = new Gson();
       String body = gson.toJson(category);
       
       
       mockMvc.perform(post("/GreenCard/addCategory")
       .contentType(MediaType.APPLICATION_JSON).content(body))
       .andExpect(status().isOk());
       assertEquals(superAdminController.addCategory(category), "Category name already Exists");
	}
}
