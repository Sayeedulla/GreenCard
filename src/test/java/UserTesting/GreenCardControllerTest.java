package UserTesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.greencard.controller.GreenCardController;
import com.mindtree.greencard.model.FeedBack;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.GreenCardServiceImpl;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GreenCardControllerTest {
	@Autowired
	@InjectMocks
	GreenCardController greenCardController;
	@Mock
	UserServiceImpl userServiceImpl;
	@Mock
	GreenCardServiceImpl greenCardServiceImpl;
	MockMvc mockMvc;
	@Before
    public void testSetUp() {
           MockitoAnnotations.initMocks(this);
           mockMvc = MockMvcBuilders.standaloneSetup(greenCardController).build();

    }
	@Test
	public void getUserByMidTest() throws Exception {
		User user=new User();
		user.setMid("M1046908");
		user.setName("Sayeed");
		user.setPassword("Pass@123");
		when(userServiceImpl.getUserInfoByMid(user)).thenReturn("User");
	       mockMvc.perform(post("/GreenCard/userInfoByMid").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
           .andExpect(status().isOk());

assertEquals("User", greenCardController.getUserInfoByMid(user));

		
		
	}
	@Test
	public void sendFeedBackTest() throws Exception {
		FeedBack feedBack=new FeedBack();
		
		feedBack.setId(1);
		feedBack.setRating(4.0);
		feedBack.setComment("Hello");
		when(userServiceImpl.saveFeedBack(feedBack)).thenReturn("Thank you for providing Feedback");
		mockMvc.perform(post("/GreenCard/feedback").contentType(MediaType.APPLICATION_JSON).content(asJsonString(feedBack))).andExpect(status().isOk());
		assertEquals("Thank you for providing Feedback", greenCardController.sendFeedback(feedBack));
	}
	@Test
	public void addGreenCardByUserTest() throws Exception {
		when(greenCardServiceImpl.saveNewGreenCard(null, "ElectricCurrent","Bahada","M1046908")).thenReturn("Your GreenCard Id is 1 Note it down for future Reference");
		//mockMvc.perform(post("/GreenCard/add/greenCard").contentType(MediaType.MULTIPART_FORM_DATA).accept(null,"ElectricCurrent","Bahada","M1046908")).andExpect(status().isOk());
		assertEquals("Your GreenCard Id is 1 Note it down for future Reference", greenCardController.saveNewGreenCardService(null, "ElectricCurrent", "Bahada","M1046908"));
	}
	public static String asJsonString(final Object obj) {
        try {
               return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
               throw new RuntimeException(e);
        }
 }



}
