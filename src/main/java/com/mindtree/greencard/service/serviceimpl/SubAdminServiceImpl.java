package com.mindtree.greencard.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.exception.subadminserviceexception.ComplaintNotFoundException;
import com.mindtree.greencard.exception.subadminserviceexception.ElementNotFoundException;
import com.mindtree.greencard.exception.subadminserviceexception.EmptyListException;
import com.mindtree.greencard.exception.subadminserviceexception.ServiceException;
import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.GreenCardLifeCycleRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.SubAdminCategoryRepository;
import com.mindtree.greencard.jprepository.superadminrepository.UserRepository;
import com.mindtree.greencard.model.Category;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.GreenCardLifeCycle;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.model.SubAdminCategory;
import com.mindtree.greencard.model.User;
import com.mindtree.greencard.service.SubAdminService;

@Service
public class SubAdminServiceImpl implements SubAdminService {

	private String listIsEmpty = "List is empty";
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

	@Autowired
	CategoryRepository cat;

	@Autowired
	SubAdminCategoryRepository subRepo;

	@Override
	public List<InProgressGreenCard> getComplaints(String mid) throws ServiceException {
		List<InProgressGreenCard> complList = new ArrayList<InProgressGreenCard>();
		try {
			complList = inProgGCRepo.getComplaints(mid);
			if (complList.isEmpty())
				throw new EmptyListException();
		} catch (EmptyListException e) {
			throw new ServiceException(listIsEmpty);
		}
		return complList;
	}

	@Override
	public NewGreenCard getData(int gcid) throws ServiceException {
		try {
			if (newGCRepo.existsById(gcid)) {
				return newGCRepo.getOne(gcid);
			} else {
				throw new ElementNotFoundException();
			}
		} catch (ElementNotFoundException e) {
			throw new ServiceException("Particular Complaint not exist");
		}
	}

	@Override
	public String updateComplaint(InProgressGreenCard sub) throws ServiceException {
		try {
			if (inProgGCRepo.existsById(sub.getGcId())) {

				inProgGCRepo.save(sub);
				int id = sub.getGcId();
				GreenCardLifeCycle greencardLC = greencardLCRepo.getOne(sub.getlId());
				NewGreenCard ngc = newGCRepo.getOne(sub.getGcId());
				greencardLC.setStatus("Closed");
				greencardLC.setResolvedTime(LocalDateTime.now(ZoneId.of("Asia/Calcutta")));
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

				gcH.setPriority(sub.getPriority());

				gcH.setSubmittedDateTime(greencardLC.getSubmittedTime());
				gcH.setWhatHappened(ngc.getWhatHappened());
				gcHR.save(gcH);

				inProgGCRepo.delete(sub);

				return "Complaint " + id + " is resolved";
			} else {
				throw new ComplaintNotFoundException();
			}
		} catch (ComplaintNotFoundException e) {
			throw new ServiceException("Requested Complaint not exist");
		}
	}

	@Override
	public String reassignComplaint(InProgressGreenCard sub) throws ServiceException {
		try {
			if (inProgGCRepo.existsById(sub.getGcId())) {
				inProgGCRepo.save(sub);
				return "Complaint " + sub.getGcId() + " is reassigned to " + sub.getAssignedPersonId() + " of "
						+ sub.getCategory();
			} else {
				throw new ComplaintNotFoundException();
			}
		} catch (ComplaintNotFoundException e) {
			throw new ServiceException("Requested Complaint not exist");
		}
	}

	public List<Category> getCategory() throws ServiceException {
		List<Category> cate = new ArrayList<Category>();

		try {
			cate = cat.findAll();
			if (cate.isEmpty())
				throw new EmptyListException();
		} catch (EmptyListException e) {
			throw new ServiceException(listIsEmpty);
		}
		return cate;
	}

	public List<SubAdminCategory> getSubadmins(String category) throws ServiceException {
		List<SubAdminCategory> subad = new ArrayList<SubAdminCategory>();
		try {
			subad = subRepo.getSubadmins(category);
			if (subad.isEmpty())
				throw new EmptyListException();
		} catch (EmptyListException e) {
			throw new ServiceException(listIsEmpty);
		}
		return subad;
	}

	@Override
	public String sendHelpEmail(String mid, int gcId, String desc) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "true");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("stng361@gmail.com", "STng18N-r");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("stng361@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Kusal.Bandaru@mindtree.com"));
			message.setSubject(mid + " Required Help for GreenCard Id " + gcId);
			message.setText(desc);

			Transport.send(message);

			return "Mail Successfully Sent to Admin";

		} catch (MessagingException e) {
			throw new RuntimeException(e);

		}

	}
}