package com.project;

/**
 * 
 * 직원들이 갖는 정보를 담은 클래스.
 *
 */
public class Employee {

	private String name;
	private String division;
	private String position;
	private String joinDate; // 입사일
	private String employeeNum; // 사원번호
	private String birth;
	private String phoneNum;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", division=" + division + ", position=" + position + ", joinDate=" + joinDate
				+ ", employeeNum=" + employeeNum + ", birth=" + birth + ", phoneNum=" + phoneNum + ", address="
				+ address + "]";
	}
	
	
}//Employee
