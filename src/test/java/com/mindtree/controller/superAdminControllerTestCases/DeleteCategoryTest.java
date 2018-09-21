package com.mindtree.controller.superAdminControllerTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.controller.SuperAdminController;
import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class DeleteCategoryTest {

	@Mock
	SuperAdminServiceImpl superAdminServiceImpl;
	
	@InjectMocks
	SuperAdminController superAdminController;
		
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
	}
	
	@Test
	public void deleteCategory() throws Exception{
        		
		String categoryName="Safety";
		when(superAdminServiceImpl.deleteCategory(categoryName)).thenReturn(categoryName);
		
		mockMvc.perform(delete("/GreenCard/deleteCategory/{categoryName}",1)
				.contentType(MediaType.APPLICATION_STREAM_JSON_VALUE).content(categoryName))
		        .andExpect(status().isOk());
		assertEquals("Safety",superAdminController.deleteCategory(categoryName));
	}
	
	@Test
	public void deleteCategory1() throws Exception{
        		
		String categoryName="Safety";
		when(superAdminServiceImpl.deleteCategory(categoryName)).thenThrow(new SuperAdminServiceException("Default Category ,Operation Not Allowed"));
		
		mockMvc.perform(delete("/GreenCard/deleteCategory/{categoryName}",1)
				.contentType(MediaType.APPLICATION_STREAM_JSON_VALUE).content(categoryName))
		        .andExpect(status().isOk());
		assertEquals("Default Category ,Operation Not Allowed",superAdminController.deleteCategory(categoryName));
	}
}

