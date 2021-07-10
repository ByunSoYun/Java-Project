package com.project.employee;

public class getpoint {		//물품을 구매하기 위한 get/set

	private String seq;
	private String name;
	private String product;
	private String cost;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		
		return " [seq=" + seq + ", name=" + name + ", product=" + product + ""
				+ ", cost=" +cost + "]";
	}//toString
	
	
}//fin(getpoint)
