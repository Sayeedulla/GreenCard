package com.mindtree.controller.admincontrollertestcases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.greencard.controller.AdminController;
import com.mindtree.greencard.entity.GreenCardHistory;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.AdminServiceImpl;
import com.mindtree.greencard.service.serviceimpl.GreenCardServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class AdminControllerCases {
	@Autowired
	@InjectMocks
	AdminController adminController;
	@Mock
	AdminServiceImpl adminServiceImpl;
	GreenCardServiceImpl greenCardServiceImpl;
	MockMvc mockMvc;

	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

	}

	@Test
	public void getComplaints() throws Exception {
		List<NewGreenCard> l = new ArrayList<NewGreenCard>();
		NewGreenCard newGreenCard = new NewGreenCard();
		newGreenCard.setGreenCardId(1);
		newGreenCard.setImage(null);
		newGreenCard.setLandmark("Bahada");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		newGreenCard.setWhatHappened("Powercut");
		l.add(newGreenCard);
		when(adminServiceImpl.newComplaints()).thenReturn(l);
		mockMvc.perform(get("/GreenCard/admin/newgreencards")).andExpect(status().isOk());
		assertEquals(l, adminController.newComplaints());

	}

	@Test
	public void getCount() throws Exception {
		when(adminServiceImpl.newcount()).thenReturn(1);
		mockMvc.perform(get("/GreenCard/admin/newcount")).andExpect(status().isOk());
		assertEquals(1, adminController.newcount());
	}

	@Test
	public void getCardtest() throws Exception {
		NewGreenCard newGreenCard = new NewGreenCard();
		newGreenCard.setGreenCardId(1);
		newGreenCard.setImage(null);
		newGreenCard.setLandmark("Bahada");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		newGreenCard.setWhatHappened("Powercut");
		Optional<NewGreenCard> newcard;
		newcard = Optional.of(newGreenCard);
		when(adminServiceImpl.getCard(1)).thenReturn(newcard);
		mockMvc.perform(get("/GreenCard/admin/getCard/{gid}", 1)).andExpect(status().isOk());
		assertEquals(newcard, adminController.getCard(1));
	}

	@Test
	public void viewprogresscard() throws Exception {
		InProgressGreenCard inProgressGreenCard = new InProgressGreenCard();
		inProgressGreenCard.setAssignedPersonId("M1046908");
		inProgressGreenCard.setCategory("Health");
		inProgressGreenCard.setCorrectiveAction("WestBengal Tides");
		inProgressGreenCard.setGcId(1);
		inProgressGreenCard.setlId(1);
		inProgressGreenCard.setPriority("high");
		inProgressGreenCard.setRootCause("RootCause");
		List<InProgressGreenCard> l = new ArrayList<InProgressGreenCard>();
		l.add(inProgressGreenCard);
		when(adminServiceImpl.viewprogress()).thenReturn(l);
		mockMvc.perform(get("/GreenCard/admin/getprogress")).andExpect(status().isOk());
		assertEquals(l, adminController.viewprogress());
	}

	@Test
	public void getProgressCardTest() throws Exception {
		InProgressGreenCard inProgressGreenCard = new InProgressGreenCard();
		inProgressGreenCard.setAssignedPersonId("M1046909");
		inProgressGreenCard.setCategory("Health");
		inProgressGreenCard.setCorrectiveAction("WestBengal Tides");
		inProgressGreenCard.setGcId(1);
		inProgressGreenCard.setlId(1);
		inProgressGreenCard.setPriority("high");
		inProgressGreenCard.setRootCause("RootCause");
		Optional<InProgressGreenCard> inprogress;
		inprogress = Optional.of(inProgressGreenCard);
		when(adminServiceImpl.getprogressCard(1)).thenReturn(inprogress);
		mockMvc.perform(get("/GreenCard/admin/getprogresscard/{gid}", 1)).andExpect(status().isOk());
		assertEquals(inprogress, adminController.getprogresscard(1));

	}

	@Test
	public void assigncardTest() throws Exception {
		InProgressGreenCard inProgressGreenCard = new InProgressGreenCard();
		inProgressGreenCard.setAssignedPersonId("M1046910");
		inProgressGreenCard.setCategory("Health ");
		inProgressGreenCard.setCorrectiveAction("WestBengal Tides ");
		inProgressGreenCard.setGcId(1);
		inProgressGreenCard.setlId(1);
		inProgressGreenCard.setPriority("high ");
		inProgressGreenCard.setRootCause("RootCause ");
		when(adminServiceImpl.assigncard(inProgressGreenCard)).thenReturn("Assigned");
		mockMvc.perform(post("/GreenCard/admin/assigncard").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(inProgressGreenCard))).andExpect(status().isOk());
		assertEquals("Assigned", adminController.assigncard(inProgressGreenCard));

	}

	@Test
	public void getAllFromistoryTest() throws Exception {
		GreenCardHistory greenCardHistory = new GreenCardHistory();
		greenCardHistory.setAssignedPersonId1("M1046908");
		greenCardHistory.setCorrectiveAction1("Go to ");
		greenCardHistory.setCategory("Health");
		greenCardHistory.setClosedDateTime(LocalDateTime.now());
		greenCardHistory.setgId(1);
		greenCardHistory.setImage(null);
		greenCardHistory.setLandmark("Bahada");
		greenCardHistory.setPriority("High");
		greenCardHistory.setRootCause1("RootCause");
		greenCardHistory.setStatus1("status1");
		greenCardHistory.setSubmittedDateTime(LocalDateTime.now());
		greenCardHistory.setWhatHappened("Idk");
		List<GreenCardHistory> l = new ArrayList<GreenCardHistory>();
		l.add(greenCardHistory);
		when(adminServiceImpl.getAllFromHistory()).thenReturn(l);
		mockMvc.perform(get("/GreenCard/admin/gethistory")).andExpect(status().isOk());
		assertEquals(l, adminController.getAllFromHistory());

	}

	@Test
	public void getByGidTest() throws Exception {
		GreenCardHistory greenCardHistory = new GreenCardHistory();
		greenCardHistory.setAssignedPersonId1("M1046908");
		greenCardHistory.setCorrectiveAction1("Go to ");
		greenCardHistory.setCategory("Health");
		greenCardHistory.setClosedDateTime(LocalDateTime.now());
		greenCardHistory.setgId(1);
		greenCardHistory.setImage(null);
		greenCardHistory.setLandmark("Bahada");
		greenCardHistory.setPriority("High");
		greenCardHistory.setRootCause1("RootCause");
		greenCardHistory.setStatus1("status1");
		greenCardHistory.setSubmittedDateTime(LocalDateTime.now());
		greenCardHistory.setWhatHappened("Idk");
		Optional<GreenCardHistory> greencardhistory;
		greencardhistory = Optional.of(greenCardHistory);
		when(adminServiceImpl.getByGid(1)).thenReturn(greencardhistory);
		mockMvc.perform(get("/GreenCard/admin/getCardhistory/{gid}", 1)).andExpect(status().isOk());
		assertEquals(greencardhistory, adminController.getByGid(1));

	}

	@Test
	public void getForSubAdminnTest() throws Exception {
		GreenCardHistory greenCardHistory = new GreenCardHistory();
		greenCardHistory.setAssignedPersonId1("M1046908");
		greenCardHistory.setCorrectiveAction1("Go to ");
		greenCardHistory.setCategory("Health");
		greenCardHistory.setClosedDateTime(LocalDateTime.now());
		greenCardHistory.setgId(1);
		greenCardHistory.setImage(null);
		greenCardHistory.setLandmark("Bahada");
		greenCardHistory.setPriority("High");
		greenCardHistory.setRootCause1("RootCause");
		greenCardHistory.setStatus1("status1");
		greenCardHistory.setSubmittedDateTime(LocalDateTime.now());
		greenCardHistory.setWhatHappened("Idk");
		List<GreenCardHistory> l = new ArrayList<GreenCardHistory>();
		l.add(greenCardHistory);
		when(adminServiceImpl.getForSubadmin("M1046908")).thenReturn(l);
		mockMvc.perform(get("/GreenCard/admin/historysubadmin/{mid}", "M1046908")).andExpect(status().isOk());
		assertEquals(l, adminController.getForSubAdmin("M1046908"));
	}

	@Test
	public void getSubAdminsTest() throws Exception {
		SubAdminCategory subAdminCategory = new SubAdminCategory();
		subAdminCategory.setMid("M1046908");
		subAdminCategory.setCategoryName("Health");
		List<SubAdminCategory> l = new ArrayList<SubAdminCategory>();
		l.add(subAdminCategory);
		when(adminServiceImpl.getSubAdmins()).thenReturn(l);
		mockMvc.perform(get("/GreenCard/admin/getSubAdminCategory")).andExpect(status().isOk());
		assertEquals(l, adminController.getSubAdmins());

	}

	@Test
	public void rejectGreenCardtest() throws Exception {
		when(adminServiceImpl.rejectGreenCard(1)).thenReturn("Rejected");
		mockMvc.perform(get("/GreenCard/admin/reject/{gid}", 1)).andExpect(status().isOk());
		assertEquals("Rejected", adminController.rejectGreenCard(1));
	}

	@Test
	public void resolveCardTest() throws Exception {
		when(this.adminServiceImpl.resolveCard(1, "rootcause", "correctiveaction")).thenReturn("Resolved");
		mockMvc.perform(get("/GreenCard/admin//resolve/{gid}/{rootcause}/{correctiveaction}", 1, "rootcause",
				"correctiveaction")).andExpect(status().isOk());
		assertEquals("Resolved", adminController.resolveCard(1, "rootcause", "correctiveaction"));

	}

	@Test
	public void assignCountTest() throws Exception {
		when(this.adminServiceImpl.assignedcount()).thenReturn(1);
		mockMvc.perform(get("/GreenCard/admin/assignedcount")).andExpect(status().isOk());
		assertEquals(1, adminController.assignedcount());
	}

	@Test
	public void RejectedCountTest() throws Exception {
		when(this.adminServiceImpl.closedcount()).thenReturn(1);
		mockMvc.perform(get("/GreenCard/admin/closedcount")).andExpect(status().isOk());
		assertEquals(1, adminController.closedcount());
	}

	@Test
	public void FullCountTest() throws Exception {
		when(this.adminServiceImpl.rejectcount()).thenReturn(1);
		mockMvc.perform(get("/GreenCard/admin/rejectcount")).andExpect(status().isOk());
		assertEquals(1, adminController.rejectcount());
	}

	@Test
	public void closedCountTest() throws Exception {
		when(this.adminServiceImpl.fullcount()).thenReturn(1);
		mockMvc.perform(get("/GreenCard/admin/fullcount")).andExpect(status().isOk());
		assertEquals(1, adminController.fullcount());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
