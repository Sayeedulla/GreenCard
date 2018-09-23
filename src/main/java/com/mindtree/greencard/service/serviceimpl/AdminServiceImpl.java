package com.mindtree.greencard.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.entity.GreenCardHistory;
import com.mindtree.greencard.entity.GreenCardLifeCycle;
import com.mindtree.greencard.entity.InProgressGreenCard;
import com.mindtree.greencard.entity.NewGreenCard;
import com.mindtree.greencard.entity.SubAdminCategory;
import com.mindtree.greencard.exception.adminexceptions.AdminException;
import com.mindtree.greencard.exception.adminexceptions.CardNotFoundException;
import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	NewGreenCardRepository newgreencard;

	@Autowired
	InProgressGreenCardRepository inprogresscard;

	@Autowired
	GreenCardHistoryRepository history;

	@Autowired
	SubAdminCategoryRepository subadmin;

	@Autowired
	GreenCardLifeCycleRepository glc;

	@Autowired
	UserRepository userRepository;

	@Autowired
	GreenCardHistory gcH;

	private static final String TIME_ZONE = "Asia/Calcutta";
	
    private static final String ASSIGNED = "Assigned";

    private static final String REJECTED = "Rejected";
	@Override
	public List<NewGreenCard> newComplaints() {
		return this.newgreencard.getNewCards();
	}

	@Override
	public Optional<NewGreenCard> getCard(int gid) {
		Optional<NewGreenCard> ngc = this.newgreencard.findById(gid);
		try {
			if (!ngc.isPresent()) {
				throw new CardNotFoundException("id " + gid + "not found");
			}
		} catch (CardNotFoundException e) {
			throw new AdminException(e.getMessage());
		}
		return ngc;
	}

	@Override
	public String assigncard(InProgressGreenCard card) {
		NewGreenCard ngc1 = this.newgreencard.getOne(card.getGcId());
		GreenCardLifeCycle glc1 = this.glc.getGreenCardById(ngc1);
		card.setlId(glc1.getLifeCycleId());
		this.inprogresscard.save(card);
		NewGreenCard newGreenCard = this.newgreencard.getOne(card.getGcId());
		GreenCardLifeCycle gcLifeCycle = this.glc.getGreenCardById(newGreenCard);
		newGreenCard.setStatus(ASSIGNED);
		gcLifeCycle.setStatus(ASSIGNED);
		gcLifeCycle.setAssignedTime(LocalDateTime.now(ZoneId.of(TIME_ZONE)));
		this.glc.save(gcLifeCycle);
		return ASSIGNED;
	}

	@Override
	public List<InProgressGreenCard> viewprogress() {
		return this.inprogresscard.findAll();
	}

	@Override
	public List<GreenCardHistory> getAllFromHistory() {
		return this.history.getAllExceptImg();
	}

	@Override
	public List<SubAdminCategory> getSubAdmins() {
		return this.subadmin.findAll();
	}

	@Override
	public Optional<GreenCardHistory> getByGid(int gId) {
		return this.history.findById(gId);
	}

	@Override
	public String rejectGreenCard(int gid) {
		NewGreenCard ngc = newgreencard.getNewCard(gid);
		GreenCardLifeCycle greencardLC = glc.getGreenCardById(ngc);
		greencardLC.setResolvedTime(LocalDateTime.now(ZoneId.of(TIME_ZONE)));
		greencardLC.setStatus(REJECTED);
		ngc.setStatus(REJECTED);
		glc.save(greencardLC);
		gcH.setgId(ngc.getGreenCardId());
		gcH.setAssignedPersonId1("N/A");
		gcH.setCategory("N/A");
		gcH.setClosedDateTime(greencardLC.getResolvedTime());
		gcH.setCorrectiveAction1("N/A");
		gcH.setImage(ngc.getImage());
		gcH.setLandmark(ngc.getLandmark());
		gcH.setRootCause1("N/A");
		gcH.setStatus1(greencardLC.getStatus());
		gcH.setSubmittedDateTime(greencardLC.getSubmittedTime());
		gcH.setWhatHappened(ngc.getWhatHappened());
		history.save(gcH);
		return REJECTED;
	}

	@Override
	public String resolveCard(int gid, String rootcause, String correctiveaction) {
		NewGreenCard ngc = newgreencard.getNewCard(gid);
		GreenCardLifeCycle greencardLC = glc.getGreenCardById(ngc);
		greencardLC.setResolvedTime(LocalDateTime.now(ZoneId.of(TIME_ZONE)));
		greencardLC.setStatus("Closed");
		glc.save(greencardLC);
		gcH.setgId(ngc.getGreenCardId());
		gcH.setAssignedPersonId1(userRepository.getAdmin() + " - Admin");
		gcH.setCategory("N/A");
		gcH.setClosedDateTime(greencardLC.getResolvedTime());
		gcH.setCorrectiveAction1(correctiveaction);
		gcH.setImage(ngc.getImage());
		gcH.setLandmark(ngc.getLandmark());
		gcH.setRootCause1(rootcause);
		gcH.setStatus1(greencardLC.getStatus());
		gcH.setSubmittedDateTime(greencardLC.getSubmittedTime());
		gcH.setWhatHappened(ngc.getWhatHappened());
		ngc.setStatus("Closed");
		history.save(gcH);
		return "Resolved";
	}

	@Override
	public Optional<InProgressGreenCard> getprogressCard(int gid) {
		return this.inprogresscard.findById(gid);
	}

	@Override
	public List<GreenCardHistory> getForSubadmin(String mid) {
		return this.history.getExceptImgForSubadmin(mid);
	}

	@Override
	public int newcount() {

		List<GreenCardLifeCycle> l = this.glc.getOpenGreenCard();
		return l.size();

	}

	@Override
	public int assignedcount() {
		List<GreenCardLifeCycle> l = this.glc.getAssignedGreenCard();
		return l.size();
	}

	@Override
	public int closedcount() {

		List<GreenCardLifeCycle> l = this.glc.getClosedGreenCard();
		return l.size();
	}

	@Override
	public int rejectcount() {

		List<GreenCardLifeCycle> l = this.glc.getRejectedGreenCard();
		return l.size();
	}

	@Override
	public int fullcount() {

		List<GreenCardLifeCycle> l = this.glc.getFullGreenCard();
		return l.size();
	}

}
