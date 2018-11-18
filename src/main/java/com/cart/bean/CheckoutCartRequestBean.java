package com.cart.bean;

import java.util.ArrayList;

public class CheckoutCartRequestBean {
	
	private ArrayList<CartItem> cart;

	public ArrayList<CartItem> getCart() {
		return cart;
	}

	public void setCart(ArrayList<CartItem> cart) {
		this.cart = cart;
	}
	
}
