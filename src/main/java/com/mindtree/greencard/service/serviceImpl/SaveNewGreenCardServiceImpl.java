package com.mindtree.greencard.service.serviceImpl;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.dto.DTOClass;
import com.mindtree.greencard.service.SaveNewGreenCardService;

@Service
public class SaveNewGreenCardServiceImpl implements SaveNewGreenCardService {

	@Autowired
	
	
	@Transactional
	@Override
	public String saveNewGreenCard(DTOClass dtoClass) {
		return this.saveNewGreenCardDao.saveNewGreenCard(dtoClass);
	}

}