package com.mindtree.ServiceImpl.superAdminServiceImplTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetCategoriesServiceImplTest {

	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Spy
	List<Category> categoryList=new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		categoryList=getCategories();
	}
	
	private List<Category> getCategories() {
		List<Category> list=new ArrayList<>();
		Category category =new Category();
		category.setCategoryId(1);
		category.setCategoryName("Safety");
		list.add(category);
		category.setCategoryId(2);
		category.setCategoryName("Health");
		list.add(category);
		return list;
	}

	@Test
	public void getCategoriesServiceImplTest() {
		when(categoryRepository.findAll()).thenReturn(categoryList);
		assertEquals(2,superAdminServiceImpl.getCategories().size());
	}
	
}
