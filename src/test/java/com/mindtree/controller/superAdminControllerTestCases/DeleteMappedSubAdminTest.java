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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.controller.SuperAdminController;
import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class DeleteMappedSubAdminTest {

	@Mock
	SuperAdminServiceImpl superAdminServiceImpl;
	
	@InjectMocks
	@Autowired
	SuperAdminController superAdminController;
	
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
	}
	
	@Test
	public void deleteMappedSubAdminTest() throws Exception{
   
		String mid="M1047048";
		when(superAdminServiceImpl.deleteMappedSubAdmin(mid)).thenReturn(mid);
		
		mockMvc.perform(delete("/GreenCard/deleteMappedSubAdmin/{mid}",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(mid))
		        .andExpect(status().isOk());
		assertEquals("M1047048",superAdminController.deleteMappedSubAdmin(mid));
	}
	
	@Test
	public void deleteMappedSubAdminTest1() throws Exception{
   
		String mid="M1047048";
		when(superAdminServiceImpl.deleteMappedSubAdmin(mid)).thenThrow(new SuperAdminServiceException("SubAdmin not Found"));
		
		mockMvc.perform(delete("/GreenCard/deleteMappedSubAdmin/{mid}",1)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(mid))
		        .andExpect(status().isOk());
	   assertEquals("SubAdmin not Found",superAdminController.deleteMappedSubAdmin(mid));
	}
	
}
 