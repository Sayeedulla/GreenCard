package com.mindtree.controller.greencardcontrollertestcases;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.greencard.controller.GreenCardController;
import com.mindtree.greencard.entity.FeedBack;
import com.mindtree.greencard.entity.User;
import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.exception.userserviceexception.FeedbackException;
import com.mindtree.greencard.exception.userserviceexception.GetInfoByMidException;
import com.mindtree.greencard.service.serviceimpl.GreenCardServiceImpl;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class GreenCardControllerExceptionTest {
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
		User user = new User();
		user.setMid("M1046908");
		user.setName("Sayeed");
		user.setPassword("Pass@123");
		when(userServiceImpl.getUserInfoByMid(user))
				.thenThrow(new GetInfoByMidException("sorry can't return the status"));
		mockMvc.perform(
				post("/GreenCard/userInfoByMid").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());

		assertEquals("sorry can't return the status", greenCardController.getUserInfoByMid(user));

	}

	@Test
	public void sendFeedBackTest() throws Exception {
		FeedBack feedBack = new FeedBack();

		feedBack.setId(1);
		feedBack.setRating(4.0);
		feedBack.setComment("Hello");
		when(userServiceImpl.saveFeedBack(feedBack)).thenThrow(new FeedbackException("can't save feedback"));
		mockMvc.perform(
				post("/GreenCard/feedback").contentType(MediaType.APPLICATION_JSON).content(asJsonString(feedBack)))
				.andExpect(status().isOk());
		assertEquals("can't save feedback", greenCardController.sendFeedback(feedBack));
	}

	@Test
	public void addGreenCardByUserTest() throws Exception {
		when(greenCardServiceImpl.saveNewGreenCard(null, "ElectricCurrent", "Bahada", "M1046908"))
				.thenThrow(new GreenCardException("Sorry can't save the green card"));

		assertEquals("Sorry can't save the green card",
				greenCardController.saveNewGreenCardService(null, "ElectricCurrent", "Bahada", "M1046908"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
