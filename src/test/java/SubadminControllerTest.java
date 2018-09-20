//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mindtree.greencard.controller.SubAdminController;
//import com.mindtree.greencard.model.InProgressGreenCard;
//import com.mindtree.greencard.service.SubAdminService;
//
//public class SubadminControllerTest {
//
//	private MockMvc mockMvc;
//	@InjectMocks
//	SubAdminController subCon=new SubAdminController();
//
//	@Mock
//	SubAdminService subServ;
//
//	@Spy
//	private static List<InProgressGreenCard> complaints = new ArrayList<InProgressGreenCard>();
//
//	@BeforeClass
//	public void testSetUp() {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(subCon).build();
//		complaints=getComplaints();
//		when(subServ.getComplaints("M1046874")).thenReturn(complaints);
//	}
//
//	@Test
//	public void getAllComplaints() throws Exception {
//
//		mockMvc.perform(get("/getAllComplaints/M1046874")).andExpect(status().isOk())
//		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}
//
//	public static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static List<InProgressGreenCard> getComplaints() {
//		List<InProgressGreenCard> complaints = new ArrayList<InProgressGreenCard>();
//		InProgressGreenCard complaint = new InProgressGreenCard();
//		complaint.setAssignedPersonId("M1046874");
//		complaint.setCategory("Health");
//		complaint.setGcId(1);
//		complaint.setlId(1);
//		complaint.setPriority("High");
//		complaint.setStatus("Assigned");
//		complaints.add(complaint);
//		complaint = new InProgressGreenCard();
//		complaint.setAssignedPersonId("M1046874");
//		complaint.setCategory("Health");
//		complaint.setGcId(2);
//		complaint.setlId(2);
//		complaint.setPriority("High");
//		complaint.setStatus("Assigned");
//		complaints.add(complaint);
//		complaint = new InProgressGreenCard();
//		complaint.setAssignedPersonId("M1046874");
//		complaint.setCategory("Health");
//		complaint.setGcId(3);
//		complaint.setlId(3);
//		complaint.setPriority("High");
//		complaint.setStatus("Assigned");
//		complaints.add(complaint);
//		return complaints;
//	}
//}