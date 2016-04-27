package main.java.com.getsetgo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import main.java.com.getsetgo.dao.TournamentDAO;
import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.Match;
import main.java.com.getsetgo.model.Participant;
import main.java.com.getsetgo.model.Tournament;
import main.java.com.getsetgo.model.User;

public class JdbcTournamentDAO implements TournamentDAO{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Tournament t){

		String sql = "INSERT INTO tournament (tournament_name, tournament_desc, tournament_start_date, tournament_start_time, tournament_end_date, tournament_end_time, tournament_address, tournament_city, tournament_state, tournament_country, tournament_zip, host_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ps.setString(1,t.getTournamentName());
			ps.setString(2, t.getTournamentDesc());
			ps.setDate(3,t.getTournamentStartDate());
			ps.setTime(4,t.getTournamentStartTime());
			ps.setDate(5,t.getTournamentEndDate());
			ps.setTime(6,t.getTournamentEndTime());
			ps.setString(7, t.getTournamentAddress());
			ps.setString(8, t.getTournamentCity());
			ps.setString(9, t.getTournamentState());
			ps.setString(10, t.getTournamentCountry());
			ps.setInt(11, t.getTournamentZip());
			ps.setInt(12, t.getHostID());
			System.out.println("b4 update");
			ps.executeUpdate();
			System.out.println("after update");
			ps.close();
			PreparedStatement ps1 = conn.prepareStatement("select last_insert_id() as last_id from tournament");
			ResultSet rs = ps1.executeQuery();
			if(rs.next()){
				int lastid = rs.getInt("last_id");
				insertTournamentGames(lastid, t.getGames());
			}
			ps1.close();
			
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
	
	public List<Tournament> getAllTournaments(){
		List<Tournament> tournamentObj = new ArrayList<>();
		List<Match> matches;
		List<Game> games;
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
				matches = insertMatches(rs.getInt("tournament_id"));
				games = insertGamesInTournament(rs.getInt("tournament_id"));
				currTournament.setGames(games);
				currTournament.setMatches(matches);
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
		List<Match> matches = new ArrayList<>();
		List<Game> games = new ArrayList<>();
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
				matches = insertMatches(rs.getInt("tournament_id"));
				games = insertGamesInTournament(rs.getInt("tournament_id"));
				regTournament.setGames(games);
				regTournament.setMatches(matches);
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
		List<Match> matches = null;
		List<Game> games = null;
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
				matches = insertMatches(tournamentObj.getTournamentId());
				games = insertGamesInTournament(rs.getInt("tournament_id"));
				tournamentObj.setGames(games);
				tournamentObj.setMatches(matches);
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
	
	public List<Match> insertMatches (int tid){
		List<Match> matches = new ArrayList<Match>();
		List<User> users = new ArrayList<User>();
		Match match = null;
		
		String sql = "SELECT * FROM match_old where tournament_id = ?";
		
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
				match = new Match(rs.getInt("tournament_id"),rs.getInt("game_id"),rs.getString("match_name"),rs.getString("match_tag"),rs.getString("match_desc"),rs.getString("match_gender_type"),rs.getString("match_cardinality"),rs.getInt("match_type_id"),rs.getDate("match_start_date"),rs.getTime("match_start_time"),rs.getDate("match_end_date"),rs.getTime("match_end_time"),rs.getString("match_location"));
				match.setMatchId(rs.getInt("match_id"));
				users = getCorrespondingUsers(match.getMatchId());
				match.setParticipants(users);
				matches.add(match);
			}
			return matches;
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
	
	public List<User> getCorrespondingUsers(int mid) {
		
		List<User> users = new ArrayList<User>();
		User user = null;
		String sql = "SELECT * FROM match_participants where match_id = ?";
		
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
				user = getParticipant(rs.getInt("ID"));
				users.add(user);
			}
			return users;
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
	
	public User getParticipant(int id) {
		String sql = "SELECT * FROM user where ID=?";
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
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new Participant(rs.getString("Name"),rs.getString("Email"),rs.getInt("Contact"),rs.getString("Gender").charAt(0),rs.getString("Status"),rs.getString("Role"));
				user.setId(rs.getInt("ID"));
				return user;
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

	
	public void insertGames(){
		
		
	}
	
	/*public List<String> getAllTournaments(int hostID) {
		List<String >tournamentNames = new ArrayList<>();
		String sql = "SELECT * FROM tournament where host_id = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return tournamentNames;
		}
		Connection conn = null;
		
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, hostID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getString("Password"));
				tournamentNames.add(rs.getString("tournament_name"));
			}
			return tournamentNames;
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
		
	}*/

	@Override
	public List<Tournament> getAllHostedTournaments(int hostId) {
		List<Tournament> tournamentObj = new ArrayList<>();
		List<Match> matches = new ArrayList<>();
		List<Game> games = new ArrayList<>();
		String sql = "SELECT * FROM tournament where host_id = ?";
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
			ps.setInt(1, hostId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tournament hostedTournament = getTournament(rs.getInt("tournament_id"));
				matches = insertMatches(rs.getInt("tournament_id"));
				games = insertGamesInTournament(rs.getInt("tournament_id"));
				hostedTournament.setGames(games);
				hostedTournament.setMatches(matches);
				tournamentObj.add(hostedTournament);
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

	@Override
	public void deleteTournament(int tId) {
		String sql1 = "DELETE FROM tournament where tournament_id = ?";
		String sql2 = "DELETE FROM reg_tournaments where tournament_id = ?";
		String sql3 = "DELETE FROM match_old where tournament_id = ?";
		Connection conn = null;
		String sql[] = {sql1, sql2, sql3};
		for(int i=0;i<sql.length;i++){
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql[i]);
				ps.setInt(1,tId);
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
	}
	
	public void insertTournamentGames(int tid, List<Game> games){
		//System.out.println("try connection");
		//List<Game> games = t.getGames();
		String sql = "INSERT INTO tournament_game " +
				"(tournament_id, game_id) VALUES (?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		//System.out.println("Completed try");
		Connection conn = null;
		if(games.size() > 0) {
			for(Game g : games){
				try {
					//System.out.println("b4 conn");
					//conn = dataSource.getConnection();
					conn = dataSource.getConnection();
					//System.out.println("after conn");
					PreparedStatement ps = conn.prepareStatement(sql);
					
					
					ps.setInt(1,tid);
					ps.setInt(2,g.getGame_id());
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
		}
	}

	
	public List<Game> insertGamesInTournament (int tid){
		List<Game> games = new ArrayList<Game>();
		Game game = null;
		
		String sql1 = "SELECT * FROM tournament_game where tournament_id = ?";
		String sql2 = "SELECT * FROM game where game_id = ?";
		
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
			conn = dataSource.getConnection();
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, tid);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, rs1.getInt("game_id"));
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					game = new Game(rs2.getString("game_name"),rs2.getString("game_desc"));
					game.setGame_id(rs2.getInt("game_id"));
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
	
public void registerGame(int uid, int tid, int gid){
		
		String sql = "INSERT INTO reg_tournaments " +
				"(participant_id, tournament_id, game_id) VALUES (?, ?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		//System.out.println("Completed try");
		Connection conn = null;
		try {
				conn = dataSource.getConnection();
				//System.out.println("after conn");
				PreparedStatement ps = conn.prepareStatement(sql);
						
				ps.setInt(1,uid);
				ps.setInt(2,tid);
				ps.setInt(3,gid);
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
}
