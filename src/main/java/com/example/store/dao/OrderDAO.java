package com.example.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.store.model.Contact;
import com.example.store.model.Lead;
import com.example.store.model.Order;
import com.example.store.model.OrderItem;
import com.example.store.model.User;

public class OrderDAO {
	static final String GET_ORDERS = "SELECT * FROM orders, contacts WHERE orders.contact = contacts.id";
	static final String GET_ORDER_BY_ID = "SELECT * FROM orders, contacts WHERE orders.contact = contacts.id AND orders.id=?";
	static final String SAVE_LEAD = "INSERT INTO `qa_store`.`leads` (`lead_owner`, `name`, `company`, `title`, `email`, `phone`, `website`, `lead_source`, `lead_status`, `rating`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	static final String UPDATE_LEAD = "UPDATE `qa_store`.`leads` "
			+ "SET `lead_owner` = ?, `name` = ?, `company` = ?, `title` = ?, `email` = ?, `phone` = ?, `website` = ?, `lead_source` = ?, `lead_status` = ?, `rating` = ? "
			+ "WHERE (`id` = ?);";
	static final String DELETE_LEAD = "DELETE FROM `qa_store`.`leads` WHERE (`id` = ?);";
	
	public static ArrayList<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_ORDERS)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDue_date(rs.getDate(3));
				order.setStatus(rs.getString(4));
				order.setAddress(rs.getString(5));
				Contact contact = new Contact();
				contact.setId(rs.getInt(6));
				contact.setName(rs.getString(8));
				order.setContact(contact);
				orders.add(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orders;
	}
	
	public static Order getOrder(int id) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDue_date(rs.getDate(3));
				order.setStatus(rs.getString(4));
				order.setAddress(rs.getString(5));
				Contact contact = new Contact();
				contact.setId(rs.getInt(6));
				contact.setName(rs.getString(8));
				order.setContact(contact);
				
				return order;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<OrderItem> getOrderItems(int id) {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setAmount(rs.getInt(3));
				order.setDue_date(rs.getDate(3));
				order.setStatus(rs.getString(4));
				order.setAddress(rs.getString(5));
				Contact contact = new Contact();
				contact.setId(rs.getInt(6));
				contact.setName(rs.getString(8));
				order.setContact(contact);
				
				return order;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveOrder(Order order) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(SAVE_LEAD)) {
			ps.setInt(1, lead.getLead_owner().getId());
			ps.setString(2, lead.getName());
			ps.setString(3, lead.getCompany());
			ps.setString(4, lead.getTitle());
			ps.setString(5, lead.getEmail());
			ps.setString(6, lead.getPhone());
			ps.setString(7, lead.getWebsite());
			ps.setString(8, lead.getLead_source());
			ps.setString(9, lead.getLead_status());
			ps.setString(10, lead.getRating());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateLead(Lead lead) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(UPDATE_LEAD)) {
			ps.setInt(1, lead.getLead_owner().getId());
			ps.setString(2, lead.getName());
			ps.setString(3, lead.getCompany());
			ps.setString(4, lead.getTitle());
			ps.setString(5, lead.getEmail());
			ps.setString(6, lead.getPhone());
			ps.setString(7, lead.getWebsite());
			ps.setString(8, lead.getLead_source());
			ps.setString(9, lead.getLead_status());
			ps.setString(10, lead.getRating());
			ps.setInt(11, lead.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteLead(int id) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(DELETE_LEAD)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
