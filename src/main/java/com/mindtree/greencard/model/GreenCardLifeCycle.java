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
@Table(name = "greencardlifecycle")
public class GreenCardLifeCycle {
	@Id@GeneratedValue(generator="newGenerator")
	@GenericGenerator(name="newGenerator",strategy="foreign",parameters= {@Parameter(value="newgreencard",name="property")})
	int greenCardId;
	LocalDateTime submittedTime;
	String status;
	String timePeriod;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="greenCardId")
	NewGreenCard newgreencard;
	public int getgreenCardId() {
		return greenCardId;
	}
	public void setgreenCardId(int greenCardId) {
		this.greenCardId = greenCardId;
	}
	public LocalDateTime getSubmittedTime() {
		return submittedTime;
	}
	public void setSubmittedTime(LocalDateTime submittedTime) {
		this.submittedTime = submittedTime;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public NewGreenCard getNewGreenCard() {
		return newgreencard;
	}
	public void setNewGreenCard(NewGreenCard newGreenCard) {
		this.newgreencard = newGreenCard;
	}
	
}
