package com.example.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.store.model.Contact;
import com.example.store.model.User;

public class ContactDAO {
	static final String GET_CONTACTS = "SELECT * FROM contacts, users WHERE contact_owner = users.id";
	static final String GET_CONTACT_BY_ID = "SELECT * FROM contacts, users WHERE contact_owner = users.id AND contacts.id=?";
	static final String SAVE_CONTACT = "INSERT INTO `qa_store`.`contacts` (`contact_owner`, `name`, `title`, `email`, `phone`, `lead_source`) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";
	static final String UPDATE_CONTACT = "UPDATE `qa_store`.`contacts` "
			+ "SET `contact_owner` = ?, `name` = ?, `title` = ?, `email` = ?, `phone` = ?, `lead_source` = ? "
			+ "WHERE (`id` = ?);";
	static final String DELETE_CONTACT = "DELETE FROM `qa_store`.`contacts` WHERE (`id` = ?);";
	
	public static ArrayList<Contact> getContacts() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_CONTACTS)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setName(rs.getString(3));
				contact.setTitle(rs.getString(4));
				contact.setEmail(rs.getString(5));
				contact.setPhone(rs.getString(6));
				contact.setLead_source(rs.getString(7));
				contact.setContact_owner(new User(rs.getInt(8), rs.getString(9), rs.getString(10)));
				contacts.add(contact);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return contacts;
	}
	
	public static Contact getContact(int id) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_CONTACT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setName(rs.getString(3));
				contact.setTitle(rs.getString(4));
				contact.setEmail(rs.getString(5));
				contact.setPhone(rs.getString(6));
				contact.setLead_source(rs.getString(7));
				contact.setContact_owner(new User(rs.getInt(8), rs.getString(9), rs.getString(10)));
				return contact;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveContact(Contact contact) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(SAVE_CONTACT)) {
			ps.setInt(1, contact.getContact_owner().getId());
			ps.setString(2, contact.getName());
			ps.setString(3, contact.getTitle());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getPhone());
			ps.setString(6, contact.getLead_source());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateContact(Contact contact) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(UPDATE_CONTACT)) {
			ps.setInt(1, contact.getContact_owner().getId());
			ps.setString(2, contact.getName());
			ps.setString(3, contact.getTitle());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getPhone());
			ps.setString(6, contact.getLead_source());
			ps.setInt(7, contact.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteContact(int id) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(DELETE_CONTACT)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
