import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.entity.GreenCardLifeCycle;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.service.serviceimpl.GreenCardServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GreenCardServiceTest {
	@InjectMocks
	GreenCardServiceImpl greenCardServiceImpl;
	@Mock
	NewGreenCardRepository newgreencardrepository;
	@Mock
	GreenCardLifeCycleRepository greenCardLifeCycleRepository;
	@Mock
	UserRepository userrepository;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		NewGreenCard newGreenCard=new NewGreenCard();
		newGreenCard.setGreenCardId(1);
		newGreenCard.setLandmark("Amla");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		newGreenCard.setWhatHappened("Amla");
		newGreenCard.setImage(null);
		
		GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
		greenCardLifeCycle.setStatus("open");
		greenCardLifeCycle.setNewgreencard(newGreenCard);
		greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
		when(newgreencardrepository.save(newGreenCard)).thenReturn(newGreenCard);
		when(greenCardLifeCycleRepository.save(greenCardLifeCycle)).thenReturn(greenCardLifeCycle);
	}
	@Test
	public void saveGreenCardByGuest() throws GreenCardException, IOException {
		NewGreenCard newGreenCard=new NewGreenCard();
		newGreenCard.setGreenCardId(1);
		newGreenCard.setLandmark("Amla");
		newGreenCard.setSubmittedDate(LocalDateTime.now());
		newGreenCard.setWhatHappened("Amla");
		newGreenCard.setImage(null);
		CommonsMultipartFile commonsMultipartFile=null;
		BigInteger i=null;
		String str="Your GreenCard Id is null with status Open Note it down for future Reference";
		assertEquals(greenCardServiceImpl.saveNewGreenCardByGuest(commonsMultipartFile, "Amla", "Amla","Tarun",i), str);
		
		
	}
}
