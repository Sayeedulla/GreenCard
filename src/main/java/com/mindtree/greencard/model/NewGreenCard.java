package com.mindtree.greencard.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "newgreencard")
public class NewGreenCard {
	@Id
	@GeneratedValue(generator="newGenerator")
	@GenericGenerator(name="newGenerator",strategy="foreign",parameters= {@Parameter(value="user",name="property")})
	int greenCardId;
	String whatHappened;
	String landmark;
	LocalDateTime submittedDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="greenCardId")
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	
}
