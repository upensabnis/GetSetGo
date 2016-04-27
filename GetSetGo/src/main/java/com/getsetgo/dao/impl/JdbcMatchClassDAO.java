package main.java.com.getsetgo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import main.java.com.getsetgo.dao.MatchClassDAO;
import main.java.com.getsetgo.model.MatchClass;

public class JdbcMatchClassDAO implements MatchClassDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		System.out.println("set datasource");
		this.dataSource = dataSource;
	}
	
	public void insert(MatchClass m){
		System.out.println("try connection");
		String sql = "INSERT INTO match_class " +
				"(match_class_name, match_class_desc) VALUES (?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("Completed try");
		Connection conn = null;
		try {
			System.out.println("b4 conn");
			//conn = dataSource.getConnection();
			conn = dataSource.getConnection();
			System.out.println("after conn");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getMatch_class_name());
			ps.setString(2, m.getMatch_class_desc());
			
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
}
