package com.cart.bean;

import java.util.ArrayList;

public class AllProductsResponseBean {

	private ArrayList<ProductBean> allProducts;

	public ArrayList<ProductBean> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(ArrayList<ProductBean> allProducts) {
		this.allProducts = allProducts;
	}
	
}
