package com.example.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.store.model.User;

public class UserDAO {
	static final String CHECK_USER = "SELECT * FROM users WHERE username = ? AND password = ?;";
	static final String GET_USERS = "SELECT * FROM users;";
	
	public static boolean checkLogin(User user) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(CHECK_USER)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_USERS)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}
}
