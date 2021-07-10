package com.project.employee;

public class AnonymousList {
	
	private String seq;
	private String title;
	private String regdate;
	private String content;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public   String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "AnonymousList [seq=" + seq + ", title=" + title + ", regdate=" + regdate + ", content=" + content + "]";
	}
	

}
