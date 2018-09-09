	package com.mindtree.greencard.model;
	
	import java.math.BigInteger;
	import java.util.HashSet;
	import java.util.Set;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.OneToMany;
	import javax.persistence.Table;
	
	@Entity
	@Table(name = "user")
	public class User {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		Integer userId;
		String mid;
		String password;
		String name;
		String type;
		String emailId;
		BigInteger phoneNo;
		
		@OneToMany
		private Set<NewGreenCard> newGreenCards=new HashSet<>();
		
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getMid() {
			return mid;
		}
		public void setMid(String mid) {
			this.mid = mid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public BigInteger getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(BigInteger phoneNo) {
			this.phoneNo = phoneNo;
		}
		public Set<NewGreenCard> getNewGreenCards() {
			return newGreenCards;
		}
		public void setNewGreenCards(Set<NewGreenCard> newGreenCards) {
			this.newGreenCards = newGreenCards;
		}
		
	}