package com.mindtree.greencard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Inprogress_Greencard")
public class InProgressGreenCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lId;
	private int gcId;
	private String priority;
	private String category;
	private int assignedPersonId;
	private String correctiveAction;
	private String rootCause;
	private String status;
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	public int getGcId() {
		return gcId;
	}
	public void setGcId(int gcId) {
		this.gcId = gcId;
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
	
}
