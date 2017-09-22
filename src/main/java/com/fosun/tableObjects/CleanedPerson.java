package com.fosun.tableObjects;

import java.io.Serializable;

public class CleanedPerson implements Serializable{
	public int age;
	public String decade;
	public String birthDay;
	public String personID;
	
	public CleanedPerson () {
	}

	@Override
	public String toString() {
		return "CleanedPerson [age=" + age + ", decade=" + decade + ", birthDay=" + birthDay + ", personID=" + personID
				+ "]";
	}
}
