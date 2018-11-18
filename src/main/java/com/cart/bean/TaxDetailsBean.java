package com.cart.bean;

import java.util.ArrayList;

public class TaxDetailsBean {
	
	private ArrayList<TaxCategory> productCategories;

	public ArrayList<TaxCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(ArrayList<TaxCategory> productCategories) {
		this.productCategories = productCategories;
	}

}
