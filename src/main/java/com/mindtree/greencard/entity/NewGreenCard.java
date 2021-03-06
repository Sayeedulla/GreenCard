package com.mindtree.greencard.entity;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "newgreencard")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NewGreenCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer greenCardId;
	String whatHappened;
	String landmark;
	LocalDateTime submittedDate;
	

	@Lob
	byte[] image;
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getGreenCardId() {
		return greenCardId;
	}

	public void setGreenCardId(Integer greenCardId) {
		this.greenCardId = greenCardId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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