package com.example.shoppingweb.model;


public class Cart {
	private String productId;
	private Integer productQty;
	public Cart() {
	}
	public Cart(String productId, Integer productQty) {		
		this.productId = productId;
		this.productQty = productQty;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getProductQty() {
		return productQty;
	}
	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}
	
	
}
