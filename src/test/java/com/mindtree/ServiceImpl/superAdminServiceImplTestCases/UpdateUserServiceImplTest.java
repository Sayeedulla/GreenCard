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

import com.mindtree.greencard.exception.superAdminExceptions.SuperAdminServiceException;
import com.mindtree.greencard.jprepository.superadminrepository.SuperAdminHistoryRepo;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.SuperAdminHistory;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class UpdateUserServiceImplTest {

	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	@Mock
	UserRepository userRepository;
	@Mock
	SuperAdminHistoryRepo superAdminHistoryRepo;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
	}
	
	@Test
	public void updateUserServiceImplTest() throws SuperAdminServiceException {
		User user = new User();
		user.setMid("M1047048");
		user.setName("Dillan");
		user.setEmailId("Dillan.Fernando@mindtree.com");
		user.setType("User");
		user.setPassword("Pass@123");
		
		SuperAdminHistory superAdminHistory=new SuperAdminHistory();
		Optional<User> USER = Optional.of(user);
		
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findUser(user.getMid())).thenReturn(USER);
		when(userRepository.getType(user.getMid())).thenReturn("Admin");
		when(superAdminHistoryRepo.save(superAdminHistory)).thenReturn(null);
		assertEquals("M1047048",superAdminServiceImpl.updateUser(user));
	}
	
//	@Test 
//	public void updateUserServiceImplTest1() {
//		User user = new User();
//		user.setMid("M1047048");
//		user.setName("Dillan");
//		user.setEmailId("Dillan.Fernando@mindtree.com");
//		user.setType("User");
//		user.setPassword("Pass@123");
//		
//		SuperAdminHistory superAdminHistory=new SuperAdminHistory();
//		
//		when(userRepository.save(user)).thenReturn(user);
//		when(userRepository.findUser(user.getMid())).thenReturn(null);
//		when(userRepository.getType(user.getMid())).thenReturn("Admin");
//		when(superAdminHistoryRepo.save(superAdminHistory)).thenReturn(null);
//		assertEquals("User Not Found",superAdminServiceImpl.updateUser(user));
//	}
}
