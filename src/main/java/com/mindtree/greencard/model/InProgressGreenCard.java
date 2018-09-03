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
	int lId;
	int gc_id;
	String priority;
	String category;
	int assigned_person_id;
	String corrective_action;
	String root_cause;
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	public int getGc_id() {
		return gc_id;
	}
	public void setGc_id(int gc_id) {
		this.gc_id = gc_id;
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
	public int getAssigned_person_id() {
		return assigned_person_id;
	}
	public void setAssigned_person_id(int assigned_person_id) {
		this.assigned_person_id = assigned_person_id;
	}
	public String getCorrective_action() {
		return corrective_action;
	}
	public void setCorrective_action(String corrective_action) {
		this.corrective_action = corrective_action;
	}
	public String getRoot_cause() {
		return root_cause;
	}
	public void setRoot_cause(String root_cause) {
		this.root_cause = root_cause;
	}

}
