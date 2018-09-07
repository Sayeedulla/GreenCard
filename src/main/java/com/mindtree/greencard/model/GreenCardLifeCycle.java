package com.mindtree.greencard.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GreenCardLifeCycle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int lifeCycleId;
	LocalDateTime submittedTime;
	LocalDateTime assignedTime;
	LocalDateTime resolvedTime;
	String status;
	String timePeriod;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "greenCardId")
	NewGreenCard newgreencard;
	public int getLifeCycleId() {
		return lifeCycleId;
	}
	public void setLifeCycleId(int lifeCycleId) {
		this.lifeCycleId = lifeCycleId;
	}
	public LocalDateTime getSubmittedTime() {
		return submittedTime;
	}
	public void setSubmittedTime(LocalDateTime submittedTime) {
		this.submittedTime = submittedTime;
	}
	public LocalDateTime getAssignedTime() {
		return assignedTime;
	}
	public void setAssignedTime(LocalDateTime assignedTime) {
		this.assignedTime = assignedTime;
	}
	public LocalDateTime getResolvedTime() {
		return resolvedTime;
	}
	public void setResolvedTime(LocalDateTime resolvedTime) {
		this.resolvedTime = resolvedTime;
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
	public NewGreenCard getNewgreencard() {
		return newgreencard;
	}
	public void setNewgreencard(NewGreenCard newgreencard) {
		this.newgreencard = newgreencard;
	}
}
