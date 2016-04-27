package main.java.com.getsetgo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import main.java.com.getsetgo.dao.MatchDAO;
import main.java.com.getsetgo.model.Match;
import main.java.com.getsetgo.model.Tournament;

public class JdbcMatchDAO implements MatchDAO{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Match m){
		String sql = "INSERT INTO match_old " +
				"(tournament_id, game_id, match_name, match_tag, match_desc, match_gender_type, match_cardinality, match_type_id, match_start_date, match_start_time, match_end_date, match_end_time, match_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			conn = dataSource.getConnection();
			System.out.println("after conn");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, m.gettId());
			ps.setInt(2, m.getgId());
			ps.setString(3, m.getMatchName());
			ps.setString(4, m.getmTag());
			ps.setString(5, m.getMatchDesc());
			ps.setString(6, m.getMatch_gender_type());
			ps.setString(7, m.getMatch_cardinality());
			ps.setInt(8, m.getMatch_type_id());
			ps.setDate(9, m.getMatchStartDate());
			ps.setTime(10, m.getMatchStartTime());
			ps.setDate(11, m.getMatchEndDate());
			ps.setTime(12, m.getMatchEndTime());
			ps.setString(13, m.getMatchLocation());
			
			ps.executeUpdate();
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
	public int getMatchId(String mtype) {
		String sql = "SELECT * from match_type where match_type_name = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return 0;
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mtype);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int mTypeID = rs.getInt("match_type_id");
				return mTypeID;
			}
			return 0;
			
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
	public String getMatchType(int match_type_id) {
		String sql = "SELECT * from match_type where match_type_id = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, match_type_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String mName = rs.getString("match_type_name");
				return mName;
			}
			return null;
			
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
	public void register(int mid, int pid, int tid) {
		String sql1 = "INSERT INTO match_participants (match_id, ID) VALUES (?, ?)";
		String sql2 = "INSERT INTO reg_tournaments (participant_id, tournament_id) VALUES (?,?)";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			System.out.println("after conn");
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, mid);
			ps1.setInt(2, pid);
			ps1.executeUpdate();
			ps1.close();
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, pid);
			ps2.setInt(2, tid);
			ps2.executeUpdate();
			ps2.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			//throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		
	}

	@Override
	public List<Match> getAllRegMatches(int pid) {
		List<Match> matchObj = new ArrayList<>();

		String sql = "SELECT * FROM match_participants where ID = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return matchObj;
		}

		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Match regMatch = getMatch(rs.getInt("match_id"));
				matchObj.add(regMatch);
			}
			return matchObj;
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

	private Match getMatch(int mid) {
		String sql = "SELECT * FROM match_old WHERE match_id = ?";
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
			ps.setInt(1, mid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Match regMatch = new Match(rs.getInt("tournament_id"), rs.getInt("game_id"), rs.getString("match_name"), rs.getString("match_tag"), rs.getString("match_desc"),rs.getString("match_gender_type"), rs.getString("match_cardinality"),rs.getInt("match_type_id"),rs.getDate("match_start_date"),rs.getTime("match_start_time"),rs.getDate("match_end_date"),rs.getTime("match_end_time"), rs.getString("match_location"));
				regMatch.setMatchId(rs.getInt("match_id"));
				return regMatch;
			}
			return null;
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
