package com.lee.Upload.entity;


import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="user_upload")
@JsonIgnoreProperties(value={"userPwd"})
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)	//针对不同数据库
	private int userId;
	@Column(name="user_name")
	private String userName;
	@Column(name="user_email")
	private String userEmail;
	@Column(name="user_pwd")
	private String userPwd;
	@Column(name="user_image")
	private String userImage;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public User(String userName, String userEmail, String userPwd, String userImage) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userImage = userImage;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}