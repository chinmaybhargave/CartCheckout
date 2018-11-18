package com.cart.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.cart.bean.AllProductsResponseBean;
import com.cart.bean.BillDetailsResponseBean;
import com.cart.bean.CartItem;
import com.cart.bean.CheckoutCartRequestBean;
import com.cart.bean.ProductBean;
import com.cart.service.ProductServices;

@RunWith(SpringRunner.class)
public class ProductControllerImplTests {

	@Mock
	public ProductServices service;
	
	@InjectMocks
	public ProductControllerImpl testSubject;
	
	
	@Test
	public void testGetAllProducts() {

		AllProductsResponseBean stubbedResponse = new AllProductsResponseBean();
		ArrayList<ProductBean> productList = new ArrayList<>();
		ProductBean product = new ProductBean();
		productList.add(product);
		stubbedResponse.setAllProducts(productList);
		
		Mockito.when(service.getAllProducts()).thenReturn(stubbedResponse);
	
		AllProductsResponseBean actualResponse = testSubject.getAllProducts();
		
		assertNotNull(actualResponse);
		assertEquals(stubbedResponse, actualResponse);
		
	}
	
	@Test
	public void testGetBillDetails() {
		CheckoutCartRequestBean stubbedCartBean = new CheckoutCartRequestBean();
		ArrayList<CartItem> cartItemList = new ArrayList<>();
		CartItem item = new CartItem();
		cartItemList.add(item);
		stubbedCartBean.setCart(cartItemList);
		
		BillDetailsResponseBean stubbedResponse =  new BillDetailsResponseBean();
		Mockito.when(service.getBillDetails(stubbedCartBean)).thenReturn(stubbedResponse);
		
		BillDetailsResponseBean actualResponse = testSubject.getBillDetails(stubbedCartBean);
		
		assertNotNull(actualResponse);
		assertEquals(stubbedResponse, actualResponse);
	}
	
}
