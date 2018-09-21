package com.mindtree.ServiceImpl.GreenCardServiceImplTestCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.quality.Strictness;
import org.mockito.runners.MockitoJUnitRunner;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.serviceimpl.GreenCardServiceImpl;


@RunWith(org.mockito.junit.MockitoJUnitRunner.Silent.class)

public class SaveGreenCardByUserTest {
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
                    newGreenCard.setGreenCardId(1);;
                    newGreenCard.setLandmark("Amla");
                    newGreenCard.setSubmittedDate(LocalDateTime.now());
                    newGreenCard.setWhatHappened("Amla");
                    newGreenCard.setImage(null);
                    User user=new User();
                    Optional<User> user1;
                    
                    user.setMid("M1046908");
                    user.setName("Sayeed");
                    user.setEmailId("sayeed150696@gmail.com");
                    user.setUserId(1);
                    user.setPassword("Pass@123");
                    
                  
                    user.getNewGreenCards().add(newGreenCard);
                    user1=Optional.of(user);
                    
                    GreenCardLifeCycle greenCardLifeCycle=new GreenCardLifeCycle();
                    greenCardLifeCycle.setStatus("open");
                    greenCardLifeCycle.setNewgreencard(newGreenCard);
                    greenCardLifeCycle.setSubmittedTime(LocalDateTime.now());
                    
                    when(userrepository.findUser("M1046908")).thenReturn(user1);
                    when(userrepository.save(user)).thenReturn(user);
//                    when(newgreencardrepository.save(newGreenCard)).thenReturn(newGreenCard);
//                    when(greenCardLifeCycleRepository.save(greenCardLifeCycle)).thenReturn(greenCardLifeCycle);
    }
    @Test
    public void saveGreenCard() throws GreenCardException, IOException {
                 
                    String str="Your GreenCard Id is null Note it down for future Reference";
                    assertEquals(greenCardServiceImpl.saveNewGreenCard(null, "Amla","Amla", "M1046908"),str);
                    
                    
    }
}



