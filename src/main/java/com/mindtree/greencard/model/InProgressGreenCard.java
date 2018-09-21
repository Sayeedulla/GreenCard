package com.mindtree.greencard.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Inprogress_Greencard")
public class InProgressGreenCard {

	@Id
	private int gcId;
	private int lId;
	private String priority;
	private String category;
	private String assignedPersonId;
	private String correctiveAction;
	private String rootCause;
	private String status;
	public int getGcId() {
		return gcId;
	}
	public void setGcId(int gcId) {
		this.gcId = gcId;
	}
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
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
