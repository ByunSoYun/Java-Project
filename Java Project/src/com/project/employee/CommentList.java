package com.project.employee;

import java.util.ArrayList;

public class CommentList {
	
	private String seq;
	private ArrayList<String> comment = new ArrayList<String>();
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public ArrayList<String> getComment() {
		return comment;
	}
	
	public void setComment(ArrayList<String> comment) {
		this.comment = comment;
	}
	
	public void addComment(String comment) {
		this.comment.add(comment);
	}
	
	@Override
	public String toString() {
		return "CommentList [seq=" + seq + ", comment=" + comment + "]";
	}
	
	public String getCommentList() {
		
		String line = "";
		
		for (int i=0; i<comment.size(); i++) {
			
			line += comment.get(i) + "■";
			
		}
		
		return line;
		
	}
	
	
	
}
