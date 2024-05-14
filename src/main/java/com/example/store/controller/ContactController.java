package com.example.store.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.ContactDAO;
import com.example.store.dao.LeadDAO;
import com.example.store.model.Contact;
import com.example.store.model.Lead;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	@GetMapping
    public ArrayList<Contact> getAllContacts() {
        return ContactDAO.getContacts();
    }
	
	@DeleteMapping("{id}")
	void deleteContact(@PathVariable String id) {
		ContactDAO.deleteContact(Integer.parseInt(id));
	}
}
