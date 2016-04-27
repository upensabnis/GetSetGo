package main.java.com.getsetgo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import main.java.com.getsetgo.dao.GameDAO;
import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.Match;

public class JdbcGameDAO implements GameDAO{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Game g){
		String sql = "INSERT INTO game " +
				"(game_name, game_desc) VALUES (?, ?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		Connection conn = null;
		try {

			//conn = dataSource.getConnection();
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, g.getGame_name());
			ps.setString(2, g.getGame_desc());
			

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
	public int getGameId(String gameName) {
		String sql = "SELECT * FROM game where game_name = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return 0;
		}
		System.out.println("Completed try");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, gameName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int gameID = rs.getInt("game_id");
				return gameID;
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
	public String getGameName(int gameId) {
		String sql = "SELECT * FROM game where game_id = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		System.out.println("Completed try");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, gameId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String gameName = rs.getString("game_name");
				return gameName;
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
	
	public List<Game> getAllGames(List<Integer> gameIds){
		List<Game> games = new ArrayList<Game>();
		String sql = "SELECT * FROM game where game_id = ?";
		Game game = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		System.out.println("Completed try");
		Connection conn = null;
		
		try{
			for(Integer i : gameIds){
				int game_id = i.intValue();
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, game_id);
				ResultSet rs = ps.executeQuery();
			
				while(rs.next()){
					game = new Game(rs.getString("game_name"),rs.getString("game_desc"));
					game.setGame_id(rs.getInt("game_id"));
					games.add(game);
				}
			}
			return games;
		
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

	@Override
	public List<Game> getAllRegGames(int userId) {
		List<Game> gameObj = new ArrayList<>();
		String sql = "SELECT * FROM reg_tournaments where participant_id = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return gameObj;
		}

		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Game g = getGame(rs.getInt("game_id"));
				gameObj.add(g);
			}
			return gameObj;
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

	private Game getGame(int gid) {
		String sql = "SELECT * FROM game WHERE game_id = ?";
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
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Game regGame = new Game(rs.getString("game_name"), rs.getString("game_desc"));
				regGame.setGame_id(gid);
				return regGame;
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
