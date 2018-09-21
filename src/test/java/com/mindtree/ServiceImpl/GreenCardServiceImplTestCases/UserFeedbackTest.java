package com.mindtree.ServiceImpl.GreenCardServiceImplTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.greencardrepository.Feedbackrepository;
import com.mindtree.greencard.model.FeedBack;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)
public class UserFeedbackTest {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	Feedbackrepository feedbackrepository;

	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void saveFeedback() throws GreenCardException {
		FeedBack feedback = new FeedBack();
		feedback.setRating(4.0);
		feedback.setComment("Thank you");
		when(feedbackrepository.save(feedback)).thenReturn(feedback);
		FeedBack feedBack1 = new FeedBack();
		feedBack1.setRating(4.0);
		feedBack1.setComment("Thank you");
		FeedBack feedBack2 = new FeedBack();
		feedBack2.setComment(null);
		feedBack2.setRating(null);
		assertEquals("Thank you for providing Feedback", userServiceImpl.saveFeedBack(feedBack1));
		// assertEquals(userServiceImpl.saveFeedBack(feedBack2),"can't save feedback");

	}

}
