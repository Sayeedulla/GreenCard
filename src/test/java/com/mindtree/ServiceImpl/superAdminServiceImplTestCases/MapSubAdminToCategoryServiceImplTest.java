package com.mindtree.ServiceImpl.superAdminServiceImplTestCases;

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
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class MapSubAdminToCategoryServiceImplTest {

	@Mock
	SubAdminCategoryRepository subAdminCategoryRepository;
    @Mock
    UserRepository userRepository;
	@Mock
    CategoryRepository categoryRepository;
    
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	@Spy
	SubAdminCategory subAdminCategory;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		
		subAdminCategory=getSubAdminCategory();
	}

	private SubAdminCategory getSubAdminCategory() {
		SubAdminCategory subAdminCategory= new SubAdminCategory();
		subAdminCategory.setMid("M1047048");
		subAdminCategory.setCategoryName("Safety");
		return subAdminCategory;
	}
	
	@Test
	public void mapSubAdminToCategoryServiceImplTest() throws SuperAdminServiceException {
		User user=new User();
		Category category =new Category();
		when(userRepository.getUserByMid(subAdminCategory.getMid())).thenReturn(user);
		when(categoryRepository.getCategory(subAdminCategory.getCategoryName())).thenReturn(category);
		when(subAdminCategoryRepository.save(subAdminCategory)).thenReturn(subAdminCategory);
		assertEquals("SubAdmin Mapped",superAdminServiceImpl.mapSubAdminToCategory(subAdminCategory));
	}
	
	
	
	
}
