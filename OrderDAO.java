package com.example.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.store.model.Contact;
import com.example.store.model.Order;
import com.example.store.model.OrderItem;

public class OrderDAO {
	static final String GET_ORDERS = "SELECT * FROM orders, contacts WHERE orders.contact = contacts.id";
	static final String GET_ORDER_BY_ID = "SELECT * FROM orders, contacts WHERE orders.contact = contacts.id AND orders.id=?";
	static final String SAVE_ORDER = "INSERT INTO `qa_store`.`orders` (`contact`, `status`, `address`, `due_date`) "
			+ "VALUES (?, ?, ?, ?);";
	static final String UPDATE_ORDER = "UPDATE `qa_store`.`orders` "
			+ "SET `contact` = ?, `status` = ?, `address` = ?, `due_date` = ? "
			+ "WHERE (`id` = ?);";
	static final String DELETE_ORDER = "DELETE FROM `qa_store`.`orders` WHERE (`id` = ?);";
	
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
				order.setDue_date(rs.getDate(2));
				order.setStatus(rs.getString(3));
				order.setAddress(rs.getString(4));
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
				orderItems.add(orderItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderItems;
	}
	
	public static void saveOrder(Order order) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(SAVE_ORDER)) {
			ps.setInt(1, order.getContact().getId());
			ps.setString(3, order.getStatus());
			ps.setString(4, order.getAddress());
			ps.setDate(5, order.getDue_date());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateLead(Order order) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER)) {
			ps.setInt(1, order.getContact().getId());
			ps.setString(2, order.getStatus());
			ps.setString(3, order.getAddress());
			ps.setDate(4, order.getDue_date());
			ps.setInt(11, order.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteOrder(int id) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(DELETE_ORDER)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
