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
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetMappedSubAdminTest {

	@InjectMocks
	SuperAdminController superAdminController;
	
	@Mock 
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	List<SubAdminCategory> mappedSubAdminslist;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
		
        mappedSubAdminslist = getSubAdminCategories();	
	}
	private List<SubAdminCategory> getSubAdminCategories() {
	    List<SubAdminCategory> list = new ArrayList<SubAdminCategory>();
	    SubAdminCategory subAdminCategory=new SubAdminCategory();
	    subAdminCategory.setMid("M1047048");
	    subAdminCategory.setCategoryName("Safety");
	    list.add(subAdminCategory);
	    subAdminCategory.setMid("M1047049");
	    subAdminCategory.setCategoryName("Health");
	    list.add(subAdminCategory);
	    subAdminCategory.setMid("M1047047");
	    subAdminCategory.setCategoryName("Safety");
	    list.add(subAdminCategory);
		return list;
	}
	
	@Test
	public void getMappedSubAdminTest() throws Exception {
		
		when(superAdminServiceImpl.getMappedSubAdmins()).thenReturn(mappedSubAdminslist);
		
		mockMvc.perform(get("/GreenCard/getMappedSubAdmins"))
		.andExpect(status().isOk());
		
		assertEquals(3,superAdminController.getMappedSubAdmins().size());
	}
	
}
