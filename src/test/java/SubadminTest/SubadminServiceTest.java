package SubadminTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.greencard.exception.subadminserviceexception.EmptyListException;
import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.SubAdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SubadminServiceTest {

	@InjectMocks
	SubAdminServiceImpl subServ;

	@Mock
	InProgressGreenCardRepository inpgcrepo;

	@Mock
	NewGreenCardRepository ngcrepo;

	@Mock
	CategoryRepository carepo;

	@Mock
	SubAdminCategoryRepository screpo;

	@Mock
	GreenCardLifeCycleRepository gclcrepo;

	@Mock
	GreenCardHistoryRepository gchrepo;

	@Spy
	private static List<InProgressGreenCard> complaints = new ArrayList<InProgressGreenCard>();

	@Spy
	private static NewGreenCard ngcc = new NewGreenCard();

	@Spy
	private static List<Category> cate = new ArrayList<Category>();

	@Spy
	private static List<SubAdminCategory> subad = new ArrayList<SubAdminCategory>();

	@Spy
	private static InProgressGreenCard inpGC = new InProgressGreenCard();

	@Spy
	private static GreenCardLifeCycle gclc = new GreenCardLifeCycle();

	@Spy
	private static GreenCardHistory gch = new GreenCardHistory();

	@Test
	public void getAllComplaints() throws Exception {

		complaints = getComplaints();

		when(inpgcrepo.getComplaints("M1046874")).thenReturn(complaints);
		assertEquals(3, subServ.getComplaints("M1046874").size());
	}

	@Test
	public void getAllComplaintsException() throws Exception {

		complaints = getComplaints();
		doThrow(new EmptyListException()).when(inpgcrepo).getComplaints("M1046874");
		// when(inpgcrepo.getComplaints("M1046874")).thenThrow(new
		// EmptyListException());
		subServ.getComplaints("M1046874");
	}

	@Test
	public void getComplaint() throws Exception {

		ngcc = getComplaintData();

		when(ngcrepo.existsById(1)).thenReturn(true);
		when(ngcrepo.getOne(1)).thenReturn(ngcc);
		assertEquals(1, subServ.getData(1).getGreenCardId().intValue());
		assertEquals("Amla", subServ.getData(1).getLandmark());
		assertEquals("LiveWire", subServ.getData(1).getWhatHappened());
	}

	@Test
	public void getCategory() throws Exception {

		cate = getCategoryData();
		when(carepo.findAll()).thenReturn(cate);
		assertEquals(2, subServ.getCategory().size());
	}

	@Test
	public void getSubadmin() throws Exception {

		subad = getSubadminData();
		when(screpo.getSubadmins("HEALTH")).thenReturn(subad);

		assertEquals(2, subServ.getSubadmins("HEALTH").size());
	}

	@Test
	public void resolveComplaint() throws Exception {
		inpGC = getInprogData();
		gclc = getGCLC();
		gch = getGCH();
		ngcc = getComplaintData();
		when(inpgcrepo.existsById(1)).thenReturn(true);
		when(inpgcrepo.save(inpGC)).thenReturn(inpGC);
		when(gclcrepo.getOne(1)).thenReturn(gclc);
		when(ngcrepo.getOne(1)).thenReturn(ngcc);
		when(gclcrepo.save(gclc)).thenReturn(gclc);
		// when(gchrepo.save(null)).thenReturn(gch);
		assertEquals("Complaint 1 is resolved", subServ.updateComplaint(inpGC));
	}

	@Test
	public void reassignComplaint() throws Exception {
		inpGC = getInprogData();
		when(inpgcrepo.existsById(1)).thenReturn(true);
		when(inpgcrepo.save(inpGC)).thenReturn(inpGC);
		assertEquals("Complaint 1 is reassigned to M1046871 of SAFETY", subServ.reassignComplaint(inpGC));
	}

	public static GreenCardHistory getGCH() {
		GreenCardHistory gch = new GreenCardHistory();
		gch.setAssignedPersonId("M10046874");
		gch.setCategory("HEALTH");
		gch.setClosedDateTime(LocalDateTime.now());
		gch.setCorrectiveAction("Changed");
		gch.setgId(1);
		gch.setImage(null);
		gch.setLandmark("Amla");
		gch.setPriority("High");
		gch.setRootCause("Changed");
		gch.setStatus("Closed");
		gch.setSubmittedDateTime(LocalDateTime.now());
		gch.setWhatHappened("LiveWire");
		return gch;
	}

	public static GreenCardLifeCycle getGCLC() {
		GreenCardLifeCycle gclc = new GreenCardLifeCycle();
		ngcc = getComplaintData();
		gclc.setAssignedTime(LocalDateTime.now());
		gclc.setLifeCycleId(1);
		gclc.setNewgreencard(ngcc);
		gclc.setResolvedTime(LocalDateTime.now());
		gclc.setStatus("Assigned");
		gclc.setSubmittedTime(LocalDateTime.now());
		gclc.setTimePeriod("days");
		return gclc;
	}

	public static InProgressGreenCard getInprogData() {
		InProgressGreenCard inpGC = new InProgressGreenCard();
		inpGC.setAssignedPersonId("M1046871");
		inpGC.setCategory("SAFETY");
		inpGC.setCorrectiveAction("Changed");
		inpGC.setGcId(1);
		inpGC.setlId(1);
		inpGC.setPriority("High");
		inpGC.setRootCause("Oldone");
		inpGC.setStatus("Assigned");
		return inpGC;
	}

	public static List<SubAdminCategory> getSubadminData() {
		List<SubAdminCategory> subadmins = new ArrayList<SubAdminCategory>();
		SubAdminCategory subadmin = new SubAdminCategory();
		subadmin.setMid("M1046874");
		subadmin.setCategoryName("HEALTH");
		subadmins.add(subadmin);
		subadmin = new SubAdminCategory();
		subadmin.setMid("M1046871");
		subadmin.setCategoryName("SAFETY");
		subadmins.add(subadmin);
		return subadmins;
	}

	public static List<Category> getCategoryData() {
		List<Category> categories = new ArrayList<Category>();
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("HEALTH");
		categories.add(category);
		category = new Category();
		category.setCategoryId(2);
		category.setCategoryName("HEALTH");
		categories.add(category);
		return categories;
	}

	public static NewGreenCard getComplaintData() {
		NewGreenCard ngc = new NewGreenCard();
		ngc.setGreenCardId(1);
		ngc.setLandmark("Amla");
		ngc.setWhatHappened("LiveWire");
		ngc.setSubmittedDate(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
		ngc.setImage(null);
		return ngc;
	}

	public static List<InProgressGreenCard> getComplaints() {
		List<InProgressGreenCard> complaints = new ArrayList<InProgressGreenCard>();
		InProgressGreenCard complaint = new InProgressGreenCard();
		complaint.setAssignedPersonId("M1046874");
		complaint.setCategory("Health");
		complaint.setGcId(1);
		complaint.setlId(1);
		complaint.setPriority("High");
		complaint.setStatus("Assigned");
		complaints.add(complaint);
		complaint = new InProgressGreenCard();
		complaint.setAssignedPersonId("M1046874");
		complaint.setCategory("Health");
		complaint.setGcId(2);
		complaint.setlId(2);
		complaint.setPriority("High");
		complaint.setStatus("Assigned");
		complaints.add(complaint);
		complaint = new InProgressGreenCard();
		complaint.setAssignedPersonId("M1046874");
		complaint.setCategory("Health");
		complaint.setGcId(3);
		complaint.setlId(3);
		complaint.setPriority("High");
		complaint.setStatus("Assigned");
		complaints.add(complaint);
		return complaints;
	}
}