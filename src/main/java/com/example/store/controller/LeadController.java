package com.example.store.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.LeadDAO;
import com.example.store.model.Lead;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    @GetMapping
    public ArrayList<Lead> getAllLeads() {
        return LeadDAO.getLeads();
    }
}
