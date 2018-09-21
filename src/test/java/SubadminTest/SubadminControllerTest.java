package SubadminTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.greencard.controller.SubAdminController;
import com.mindtree.greencard.exception.subadminserviceexception.ServiceException;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.service.serviceimpl.SubAdminServiceImpl;

public class SubadminControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	SubAdminController subCon;

	@Mock
	SubAdminServiceImpl subServ;

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

	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(subCon).build();

	}

	@Test
	public void getAllComplaints() throws Exception {

		complaints = getComplaints();

		when(subServ.getComplaints("M1046874")).thenReturn(complaints);

		mockMvc.perform(get("/getAllComplaints/{mid}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		assertEquals(3, subCon.getComplaints("M1046874").size());
	}

	@Test
	public void getAllComplaintsExcept() throws Exception {

		complaints = getComplaints();

		when(subServ.getComplaints("M1046874")).thenThrow(new ServiceException("List is Empty"));

		mockMvc.perform(get("/getAllComplaints/{mid}", "M1046874")).andExpect(status().isOk());
	}

	@Test
	public void getComplaint() throws Exception {

		ngcc = getComplaintData();

		when(subServ.getData(1)).thenReturn(ngcc);

		mockMvc.perform(get("/getComplaintData/{a}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.greenCardId", is(1))).andExpect(jsonPath("$.whatHappened", is("LiveWire")))
				.andExpect(jsonPath("$.landmark", is("Amla")));
	}

	@Test
	public void getComplaintExcept() throws Exception {

		ngcc = getComplaintData();
		when(subServ.getData(1)).thenThrow(new ServiceException("Particular Complaint not exist"));
		mockMvc.perform(get("/getComplaintData/{a}", 1)).andExpect(status().isOk());
	}

	@Test
	public void getCategory() throws Exception {

		cate = getCategoryData();
		when(subServ.getCategory()).thenReturn(cate);

		mockMvc.perform(get("/getCategory")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		assertEquals(2, subCon.getCategories().size());
	}

	@Test
	public void getCategoryExcept() throws Exception {

		cate = getCategoryData();
		when(subServ.getCategory()).thenThrow(new ServiceException("List is empty"));

		mockMvc.perform(get("/getCategory")).andExpect(status().isOk());

	}

	@Test
	public void getSubadmin() throws Exception {

		subad = getSubadminData();
		when(subServ.getSubadmins("HEALTH")).thenReturn(subad);

		mockMvc.perform(get("/getSubadmins/{category}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		assertEquals(2, subCon.getSubadmins("HEALTH").size());
	}

	@Test
	public void getSubadminExcept() throws Exception {

		subad = getSubadminData();
		when(subServ.getSubadmins("HEALTH")).thenThrow(new ServiceException("List is empty"));

		mockMvc.perform(get("/getSubadmins/{category}", "HEALTH")).andExpect(status().isOk());
	}

	@Test
	public void resolveComplaint() throws Exception {
		inpGC = getInprogData();
		when(subServ.updateComplaint(inpGC)).thenReturn("Complaint " + inpGC.getGcId() + " is resolved");

		mockMvc.perform(post("/updateComplaint").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inpGC)))
				.andExpect(status().isOk());

		assertEquals("Complaint 1 is resolved", subCon.updateComplaint(inpGC));
	}

	@Test
	public void resolveComplaintExcept() throws Exception {
		inpGC = getInprogData();
		when(subServ.updateComplaint(inpGC)).thenThrow(new ServiceException("Requested Complaint not exist"));

		mockMvc.perform(post("/updateComplaint").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inpGC)))
				.andExpect(status().isOk());
		subCon.updateComplaint(inpGC);
	}

	@Test
	public void reassignComplaint() throws Exception {
		inpGC = getInprogData();
		when(subServ.reassignComplaint(inpGC)).thenReturn("Complaint " + inpGC.getGcId() + " is reassigned to "
				+ inpGC.getAssignedPersonId() + " of " + inpGC.getCategory());

		mockMvc.perform(post("/reassignComplaint").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inpGC)))
				.andExpect(status().isOk());

		assertEquals("Complaint 1 is reassigned to M1046871 of SAFETY", subCon.reassignComplaint(inpGC));
	}

	@Test
	public void reassignComplaintExcept() throws Exception {
		inpGC = getInprogData();
		when(subServ.reassignComplaint(inpGC)).thenThrow(new ServiceException("Requested Complaint not exist"));

		mockMvc.perform(post("/reassignComplaint").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inpGC)))
				.andExpect(status().isOk());
		subCon.reassignComplaint(inpGC);
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

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}