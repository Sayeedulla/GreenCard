package com.mindtree.greencard.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.SubAdminService;

@Service
public class SubAdminServiceImpl implements SubAdminService {

	@Autowired
	InProgressGreenCardRepository inProgGCRepo;

	@Autowired
	NewGreenCardRepository newGCRepo;

	@Autowired
	GreenCardLifeCycleRepository greencardLCRepo;

	@Autowired
	GreenCardHistoryRepository gcHR;

	@Autowired
	User u;
	
	@Autowired
	GreenCardHistory gcH;
	
	@Autowired
	UserRepository us;

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
			inProgGCRepo.save(sub);
			GreenCardLifeCycle greencardLC = greencardLCRepo.getOne(sub.getlId());
			NewGreenCard ngc = newGCRepo.getOne(sub.getGcId());
			greencardLC.setStatus("Done");
			greencardLC.setResolvedTime(LocalDateTime.now());
			greencardLCRepo.save(greencardLC);
			gcH.setgId(sub.getGcId());
			gcH.setAssignedPersonId(sub.getAssignedPersonId());
			gcH.setCategory(sub.getCategory());
			gcH.setClosedDateTime(greencardLC.getResolvedTime());
			gcH.setCorrectiveAction(sub.getCorrectiveAction());
			gcH.setImage(ngc.getImage());
			gcH.setLandmark(ngc.getLandmark());
			gcH.setRootCause(sub.getRootCause());
			gcH.setStatus(greencardLC.getStatus());
			//gcH.setUserId();
			gcH.setSubmittedDateTime(greencardLC.getSubmittedTime
			gcH.setWhatHappened(ngc.getWhatHappened());
			gcHR.save(gcH);
			
// 			newGCRepo.delete(ngc);
 			inProgGCRepo.delete(sub);
// 			greencardLCRepo.delete(greencardLC);
			return "Complaint Resolved";		
	}
}