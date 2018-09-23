package com.mindtree.serviceimpl.adminserviceimpltestcases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.greencard.entity.GreenCardHistory;
import com.mindtree.greencard.entity.GreenCardLifeCycle;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.service.serviceimpl.AdminServiceImpl;

public class AdminServiceImplTest {
	@InjectMocks
	AdminServiceImpl adminServiceImpl;
	@Mock
	UserRepository userRepository;
	@Mock
	GreenCardHistoryRepository greenCardHistoryRepository;
	@Mock
	GreenCardLifeCycleRepository grCardLifeCycleRepository;
	@Mock
	NewGreenCardRepository newGreenCardRepository;
	@Mock
	SubAdminCategoryRepository subAdminCategoryRepository;
	@Mock
	InProgressGreenCardRepository inProgressGreenCardRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getForSubAdminTest() {
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
		when(greenCardHistoryRepository.getExceptImgForSubadmin("M1046908")).thenReturn(l);
		assertEquals(l, adminServiceImpl.getForSubadmin("M1046908"));
	}

	@Test
	public void newCountTest() {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setLifeCycleId(1);
		greenCardLifeCycle.setNewgreencard(null);
		greenCardLifeCycle.setResolvedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setTimePeriod("Time");
		List<GreenCardLifeCycle> l = new ArrayList<GreenCardLifeCycle>();
		l.add(greenCardLifeCycle);
		when(grCardLifeCycleRepository.getOpenGreenCard()).thenReturn(l);
		assertEquals(1, adminServiceImpl.newcount());

	}

	@Test
	public void assignedCountTest() {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setLifeCycleId(1);
		greenCardLifeCycle.setNewgreencard(null);
		greenCardLifeCycle.setResolvedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setTimePeriod("Time");
		List<GreenCardLifeCycle> l = new ArrayList<GreenCardLifeCycle>();
		l.add(greenCardLifeCycle);
		when(grCardLifeCycleRepository.getAssignedGreenCard()).thenReturn(l);
		assertEquals(1, adminServiceImpl.assignedcount());
	}

	@Test
	public void closedCountTest() {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setLifeCycleId(1);
		greenCardLifeCycle.setNewgreencard(null);
		greenCardLifeCycle.setResolvedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setTimePeriod("Time");
		List<GreenCardLifeCycle> l = new ArrayList<GreenCardLifeCycle>();
		l.add(greenCardLifeCycle);
		when(grCardLifeCycleRepository.getClosedGreenCard()).thenReturn(l);
		assertEquals(1, adminServiceImpl.closedcount());
	}

	@Test
	public void RejectedCountTest() {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setLifeCycleId(1);
		greenCardLifeCycle.setNewgreencard(null);
		greenCardLifeCycle.setResolvedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setTimePeriod("Time");
		List<GreenCardLifeCycle> l = new ArrayList<GreenCardLifeCycle>();
		l.add(greenCardLifeCycle);
		when(grCardLifeCycleRepository.getRejectedGreenCard()).thenReturn(l);
		assertEquals(1, adminServiceImpl.rejectcount());
	}

	@Test
	public void FullCountTest() {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setLifeCycleId(1);
		greenCardLifeCycle.setNewgreencard(null);
		greenCardLifeCycle.setResolvedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setTimePeriod("Time");
		List<GreenCardLifeCycle> l = new ArrayList<GreenCardLifeCycle>();
		l.add(greenCardLifeCycle);
		when(grCardLifeCycleRepository.getFullGreenCard()).thenReturn(l);
		assertEquals(1, adminServiceImpl.fullcount());
	}

	@Test
	public void getNewGreencardTest() {
		GreenCardLifeCycle greenCardLifeCycle = new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setLifeCycleId(1);
		greenCardLifeCycle.setNewgreencard(null);
		greenCardLifeCycle.setResolvedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setTimePeriod("Time");
		List<GreenCardLifeCycle> l = new ArrayList<GreenCardLifeCycle>();
		l.add(greenCardLifeCycle);
		List<NewGreenCard> list = new ArrayList<>();
		when(grCardLifeCycleRepository.getOpenGreenCard()).thenReturn(l);
		assertEquals(list, adminServiceImpl.newComplaints());

	}

	@Test
	public void viewProgresstest() {
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
		when(inProgressGreenCardRepository.findAll()).thenReturn(l);
		assertEquals(l, adminServiceImpl.viewprogress());
	}

	@Test
	public void getAllFromhistorytest() {
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
		when(greenCardHistoryRepository.getAllExceptImg()).thenReturn(l);

		assertEquals(l, adminServiceImpl.getAllFromHistory());
	}

	@Test
	public void getSubAdminstest() {
		SubAdminCategory subAdminCategory = new SubAdminCategory();
		subAdminCategory.setMid("M1046908");
		subAdminCategory.setCategoryName("Health");
		List<SubAdminCategory> l = new ArrayList<SubAdminCategory>();
		l.add(subAdminCategory);
		when(subAdminCategoryRepository.findAll()).thenReturn(l);

		assertEquals(l, adminServiceImpl.getSubAdmins());
	}

	@Test
	public void getbygidtest() {
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
		Optional<GreenCardHistory> greenhistory;
		greenhistory = Optional.ofNullable(greenCardHistory);
		when(greenCardHistoryRepository.findById(1)).thenReturn(greenhistory);
		assertEquals(greenhistory, adminServiceImpl.getByGid(1));
	}

	@Test
	public void getcard() {
		Optional<NewGreenCard> ngc = Optional.of(getComplaint());
		when(newGreenCardRepository.findById(1)).thenReturn(ngc);
		assertEquals(ngc, adminServiceImpl.getCard(1));
	}

	@Test
	public void getprogresscard() {
		Optional<InProgressGreenCard> ngc = Optional.of(getData());
		when(inProgressGreenCardRepository.findById(1)).thenReturn(ngc);
		assertEquals(ngc, adminServiceImpl.getprogressCard(1));
	}

	public NewGreenCard getComplaint() {
		NewGreenCard ngc1 = new NewGreenCard();
		ngc1.setGreenCardId(1);
		ngc1.setImage(null);
		ngc1.setLandmark("Amla");
		ngc1.setSubmittedDate(LocalDateTime.now());
		ngc1.setWhatHappened("Fire");
		return ngc1;
	}

	public InProgressGreenCard getData() {
		InProgressGreenCard ngc1 = new InProgressGreenCard();
		ngc1.setAssignedPersonId("M1046874");
		ngc1.setCategory("Health");
		ngc1.setGcId(1);
		ngc1.setPriority("High");
		ngc1.setlId(1);
		ngc1.setStatus("Assigned");
		return ngc1;
	}

}
