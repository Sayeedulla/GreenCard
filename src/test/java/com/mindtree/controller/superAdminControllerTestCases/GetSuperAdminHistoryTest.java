package com.mindtree.controller.superAdminControllerTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
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
import com.mindtree.greencard.model.SuperAdminHistory;
import com.mindtree.greencard.service.serviceimpl.SuperAdminServiceImpl;

public class GetSuperAdminHistoryTest {
	@InjectMocks
	SuperAdminController superAdminController;
	
	@Mock 
	SuperAdminServiceImpl superAdminServiceImpl;
	
	MockMvc mockMvc;
	List<SuperAdminHistory> historylist;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(superAdminController).build();
		
		historylist = getHistoryList();
	}
	private List<SuperAdminHistory> getHistoryList() {
		SuperAdminHistory superAdminHistory = new SuperAdminHistory();
		List<SuperAdminHistory> list = new ArrayList<SuperAdminHistory>();
		superAdminHistory.setHid(1);
		superAdminHistory.setMid("M1047048");
		superAdminHistory.setType("SubAdmin");
		superAdminHistory.setWhatischanged("Live Wire");
		superAdminHistory.setTimelog(LocalDateTime.now());
		list.add(superAdminHistory);
		superAdminHistory.setHid(1);
		superAdminHistory.setMid("M1047049");
		superAdminHistory.setType("SubAdmin");
		superAdminHistory.setWhatischanged("Live Wire");
		superAdminHistory.setTimelog(LocalDateTime.now());
		list.add(superAdminHistory);superAdminHistory.setHid(1);
		superAdminHistory.setMid("M1047047");
		superAdminHistory.setType("Admin");
		superAdminHistory.setWhatischanged("Live Wire");
		superAdminHistory.setTimelog(LocalDateTime.now());
		list.add(superAdminHistory);
		return list;
	}
	
	@Test
	public void getSuperAdminHistoryTest() throws Exception {
	 
		when(superAdminServiceImpl.getSuperAdminHistory()).thenReturn(historylist);
		
		mockMvc.perform(get("/GreenCard/getSuperAdminHistory"))
		    .andExpect(status().isOk());
		assertEquals(3,superAdminController.getSuperAdminHistory().size());
	}
}
