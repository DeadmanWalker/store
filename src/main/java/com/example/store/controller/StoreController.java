package com.example.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.store.dao.ContactDAO;
import com.example.store.dao.LeadDAO;
import com.example.store.model.Contact;
import com.example.store.model.Lead;

import jakarta.servlet.http.HttpSession;

@Controller
public class StoreController {
	@GetMapping("/customer")
	String getCustomerView(HttpSession session) {
		if (session.getAttribute("USER") == null)
			return "redirect:/login";
		return "customer.html";
	}
	
	@GetMapping("/login")
	String getLoginView() {
		return "login.html";
	}
	
	@GetMapping("/")
	String getHomePage(HttpSession session) {
		if (session.getAttribute("USER") == null)
			return "redirect:/login";
		return "home.html";
	}
	
	@GetMapping("/lead/create/{id}")
	String getCreateLeadView(HttpSession session, Model model, @PathVariable String id) {
		if (session.getAttribute("USER") == null)
			return "redirect:/login";
		model.addAttribute("id", Integer.parseInt(id));
		Lead lead = new Lead();
		if (!id.equals("0"))
			lead = LeadDAO.getLead(Integer.parseInt(id));
		model.addAttribute("lead", lead);
		return "lead.html";
	}
	
	@GetMapping("/lead/edit/{id}")
	String getEditLeadView(HttpSession session, Model model, @PathVariable String id) {
		if (session.getAttribute("USER") == null)
			return "redirect:/login";
		model.addAttribute("id", Integer.parseInt(id));
		Lead lead = new Lead();
		if (!id.equals("0"))
			lead = LeadDAO.getLead(Integer.parseInt(id));
		model.addAttribute("lead", lead);
		return "lead.html";
	}
	
	@PostMapping("lead/save/{id}")
	String createLead(Lead lead, @PathVariable String id) {
		LeadDAO.saveLead(lead);
		return "redirect:/customer";
	}
	
	@PutMapping("lead/save/{id}")
	String updateLead(Lead lead, @PathVariable String id) {
		lead.setId(Integer.parseInt(id));
		LeadDAO.updateLead(lead);
		return "redirect:/customer";
	}
	
	@DeleteMapping("lead/delete/{id}")
	String deleteLead(@PathVariable String id) {
		LeadDAO.deleteLead(Integer.parseInt(id));
		return "redirect:/customer";
	}
	
	@GetMapping("/contact/create/{id}")
	String getCreateContactView(HttpSession session, Model model, @PathVariable String id) {
		if (session.getAttribute("USER") == null)
			return "redirect:/login";
		model.addAttribute("id", Integer.parseInt(id));
		Contact contact = new Contact();
		if (!id.equals("0"))
			contact = ContactDAO.getContact(Integer.parseInt(id));
		model.addAttribute("contact", contact);
		return "contact.html";
	}
	
	@GetMapping("/contact/edit/{id}")
	String getEditContactView(HttpSession session, Model model, @PathVariable String id) {
		if (session.getAttribute("USER") == null)
			return "redirect:/login";
		model.addAttribute("id", Integer.parseInt(id));
		Contact contact = new Contact();
		if (!id.equals("0"))
			contact = ContactDAO.getContact(Integer.parseInt(id));
		model.addAttribute("contact", contact);
		return "contact.html";
	}
	
	@PostMapping("contact/save/{id}")
	String createContact(Contact contact, @PathVariable String id) {
		ContactDAO.saveContact(contact);
		return "redirect:/customer";
	}
	
	@PutMapping("contact/save/{id}")
	String updateContact(Contact contact, @PathVariable String id) {
		contact.setId(Integer.parseInt(id));
		ContactDAO.updateContact(contact);
		return "redirect:/customer";
	}
	
	@DeleteMapping("contact/delete/{id}")
	String deleteContact(@PathVariable String id) {
		ContactDAO.deleteContact(Integer.parseInt(id));
		return "redirect:/customer";
	}
}
