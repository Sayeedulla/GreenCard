package com.mindtree.greencard.model;

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
@Table(name = "inprogressgreencard")
public class InProgressGreenCard {
	@Id
	@GeneratedValue(generator="newGenerator")
	@GenericGenerator(name="newGenerator",strategy="foreign",parameters= {@Parameter(value="user",name="property")})
	int greenCardId;
	String priority;
	String category;
	String assignedPersonId;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="greenCardId")
	private User user;
	
	public int getGreenCardId() {
		return greenCardId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setGreenCardId(int greenCardId) {
		this.greenCardId = greenCardId;
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

}
