package com.example.store.model;

public class OrderItem {
	Product product;
	int amount;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public OrderItem() {
		super();
	}
}
