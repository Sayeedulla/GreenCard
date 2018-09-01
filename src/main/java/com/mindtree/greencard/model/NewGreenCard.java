package com.mindtree.greencard.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "newgreencard")
public class NewGreenCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int greenCardId;
	String whatHappened;
	String landmark;
	LocalDateTime submittedDate;
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	public int getGreenCardId() {
		return greenCardId;
	}
	public void setGreenCardId(int greenCardId) {
		this.greenCardId = greenCardId;
	}
	public String getWhatHappened() {
		return whatHappened;
	}
	public void setWhatHappened(String whatHappened) {
		this.whatHappened = whatHappened;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}