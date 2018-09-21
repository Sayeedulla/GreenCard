package com.mindtree.greencard.entity;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Green_Card_History")
public class GreenCardHistory {

	@Id
	private int gId;
	
	private LocalDateTime submittedDateTime;
	private LocalDateTime closedDateTime;
	private String assignedPersonId1;
	private String correctiveAction1;
	private String rootCause1;
	private String status1;
	private String whatHappened;
	private String landmark;
	private String category;
	private String priority;

	@Lob
	byte[] image;

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public LocalDateTime getSubmittedDateTime() {
		return submittedDateTime;
	}

	public void setSubmittedDateTime(LocalDateTime submittedDateTime) {
		this.submittedDateTime = submittedDateTime;
	}

	public LocalDateTime getClosedDateTime() {
		return closedDateTime;
	}

	public void setClosedDateTime(LocalDateTime closedDateTime) {
		this.closedDateTime = closedDateTime;
	}

	public String getAssignedPersonId1() {
		return assignedPersonId1;
	}

	public void setAssignedPersonId1(String assignedPersonId1) {
		this.assignedPersonId1 = assignedPersonId1;
	}

	public String getCorrectiveAction1() {
		return correctiveAction1;
	}

	public void setCorrectiveAction1(String correctiveAction1) {
		this.correctiveAction1 = correctiveAction1;
	}

	public String getRootCause1() {
		return rootCause1;
	}

	public void setRootCause1(String rootCause1) {
		this.rootCause1 = rootCause1;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	
}
