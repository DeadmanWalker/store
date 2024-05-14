package com.example.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.UserDAO;
import com.example.store.model.LoginRequest;
import com.example.store.model.User;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@PostMapping("/login")
	public ResponseEntity<User> checkLogin(@RequestBody LoginRequest loginRequest, HttpSession session) {
		User user = new User(loginRequest.getUsername(), loginRequest.getPassword());
        if (UserDAO.checkLogin(user)) {
        	session.setAttribute("USER", user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
	}
}
