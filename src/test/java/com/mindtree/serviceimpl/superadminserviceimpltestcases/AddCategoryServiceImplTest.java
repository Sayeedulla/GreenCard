package com.mindtree.serviceimpl.superadminserviceimpltestcases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SuperAdminHistoryRepo;
import com.mindtree.greencard.entity.Category;
import com.mindtree.greencard.entity.SuperAdminHistory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class AddCategoryServiceImplTest {

	@Mock
	CategoryRepository categoryRepo;
	@Mock
	SuperAdminHistoryRepo superAdminHistoryRepo;
	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	@Spy
	Category category;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		
		category=getCategory();
	}

	private Category getCategory() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("Safety");
		return category;
	}
	
	@Test
	public void addCategoryServiceImpl() throws SuperAdminServiceException {
		
		SuperAdminHistory superAdminHistory=new SuperAdminHistory();
		when(superAdminHistoryRepo.save(superAdminHistory)).thenReturn(null);
		when(categoryRepo.getCategory(category.getCategoryName())).thenReturn(null);
		when(categoryRepo.save(category)).thenReturn(category);
		assertEquals("Safety",superAdminServiceImpl.addCategory(category));
	}
}
