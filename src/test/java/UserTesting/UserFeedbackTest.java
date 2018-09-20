package UserTesting;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.greencardrepository.Feedbackrepository;
import com.mindtree.greencard.model.FeedBack;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserFeedbackTest {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	Feedbackrepository feedbackrepository;
	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);
		FeedBack feedback=new FeedBack();
		feedback.setRating(4.0);
		feedback.setComment("Thank you");
		when(feedbackrepository.save(feedback)).thenReturn(feedback);
		
	}
	@Test
	public void saveFeedback() throws GreenCardException {
		FeedBack feedBack=new FeedBack();
		feedBack.setRating(4.0);
		feedBack.setComment("Thank you");
		FeedBack feedBack2=new FeedBack();
		feedBack2.setComment(null);
		feedBack2.setRating(null);
		assertEquals(userServiceImpl.saveFeedBack(feedBack), "Thank you for providing Feedback");
		//assertEquals(userServiceImpl.saveFeedBack(feedBack2),"can't save feedback");
		
	}


}
