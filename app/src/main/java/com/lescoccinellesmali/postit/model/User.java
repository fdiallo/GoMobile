package com.lescoccinellesmali.postit.model;

import java.util.UUID;

public class User {
	
	private UUID mId;
	private String mUsername;
	private String mPassword;
	private String mPhone;
	private String mEmail;
	
	public User(){
		//Generate unique identifier
		mId = UUID.randomUUID();
	}

	public String getUsername() {
		return mUsername;
	} //username is user email

	public void setUsername(String mUsername) {
		this.mUsername = mUsername;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public UUID getId() {
		return mId;
	}
	
	

}
