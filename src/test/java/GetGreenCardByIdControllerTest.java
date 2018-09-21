import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.greencard.controller.GreenCardController;
import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.service.GreenCardService;

@RunWith(MockitoJUnitRunner.class)
public class GetGreenCardByIdControllerTest {
	@InjectMocks
	GreenCardController greenCardController;
	@Mock
	@Autowired
	GreenCardService greenCardService;
	private MockMvc mockMvc;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(greenCardController).build();
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void getGreenCardById() throws Exception {
		NewGreenCard newGreenCard=new NewGreenCard();
		newGreenCard.setLandmark("Live wire");
		newGreenCard.setWhatHappened("Amla");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setNewgreencard(newGreenCard);
		
		when(greenCardService.getGreenCardById(1)).thenReturn(greenCardLifeCycle);
		mockMvc.perform(get("/GreenCard/viewGreenCard/{id}",1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status", is("Open")));
		}
	@Test
	public void getGreenCardByIdException() throws Exception{
		NewGreenCard newGreenCard=new NewGreenCard();
		newGreenCard.setLandmark("Live wire");
		newGreenCard.setWhatHappened("Amla");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setStatus("Open");
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		greenCardLifeCycle.setNewgreencard(newGreenCard);
		
		when(greenCardService.getGreenCardById(0)).thenThrow(new GreenCardException());
		mockMvc.perform(get("/GreenCard/viewGreenCard/{id}",0));
		
	}
}
