package com.mindtree.greencard.service;

import java.math.BigInteger;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.NewGreenCard;

public interface GreenCardService {
public String saveNewGreenCard(CommonsMultipartFile fileupload,String what,String location,String mid);
public String saveNewGreenCardByGuest(CommonsMultipartFile fileupload,String what,String location,String name,BigInteger phone);
public GreenCardLifeCycle getGreenCardById( int newGreenCard);
}
