package com.mindtree.greencard.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SuperAdminHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hid;
	
	@Column
	private String mid;
	@Column
	private String whatischanged;
	@Column
	private String type;
	
	private LocalDateTime timelog;
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public LocalDateTime getTimelog() {
		return timelog;
	}
	public void setTimelog(LocalDateTime timelog) {
		this.timelog = timelog;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getWhatischanged() {
		return whatischanged;
	}
	public void setWhatischanged(String whatischanged) {
		this.whatischanged = whatischanged;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
