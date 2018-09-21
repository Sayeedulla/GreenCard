package com.mindtree.greencard.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.exception.AdminExceptions.AdminException;
import com.mindtree.greencard.exception.AdminExceptions.CardNotFoundException;
import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
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
	GreenCardLifeCycleRepository GLC;

	@Autowired
	UserRepository userRepository;

	@Autowired
	GreenCardHistory gcH;

	private static final String timeZone = "Asia/Calcutta";

	@Override
	public List<NewGreenCard> newComplaints() {
		List<NewGreenCard> gcl = new ArrayList<NewGreenCard>();
		List<GreenCardLifeCycle> l = this.GLC.getOpenGreenCard();
		l.forEach(e -> {
			NewGreenCard n = e.getNewgreencard();
			NewGreenCard n1 = new NewGreenCard();
			n1 = n;
			gcl.add(n1);
		});
		return gcl;
	}

	@Override
	public Optional<NewGreenCard> getCard(int gid) throws AdminException {
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
		GreenCardLifeCycle glc1 = this.GLC.getGreenCardById(ngc1);
		card.setlId(glc1.getLifeCycleId());
		this.inprogresscard.save(card);
		NewGreenCard newGreenCard = this.newgreencard.getOne(card.getGcId());
		GreenCardLifeCycle glc = this.GLC.getGreenCardById(newGreenCard);
		glc.setStatus("Assigned");
		glc.setAssignedTime(LocalDateTime.now(ZoneId.of(timeZone)));
		this.GLC.save(glc);
		return "Assigned";
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
		GreenCardLifeCycle greencardLC = GLC.getGreenCardById(ngc);
		greencardLC.setResolvedTime(LocalDateTime.now(ZoneId.of(timeZone)));
		greencardLC.setStatus("rejected");
		GLC.save(greencardLC);
		gcH.setgId(ngc.getGreenCardId());
		gcH.setAssignedPersonId("N/A");
		gcH.setCategory("N/A");
		gcH.setClosedDateTime(greencardLC.getResolvedTime());
		gcH.setCorrectiveAction("N/A");
		gcH.setImage(ngc.getImage());
		gcH.setLandmark(ngc.getLandmark());
		gcH.setRootCause("N/A");
		gcH.setStatus(greencardLC.getStatus());
		gcH.setSubmittedDateTime(greencardLC.getSubmittedTime());
		gcH.setWhatHappened(ngc.getWhatHappened());
		history.save(gcH);
		return "Rejected";
	}

	@Override
	public String resolveCard(int gid, String rootcause, String correctiveaction) {
		NewGreenCard ngc = newgreencard.getNewCard(gid);
		GreenCardLifeCycle greencardLC = GLC.getGreenCardById(ngc);
		greencardLC.setResolvedTime(LocalDateTime.now(ZoneId.of(timeZone)));
		greencardLC.setStatus("closed");
		GLC.save(greencardLC);
		gcH.setgId(ngc.getGreenCardId());
		gcH.setAssignedPersonId(userRepository.getAdmin() + " - Admin");
		gcH.setCategory("N/A");
		gcH.setClosedDateTime(greencardLC.getResolvedTime());
		gcH.setCorrectiveAction(correctiveaction);
		gcH.setImage(ngc.getImage());
		gcH.setLandmark(ngc.getLandmark());
		gcH.setRootCause(rootcause);
		gcH.setStatus(greencardLC.getStatus());
		gcH.setSubmittedDateTime(greencardLC.getSubmittedTime());
		gcH.setWhatHappened(ngc.getWhatHappened());
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

		List<GreenCardLifeCycle> l = this.GLC.getOpenGreenCard();
		return l.size();

	}

	@Override
	public int assignedcount() {
		List<GreenCardLifeCycle> l = this.GLC.getAssignedGreenCard();
		return l.size();
	}

	@Override
	public int closedcount() {

		List<GreenCardLifeCycle> l = this.GLC.getClosedGreenCard();
		return l.size();
	}

	@Override
	public int rejectcount() {

		List<GreenCardLifeCycle> l = this.GLC.getRejectedGreenCard();
		return l.size();
	}

	@Override
	public int fullcount() {

		List<GreenCardLifeCycle> l = this.GLC.getFullGreenCard();
		return l.size();
	}



}
