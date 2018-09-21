import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.entity.GreenCardLifeCycle;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.service.serviceimpl.GreenCardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GetGreenCardByIdServiceTest {
	@InjectMocks
	GreenCardServiceImpl greenCardServiceImpl;
	@Mock
	NewGreenCardRepository newGreenCardRepository;
	@Mock
	GreenCardLifeCycleRepository greenCardLifeCycleRepository;
	@Before
	public void setUp() throws GreenCardException {
		
		NewGreenCard newGreenCard=new NewGreenCard();
		newGreenCard.setGreenCardId(1);
		newGreenCard.setLandmark("Amla");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		newGreenCard.setWhatHappened("Amla");
		newGreenCard.setImage(null);
	}
	@Test
	public void getGreenCardByIds() throws GreenCardException {
		NewGreenCard newGreenCard=new NewGreenCard();
		newGreenCard.setGreenCardId(1);
		newGreenCard.setLandmark("Amla");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		newGreenCard.setWhatHappened("Amla");
		newGreenCard.setImage(null);

		when(newGreenCardRepository.getOne(1)).thenReturn(newGreenCard);
		
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		greenCardLifeCycle.setAssignedTime(LocalDateTime.now());
		greenCardLifeCycle.setNewgreencard(newGreenCard);
		greenCardLifeCycle.setStatus("Open");
		when(greenCardLifeCycleRepository.getGreenCardById(newGreenCard)).thenReturn(greenCardLifeCycle);
		
		assertEquals(greenCardServiceImpl.getGreenCardById(1).getNewgreencard().getLandmark(), newGreenCard.getLandmark());
	}

}
