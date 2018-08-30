package com.mindtree.greencard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubAdminCategory {

	@Id
	private String mid;
	private String Category_Name;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getCategory_Name() {
		return Category_Name;
	}

	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}
}
