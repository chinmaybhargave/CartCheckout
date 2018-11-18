package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cart.bean.AllProductsResponseBean;
import com.cart.bean.BillDetailsResponseBean;
import com.cart.bean.CheckoutCartRequestBean;
import com.cart.service.ProductServices;

@Controller
@RequestMapping("/products")
public class ProductControllerImpl {

	@Autowired
	public ProductServices service;
	
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public @ResponseBody AllProductsResponseBean getAllProducts(){
		return service.getAllProducts();
	}
	
	@RequestMapping(path = "/checkout", method = RequestMethod.POST)
	public @ResponseBody BillDetailsResponseBean getBillDetails(@RequestBody CheckoutCartRequestBean cartBean){
		return service.getBillDetails(cartBean);
	}
	
}
