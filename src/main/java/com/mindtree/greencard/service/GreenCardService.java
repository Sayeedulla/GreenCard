package com.mindtree.greencard.service;

import java.math.BigInteger;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface GreenCardService {
public String SaveNewGreenCard(CommonsMultipartFile fileupload,String what,String location,String mid);
public String SaveNewGreenCardByGuest(CommonsMultipartFile fileupload,String what,String location,String name,BigInteger phone);
}
