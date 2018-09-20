package UserTesting;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	UserRepository userrepository;


	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);
		User user=new User();
		user.setMid("M1046890");
		String sha256hex = DigestUtils.sha256Hex("Pass@123");
		user.setPassword(sha256hex);
		when(userrepository.findUserbymidPassword(user.getMid(), sha256hex)).thenReturn(user);
	}
	@Test
	public void getUserByMidPass() throws GreenCardException {
		User user=new User();
		user.setMid("M1046890");
		user.setPassword("Pass@123");
		assertEquals(userServiceImpl.getUserInfoByMidAndPassword(user).getMid(), user.getMid());
	}
}
