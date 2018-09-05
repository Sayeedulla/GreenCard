package com.mindtree.greencard.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.service.SubAdminService;
@Service
public class SubAdminServiceImpl implements SubAdminService{

	@Autowired
	InProgressGreenCardRepository inProgGCRepo;

	@Autowired
	NewGreenCardRepository newGCRepo;
	
	@Autowired
	GreenCardLifeCycleRepository greencardLCRepo;

	@Override
	public List<InProgressGreenCard> getComplaints(String mid) {
		return inProgGCRepo.getComplaints(mid);
	}

	@Override
	public NewGreenCard getData(int gcid) {
		return newGCRepo.getOne(gcid);
	}

	@Override
	public InProgressGreenCard getSubadmin(int gcid) {
		return inProgGCRepo.getSubadmin(gcid);
	}

	@Override
	public String updateComplaint(InProgressGreenCard sub) {
		if (inProgGCRepo.existsById(sub.getlId())) {
			inProgGCRepo.save(sub);
			GreenCardLifeCycle greencardLC = greencardLCRepo.getOne(sub.getlId());
			greencardLC.setStatus("Solved");
			greencardLCRepo.save(greencardLC);
			return "Passed for Review";
		}
		else {
			return "Particular complaint not exist";
		}
	}

}
