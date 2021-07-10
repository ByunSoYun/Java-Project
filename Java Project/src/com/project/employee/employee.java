package com.project.employee;

import java.util.Random;

public class employee {	// 사원들의 모든 정보를 담는 get/set
	
	private String Name;
	private String Division;
	private String Position;
	private String JoinDate;
	private String EmployeeNum;
	private String Birth;
	private String PhoneNum;
	private String Address;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDivision() {
		return Division;
	}
	public void setDivision(String division) {
		Division = division;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getJoinDate() {
		return JoinDate;
	}
	public void setJoinDate(String joinDate) {
		JoinDate = joinDate;
	}
	public String getEmployeeNum() {
		return EmployeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		EmployeeNum = employeeNum;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	
	
}
