package com.project;

public class EmployeeCommute {
	
	private String commuteDay;
	private String name;
	private String startTime;
	private String endTime;
	private String employeeNum;
	
	
	public String getCommuteday() {
		return commuteDay;
	}
	public void setCommuteDay(String commuteday) {
		this.commuteDay = commuteday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getEmployeeNum() {
		return employeeNum;
	}
	
	public void setEmployeeNum(String employeeNum) {
		// TODO Auto-generated method stub
		this.employeeNum = employeeNum;
	}
	@Override
	public String toString() {
		return "EmployeeCommute [commuteDay=" + commuteDay + ", name=" + name + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", employeeNum=" + employeeNum + "]";
	}
	
	
	
}
