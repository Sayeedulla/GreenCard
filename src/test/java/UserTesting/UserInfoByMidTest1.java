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
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInfoByMidTest1 {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	UserRepository userrepository;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		User user=new User();
		User user1=null;
		
		String sha256hex = DigestUtils.sha256Hex("Pass@123");
		user.setMid("M1046908");
		user.setPassword(sha256hex);
		when(userrepository.save(user)).thenReturn(user);
		when(userrepository.getUserByMid(user.getMid())).thenReturn(user1);
	}
	@Test
	public void getUserInfoByMid1() throws GreenCardException {
		User user=new User();
		user.setMid("M1046908");
		
		String sha256hex = DigestUtils.sha256Hex("Pass@123");
		user.setPassword(sha256hex);
		assertEquals(userServiceImpl.getUserInfoByMid(user),"User");
		
	}

}
