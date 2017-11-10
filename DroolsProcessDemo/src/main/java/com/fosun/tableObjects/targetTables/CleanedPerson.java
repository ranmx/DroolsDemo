package com.fosun.tableObjects.targetTables;

import com.fosun.tableObjects.TargetTable;
import java.io.Serializable;

public class CleanedPerson extends TargetTable {
	private String fosunuuid;
	private int age;
	private String decade;
	private String birthDay;
	private String personID;
	private String gender;
	private String hobbies;
	private String real_name;
	private String home_address;
	private String occupation;
	private String mobile;
	private String national;
	private String province;
	private String city;
	
	
	// in order to be reflected by spark, We have to define gets and sets explicitly.
	
	public String getFosunuuid() {
		return fosunuuid;
	}
	public void setFosunuuid(String fosunuuid) {
		this.fosunuuid = fosunuuid;
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	@Override
	public String toString() {
		return "CleanedPerson [age=" + age + ", decade=" + decade + ", birthDay=" + birthDay + ", personID=" + personID
				+ ", gender=" + gender + ", hobbies=" + hobbies + ", real_name=" + real_name + ", home_address="
				+ home_address + ", occupation=" + occupation + ", mobile=" + mobile + ", national=" + national
				+ ", province=" + province + ", city=" + city + "]";
	}	
	
}
