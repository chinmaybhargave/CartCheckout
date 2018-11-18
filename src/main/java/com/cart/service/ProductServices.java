package com.cart.service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.cart.bean.AllProductsResponseBean;
import com.cart.bean.BillDetailsResponseBean;
import com.cart.bean.BillItem;
import com.cart.bean.CartItem;
import com.cart.bean.CheckoutCartRequestBean;
import com.cart.bean.ProductBean;
import com.cart.bean.TaxCategory;
import com.cart.bean.TaxDetailsBean;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServices {

	public static AllProductsResponseBean productData;
	
	public static TaxDetailsBean taxDataByType;
	
	public ProductServices() {
		
		try {
			File productsData = new ClassPathResource("data.json").getFile();
			String content = new String(Files.readAllBytes(productsData.toPath()));
			productData = new ObjectMapper().readValue(content, AllProductsResponseBean.class);
			
			File taxData = new ClassPathResource("tax.json").getFile();
			String taxContent = new String(Files.readAllBytes(taxData.toPath()));
			taxDataByType = new ObjectMapper().readValue(taxContent, TaxDetailsBean.class);

			
		} catch (Exception e) {
			e.printStackTrace();
			productData = new AllProductsResponseBean();
		}
	}
	
	public AllProductsResponseBean getAllProducts() {
		if(productData.getAllProducts().isEmpty()) {
			throw new RuntimeException("Something went wrong while fetching the data.");
		}else {
			return productData;
		}
	}
	
	public BillDetailsResponseBean getBillDetails(CheckoutCartRequestBean cartBean) {
		BillDetailsResponseBean resposne =  new BillDetailsResponseBean();
		ArrayList<BillItem> billItems = new ArrayList<>();
		
		ArrayList<CartItem> cartItems = cartBean.getCart();
		
		cartItems.forEach(item -> {
			ProductBean productBean = this.getProduct(item.getId());
			TaxCategory taxCategory = this.getSalesTax(productBean.getType());
			
			if(productBean != null && taxCategory != null) {
				
				Double totalPrice = item.getQuantity() * productBean.getPrice();
				Double salestax = (taxCategory.getTax()/100) * productBean.getPrice();
				Double actualPrice = productBean.getPrice() - salestax;
				
				BillItem billItem = new BillItem();
				billItem.setId(item.getId());
				billItem.setPriceWithoutTax(actualPrice);
				billItem.setSalesTaxAmt(salestax);
				billItem.setTotal(totalPrice);
				billItem.setQuantity(item.getQuantity());
				
				billItems.add(billItem);
				
			}else {
				throw new RuntimeException("Invalid Products");
			}
			
		});
		
		resposne.setTotalBillAmt(billItems.stream().mapToDouble(item -> item.getTotal()).sum());
		resposne.setTotalSalesTax(billItems.stream().mapToDouble(item -> item.getSalesTaxAmt()).sum());
		resposne.setTotalPrice(billItems.stream().mapToDouble(item -> item.getPriceWithoutTax()).sum());
		resposne.setTotalItems(billItems.stream().mapToInt(item -> item.getQuantity()).sum());;
		resposne.setBillItems(billItems);
		
		return resposne;
		
	}
	
	public ProductBean getProduct(String productId) {
		 ProductBean productBean = productData.getAllProducts()
				   .stream()
				   .filter( product -> productId.equals(product.getId()))
				   .findAny()
				   .orElse(null);
		 
		return productBean;
	}
	
	public TaxCategory getSalesTax(String productCategory) {
		TaxCategory taxCategory = taxDataByType.getProductCategories()
				.stream()
				.filter(category -> productCategory.equals(category.getType()))
				.findAny()
				.orElse(null);
		return taxCategory;
	}
	
}
