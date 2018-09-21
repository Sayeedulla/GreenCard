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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.greencard.controller.GreenCardController;
import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class GreenCardControllerTestForGetUserByMidPass {
	@InjectMocks
	GreenCardController greenCardController;
	@Mock
	@Autowired
	UserService userService;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws GreenCardException {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(greenCardController).build();	
	}

	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void getUserByMidPass() throws Exception {
		User user = new User();
		user.setMid("M1046885");
		user.setPassword("Pass@123");
		mockMvc.perform(
				post("/GreenCard/getuserinfo")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
		when(userService.getUserInfoByMidAndPassword(user)).thenReturn(user);
		assertEquals(greenCardController.returnUser(user),user);
	}
}
