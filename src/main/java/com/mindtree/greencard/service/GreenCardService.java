package com.mindtree.greencard.service;

import java.math.BigInteger;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mindtree.greencard.exception.GreenCardException;
import com.mindtree.greencard.model.GreenCardLifeCycle;

public interface GreenCardService {
	public String saveNewGreenCard(CommonsMultipartFile fileupload, String what, String location, String mid) throws GreenCardException;

	public String saveNewGreenCardByGuest(CommonsMultipartFile fileupload, String what, String location, String name,
			BigInteger phone) throws GreenCardException;

	public GreenCardLifeCycle getGreenCardById(int newGreenCard) throws GreenCardException;

}
