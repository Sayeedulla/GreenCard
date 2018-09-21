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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SuperAdminHistoryRepo;
import com.mindtree.greencard.entity.Category;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.entity.SuperAdminHistory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class DeleteCategoryServiceImplTest {

	@Mock
	CategoryRepository categoryRepository;
	@Mock
	SuperAdminHistoryRepo superAdminHistoryRepo;
	@Mock
	SubAdminCategoryRepository subAdminCategoryRepo;
	
	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
	}
	
	@Test
	public void deleteCategoryServiceImplTest() throws SuperAdminServiceException {
		Category category =new Category();
		category.setCategoryId(1);
		category.setCategoryName("Safety");
		SuperAdminHistory superAdminHistory=new SuperAdminHistory();
		SubAdminCategory subAdminCategory = new SubAdminCategory();
		subAdminCategory.setMid("M1047048");
		subAdminCategory.setCategoryName("Safety");
		List<SubAdminCategory> list=new ArrayList<>();
		list.add(subAdminCategory);
		
		when(categoryRepository.getCategory(category.getCategoryName())).thenReturn(category);
		when(superAdminHistoryRepo.save(superAdminHistory)).thenReturn(null);
		when(subAdminCategoryRepo.getSubAdminCategories(category.getCategoryName())).thenReturn(list);
		assertEquals("Safety",superAdminServiceImpl.deleteCategory(category.getCategoryName()));
	}
	
}
