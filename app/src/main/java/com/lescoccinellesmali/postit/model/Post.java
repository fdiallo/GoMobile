package com.lescoccinellesmali.postit.model;

import java.io.Serializable;

public class Post implements Serializable {
	private int mId;
	private String mTitle;
	private String mAuthor;
	private String mDate;
	private String mLocation;
	private String mDescription;
	private String mYear;
	private String mMonth;
	private String mDay;
	private String mHour;
	private String mMin;
	private String mAmPm;
	private String mType;
	public Post(){
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getAuthor() {
		return mAuthor;
	}
	public void setAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
	}
	public String getLocation() {
		return mLocation;
	}
	public void setLocation(String mLocation) {
		this.mLocation = mLocation;
	}
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public String getDate() {
		return mDate;
	}
	public void setDate(String mDate) {
		this.mDate = mDate;
	}
	public String getDescription() {
		return mDescription;
	}
	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	public String getYear() {
		return mYear;
	}
	public void setYear(String mYear) {
		this.mYear = mYear;
	}
	public String getMonth() {
		return mMonth;
	}
	public void setMonth(String mMonth) {
		this.mMonth = mMonth;
	}
	public String getDay() {
		return mDay;
	}
	public void setDay(String mDay) {
		this.mDay = mDay;
	}
	public String getHour() {
		return mHour;
	}
	public void setHour(String mHour) {
		this.mHour = mHour;
	}
	public String getMin() {
		return mMin;
	}
	public void setMin(String mMin) {
		this.mMin = mMin;
	}
	public String getAmPm() {
		return mAmPm;
	}
	public void setAmPm(String mAmPm) {
		this.mAmPm = mAmPm;
	}
	public String getType() {
		return mType;
	}
	public void setType(String mType) {
		this.mType = mType;
	}
	public String toString(){
		return mTitle + " " + mAuthor + " " + mDate + " " 
				+ mLocation + " " + mDescription + " " + mYear + " " + mMonth + " " + mDay + " " + mHour + " " + mMin + " "  + mAmPm + " " + mType;
	}
	//Method needed to check if two posts are equals used in PostListFragment class
	public boolean equals (Object o) {
		Post x = (Post) o;
		if (x.mId == mId) return true;
		return false;
	}
}