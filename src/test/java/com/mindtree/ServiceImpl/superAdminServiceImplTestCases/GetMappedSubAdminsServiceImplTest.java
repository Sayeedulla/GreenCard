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

import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetMappedSubAdminsServiceImplTest {
	@Mock
	SubAdminCategoryRepository subAdminCategoryRepository;
	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Spy
	List<SubAdminCategory> mappedSubAdminsList=new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		mappedSubAdminsList=getMappedSubAdmins();
	}
	
	private List<SubAdminCategory> getMappedSubAdmins() {
		List<SubAdminCategory> list=new ArrayList<>();
		SubAdminCategory subAdminCategory =new SubAdminCategory();
		subAdminCategory.setMid("M1047048");
		subAdminCategory.setCategoryName("Safety");
		list.add(subAdminCategory);
		subAdminCategory.setMid("M1047049");
		subAdminCategory.setCategoryName("Health");
		list.add(subAdminCategory);
		return list;
	}

	@Test
	public void getCategoriesServiceImplTest() {
		when(subAdminCategoryRepository.findAll()).thenReturn(mappedSubAdminsList);
		assertEquals(2,superAdminServiceImpl.getMappedSubAdmins().size());
	}
	

}
