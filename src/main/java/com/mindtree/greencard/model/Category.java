package com.mindtree.greencard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Category_Id;
	@Column(unique=true)
	private String Category_Name;

	public int getCategory_Id() {
		return Category_Id;
	}

	public void setCategory_Id(int category_Id) {
		Category_Id = category_Id;
	}

	public String getCategory_Name() {
		return Category_Name;
	}

	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}

}
