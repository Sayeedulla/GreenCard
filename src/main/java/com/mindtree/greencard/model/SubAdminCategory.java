package com.mindtree.greencard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubAdminCategory {

	@Id
	private String mid;
	private String categoryName;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
