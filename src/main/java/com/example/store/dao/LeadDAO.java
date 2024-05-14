package com.example.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.store.model.Lead;
import com.example.store.model.User;

public class LeadDAO {
	static final String GET_LEADS = "SELECT * FROM leads, users WHERE lead_owner = users.id";
	static final String GET_LEAD_BY_ID = "SELECT * FROM leads, users WHERE lead_owner = users.id AND leads.id=?";
	static final String SAVE_LEAD = "INSERT INTO `qa_store`.`leads` (`lead_owner`, `name`, `company`, `title`, `email`, `phone`, `website`, `lead_source`, `lead_status`, `rating`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	static final String UPDATE_LEAD = "UPDATE `qa_store`.`leads` "
			+ "SET `lead_owner` = ?, `name` = ?, `company` = ?, `title` = ?, `email` = ?, `phone` = ?, `website` = ?, `lead_source` = ?, `lead_status` = ?, `rating` = ? "
			+ "WHERE (`id` = ?);";
	
	public static ArrayList<Lead> getLeads() {
		ArrayList<Lead> leads = new ArrayList<Lead>();
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_LEADS)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Lead lead = new Lead();
				lead.setId(rs.getInt(1));
				lead.setName(rs.getString(3));
				lead.setCompany(rs.getString(4));
				lead.setTitle(rs.getString(5));
				lead.setEmail(rs.getString(6));
				lead.setPhone(rs.getString(7));
				lead.setWebsite(rs.getString(8));
				lead.setLead_source(rs.getString(9));
				lead.setLead_status(rs.getString(10));
				lead.setRating(rs.getString(11));
				lead.setLead_owner(new User(rs.getInt(12), rs.getString(15), rs.getString(16)));
				leads.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return leads;
	}
	
	public static Lead getLead(int id) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(GET_LEAD_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Lead lead = new Lead();
				lead.setId(rs.getInt(1));
				lead.setName(rs.getString(3));
				lead.setCompany(rs.getString(4));
				lead.setTitle(rs.getString(5));
				lead.setEmail(rs.getString(6));
				lead.setPhone(rs.getString(7));
				lead.setWebsite(rs.getString(8));
				lead.setLead_source(rs.getString(9));
				lead.setLead_status(rs.getString(10));
				lead.setRating(rs.getString(11));
				lead.setLead_owner(new User(rs.getInt(12), rs.getString(15), rs.getString(16)));
				return lead;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveLead(Lead lead) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(SAVE_LEAD)) {
			ps.setInt(1, lead.getLead_owner().getId());
			ps.setString(2, lead.getName());
			ps.setString(3, lead.getCompany());
			ps.setString(4, lead.getName());
			ps.setString(5, lead.getTitle());
			ps.setString(6, lead.getEmail());
			ps.setString(7, lead.getPhone());
			ps.setString(8, lead.getWebsite());
			ps.setString(9, lead.getLead_source());
			ps.setString(10, lead.getLead_status());
			ps.setString(11, lead.getRating());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateLead(Lead lead) {
		try (Connection connection = DAO.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(SAVE_LEAD)) {
			ps.setInt(1, lead.getLead_owner().getId());
			ps.setString(2, lead.getName());
			ps.setString(3, lead.getCompany());
			ps.setString(4, lead.getName());
			ps.setString(5, lead.getTitle());
			ps.setString(6, lead.getEmail());
			ps.setString(7, lead.getPhone());
			ps.setString(8, lead.getWebsite());
			ps.setString(9, lead.getLead_source());
			ps.setString(10, lead.getLead_status());
			ps.setString(11, lead.getRating());
			ps.setInt(12, lead.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}