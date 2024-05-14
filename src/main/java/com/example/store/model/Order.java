package com.example.store.model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	int id;
	Contact contact;
	Date due_date;
	String address;
	ArrayList<OrderItem> order_items;
	
	public Order() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<OrderItem> getOrder_items() {
		return order_items;
	}
	public void setOrder_items(ArrayList<OrderItem> order_items) {
		this.order_items = order_items;
	}
}
