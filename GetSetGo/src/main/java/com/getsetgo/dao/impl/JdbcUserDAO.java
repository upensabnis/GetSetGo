package main.java.com.getsetgo.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import main.java.com.getsetgo.dao.UserDAO;
import main.java.com.getsetgo.model.Tournament;
import main.java.com.getsetgo.model.User;

public class JdbcUserDAO implements UserDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(User user){
		String sql = "INSERT INTO user " +
				"(Name, Email, Password, Contact, Gender, Status, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		Connection conn = null;
		try {
			System.out.println("b4 conn");
			//conn = dataSource.getConnection();
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/getsetgo","root","p@ssw0rd");
			System.out.println("after conn");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPwd());
			ps.setInt(4, (int) user.getContact());
			ps.setString(5, (user.getGender()+""));
			ps.setString(6, user.getStatus());
			ps.setString(7, user.getRole());
			System.out.println("b4 update");
			ps.executeUpdate();
			System.out.println("after update");
			ps.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public String checkUser(String Email, String pwd) {
		String sql = "SELECT * FROM user WHERE Email = ? ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return "";
		}
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getString("Password"));
				if(rs.getString("Password").equals(pwd)){
					return rs.getString("ID")+" "+rs.getString("Role");
				} else {
					return "";
				}
			}
		}catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return "";
		
	}
	
	public List<Tournament> getAllTournaments(){
		List<Tournament> tournamentObj = new ArrayList<>();
		String sql = "SELECT * FROM tournament";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return tournamentObj;
		}

		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tournament currTournament = new Tournament(rs.getString("tournament_name"), rs.getString("tournament_desc"), rs.getDate("tournament_start_date"), rs.getTime("tournament_start_time"), rs.getDate("tournament_end_date"), rs.getTime("tournament_end_time"), rs.getString("tournament_address"), rs.getString("tournament_city"), rs.getString("tournament_state"), rs.getString("tournament_country"), rs.getInt("tournament_zip"));
				currTournament.setTournamentId(rs.getInt("tournament_id"));
				tournamentObj.add(currTournament);
			}
			return tournamentObj;
		}catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public List<Tournament> getAllRegTournaments(int pid){
		List<Tournament> tournamentObj = new ArrayList<>();
		String sql = "SELECT * FROM reg_tournaments where participant_id = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return tournamentObj;
		}

		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tournament regTournament = getTournament(rs.getInt("tournament_id"));
				tournamentObj.add(regTournament);
			}
			return tournamentObj;
		}catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	private Tournament getTournament(int tid) {
		Tournament tournamentObj = null;
		String sql = "SELECT * FROM tournament where tournament_id = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				tournamentObj = new Tournament(rs.getString("tournament_name"), rs.getString("tournament_desc"), rs.getDate("tournament_start_date"), rs.getTime("tournament_start_time"), rs.getDate("tournament_end_date"), rs.getTime("tournament_end_time"), rs.getString("tournament_address"), rs.getString("tournament_city"), rs.getString("tournament_state"), rs.getString("tournament_country"), rs.getInt("tournament_zip"));
				tournamentObj.setTournamentId(tid);
			}
			return tournamentObj;
		}catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
