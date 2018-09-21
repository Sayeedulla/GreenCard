//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.math.BigInteger;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mindtree.greencard.controller.GreenCardController;
//import com.mindtree.greencard.service.GreenCardService;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class GreenCardControllerTestForSaveGuest {
//	@InjectMocks
//	GreenCardController greenCardController;
//	@Mock
//	@Autowired
//	GreenCardService greenCardService;
//	private MockMvc mockMvc;
//
//	@Before
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(greenCardController).build();
//	}
//
//	private static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Test
//	public void saveNewGreenCardByGuest() throws Exception {
//		CommonsMultipartFile commonsMultipartFile=null;
//		String what="Live Wire";
//		String location="Amla";
//		String name="Tarun";
//		BigInteger phone=null;
//		when(greenCardService.saveNewGreenCardByGuest(commonsMultipartFile, what, location, name, phone))
//		.thenReturn("success");
//		MockMultipartFile mockMultipartFile =
//                new MockMultipartFile("file", "FileUploadTest.txt", "text/plain", "This is a Test".getBytes());
//		mockMvc.perform(post("/GreenCard/add/greenCardByGuest")
//				.contentType(MediaType.ALL).content(mockMultipartFile.getBytes()))
//				.andExpect(status().isOk());
//		
////        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload")
////                .file(mockMultipartFile));
//		
//		
////		assertEquals(greenCardController.saveNewGreenCardServiceByGuest(commonsMultipartFile, what, location, name, phone), "success");
//	}
//}
