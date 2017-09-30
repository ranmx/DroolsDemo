package com.fosun.tableObjects;

import java.io.Serializable;

public class CleanedPerson implements Serializable{
	public int age;
	public String decade;
	public String birthDay;
	public String personID;
	public String address;
	public String gender;
	
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
