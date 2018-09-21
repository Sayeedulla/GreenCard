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
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetCategoriesTest {

	@InjectMocks
	SuperAdminController superAdminController;
	
	@Mock 
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	List<Category> categorylist;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
		
        categorylist = getCategoriesList();
	}
	private List<Category> getCategoriesList() {
	    Category category = new Category();
	    List<Category> list = new ArrayList<Category>();
	    category.setCategoryName("Safety");
	    list.add(category);
	    category.setCategoryName("Health");
	    list.add(category);
	    category.setCategoryName("Not Assigned");
	    list.add(category);
		return list;
	}
	
	@Test
	public void getCategoriesTest() throws Exception {
		
		when(superAdminServiceImpl.getCategories()).thenReturn(categorylist);
		
		mockMvc.perform(get("/GreenCard/getCategories"))
		   .andExpect(status().isOk());
		assertEquals(3,superAdminController.getCategories().size());
	}
}
