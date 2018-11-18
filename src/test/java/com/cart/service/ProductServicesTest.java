package com.cart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.cart.bean.AllProductsResponseBean;
import com.cart.bean.BillDetailsResponseBean;
import com.cart.bean.CartItem;
import com.cart.bean.CheckoutCartRequestBean;
import com.cart.bean.ProductBean;

@RunWith(SpringRunner.class)
public class ProductServicesTest {

	private ProductServices testSubject;
	
	@Test
	public void testGetAllProducts() {
		testSubject = new ProductServices();
		AllProductsResponseBean productData = testSubject.getAllProducts();
		assertNotNull(productData);
	}
	
	@Test
	public void testGetAllProductsWithEmptyData() {
		testSubject = new ProductServices();
		ArrayList<ProductBean> productList = new ArrayList<>();
		ProductServices.productData = new AllProductsResponseBean();
		ProductServices.productData.setAllProducts(productList);
		
		AllProductsResponseBean response = null;
		try {
			response = testSubject.getAllProducts();
			
		}catch(Exception e) {
			assertTrue(true);
			assertNotNull(e);
			assertEquals(e.getMessage(), "Something went wrong while fetching the data.");
		}
		assertNull(response);
	}
	
	@Test
	public void testGetBillDetails() {
		testSubject = new ProductServices();
		CheckoutCartRequestBean cartBean = new CheckoutCartRequestBean();
		ArrayList<CartItem> cartList =  new ArrayList<>();
		CartItem cartItem = new CartItem();
		cartItem.setId("1");
		cartItem.setQuantity(2);
		cartList.add(cartItem);
		cartBean.setCart(cartList);
		
		BillDetailsResponseBean response = testSubject.getBillDetails(cartBean);
		
		assertTrue(!response.getBillItems().isEmpty());
		assertEquals(response.getTotalItems(),2);

		// total bill amount including sales tax
		assertEquals(Double.parseDouble("200"),response.getTotalBillAmt(),0.001);
		
		// sales tax per product
		assertEquals(Double.parseDouble("10"),response.getTotalSalesTax(),0.001);

		// price per product without sales tax
		assertEquals(Double.parseDouble("90"),response.getTotalPrice(),0.001);
	}
	
	@Test
	public void testGetBillDetailsExceptionScenarioInvalidProductIdOrProductCategory() {
		testSubject = new ProductServices();
		CheckoutCartRequestBean cartBean = new CheckoutCartRequestBean();
		ArrayList<CartItem> cartList =  new ArrayList<>();
		CartItem cartItem = new CartItem();
		cartItem.setId("10");
		cartItem.setQuantity(2);
		cartList.add(cartItem);
		cartBean.setCart(cartList);
		
		BillDetailsResponseBean response = null;
		try {
			response = testSubject.getBillDetails(cartBean);
		}catch(Exception e) {
			assertTrue(true);
			assertNotNull(e);
			assertEquals(e.getMessage(), "Invalid Products");
		}
		assertNull(response);
	}
}
