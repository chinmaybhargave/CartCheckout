package com.cart.bean;

import java.util.ArrayList;

public class BillDetailsResponseBean {
	
	private ArrayList<BillItem> billItems;
	
	private double totalBillAmt;
	
	private double totalSalesTax;
	
	private double totalPrice;
	
	private int totalItems;
	
	public ArrayList<BillItem> getBillItems() {
		return billItems;
	}
	public void setBillItems(ArrayList<BillItem> billItems) {
		this.billItems = billItems;
	}
	public double getTotalBillAmt() {
		return totalBillAmt;
	}
	public void setTotalBillAmt(double totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}
	public double getTotalSalesTax() {
		return totalSalesTax;
	}
	public void setTotalSalesTax(double totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
}
