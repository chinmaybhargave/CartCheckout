package com.cart.bean;

public class BillItem {
	
	private String id;
	
	private double salesTaxAmt;
	
	private double priceWithoutTax;
	
	private double total;
	
	private int quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getSalesTaxAmt() {
		return salesTaxAmt;
	}
	public void setSalesTaxAmt(double salesTaxAmt) {
		this.salesTaxAmt = salesTaxAmt;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPriceWithoutTax() {
		return priceWithoutTax;
	}
	public void setPriceWithoutTax(double priceWithoutTax) {
		this.priceWithoutTax = priceWithoutTax;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
