package com.example.store.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.ContactDAO;
import com.example.store.dao.OrderDAO;
import com.example.store.model.Contact;
import com.example.store.model.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@GetMapping
    public ArrayList<Order> getAllOrders() {
        return OrderDAO.getOrders();
    }
}
