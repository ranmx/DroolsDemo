package com.fosun.tableObjects;

import java.io.Serializable;

public class CleanedPerson implements Serializable{
	private int age;
	private String decade;
	private String birthDay;
	private String personID;

	// in order to be reflected by spark, We have to define gets and sets explicitly.
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDecade() {
		return decade;
	}

	public void setDecade(String decade) {
		this.decade = decade;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public CleanedPerson () {
	}

	@Override
	public String toString() {
		return "CleanedPerson [age=" + age + ", decade=" + decade + ", birthDay=" + birthDay + ", personID=" + personID
				+ ", address=" + address 
				+ ", gender=" + gender
				+ "]";
	}
}
