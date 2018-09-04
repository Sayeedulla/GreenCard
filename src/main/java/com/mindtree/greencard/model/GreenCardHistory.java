package com.mindtree.greencard.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Green_Card_History")
public class GreenCardHistory {
	
	@Id
	private int gId;
	private String userId;
	private LocalDateTime submittedDateTime;
	private LocalDateTime closedDateTime;
	private int assignedPersonId;
	private String correctiveAction;
	private String rootCause;
	private String status;
	private String whatHappened;
	private String landmark;
	private String category;
	@Lob
	byte[] image;
	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getAssignedPersonId() {
		return assignedPersonId;
	}
	public void setAssignedPersonId(int assignedPersonId) {
		this.assignedPersonId = assignedPersonId;
	}
	public String getCorrectiveAction() {
		return correctiveAction;
	}
	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}
	public String getRootCause() {
		return rootCause;
	}
	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
