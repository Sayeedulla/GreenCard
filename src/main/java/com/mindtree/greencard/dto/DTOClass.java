package com.mindtree.greencard.dto;

import java.time.LocalDate;

public class DTOClass {
	int greenCardId;
	String mid;
	String password;
	String name;
	String type;
	String emailId;
	String phoneNo;
	String whatHappened;
	String landmark;
	LocalDate submittedDate;
	String priority;
	String category;
	String assignedPersonId;
	LocalDate submittedTime;
	String status;
	String timePeriod;
	public int getGreenCardId() {
		return greenCardId;
	}
	public void setGreenCardId(int greenCardId) {
		this.greenCardId = greenCardId;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	public LocalDate getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAssignedPersonId() {
		return assignedPersonId;
	}
	public void setAssignedPersonId(String assignedPersonId) {
		this.assignedPersonId = assignedPersonId;
	}
	public LocalDate getSubmittedTime() {
		return submittedTime;
	}
	public void setSubmittedTime(LocalDate submittedTime) {
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


}