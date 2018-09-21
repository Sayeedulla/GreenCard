package com.mindtree.serviceimpl.superadminserviceimpltestcases;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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

import com.mindtree.greencard.jprepository.superadminrepository.SuperAdminHistoryRepo;
import com.mindtree.greencard.entity.SuperAdminHistory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetSuperAdminHistoryServiceImplTest {
	@Mock
	SuperAdminHistoryRepo superAdminHistoryRepo;
	
	@InjectMocks
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	
	@Spy
	List<SuperAdminHistory> historyList=new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminServiceImpl).build();
		historyList=getSuperAdminHistory();
	}
	
	private List<SuperAdminHistory> getSuperAdminHistory() {
		List<SuperAdminHistory> list=new ArrayList<>();
		SuperAdminHistory history =new SuperAdminHistory();
		history.setMid("M1047048");
		history.setHid(1);
		history.setTimelog(LocalDateTime.now());
		history.setType("Admin");
	 	history.setWhatischanged("Live Wire is Fixed");
	    list.add(history);
	    history.setMid("M1047049");
		history.setHid(2);
		history.setTimelog(LocalDateTime.now());
		history.setType("SubAdmin");
	 	history.setWhatischanged("Live Wire is Fixed");
	    list.add(history);
		return list;
	}

	@Test
	public void getCategoriesServiceImplTest() {
		when(superAdminHistoryRepo.findAll()).thenReturn(historyList);
		assertEquals(2,superAdminServiceImpl.getSuperAdminHistory().size());
	}

}
