package com.example.store.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.UserDAO;
import com.example.store.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@GetMapping
    public ArrayList<User> getAllUsers() {
        return UserDAO.getUsers();
    }
}
