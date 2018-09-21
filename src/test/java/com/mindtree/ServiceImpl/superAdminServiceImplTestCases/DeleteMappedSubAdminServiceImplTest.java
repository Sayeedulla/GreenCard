package com.mindtree.ServiceImpl.superAdminServiceImplTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.greencard.exception.superadminexceptions.SuperAdminServiceException;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class DeleteMappedSubAdminServiceImplTest {

	@Mock
	SubAdminCategoryRepository subAdminCategoryRepository;

	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		
	}
	
	@Test
	public void deleteMappedSubAdminServiceImplTest() throws SuperAdminServiceException {
		SubAdminCategory mappedSubAdmin=new SubAdminCategory();
		mappedSubAdmin.setMid("M1047048");
		mappedSubAdmin.setCategoryName("Safety");
		Optional<SubAdminCategory> subAdminCategory=Optional.of(mappedSubAdmin);
		when(subAdminCategoryRepository.findById("M1047048")).thenReturn(subAdminCategory);
        assertEquals("M1047048",superAdminServiceImpl.deleteMappedSubAdmin("M1047048"));
	}
	
	
}