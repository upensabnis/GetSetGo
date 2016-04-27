package main.java.com.getsetgo.dao;

import java.util.ArrayList;
import java.util.List;

import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.Tournament;
import main.java.com.getsetgo.model.User;

public interface TournamentDAO {
	public void insert(Tournament t);
	//public List<String>getAllTournaments(int hostID);
	public List<Tournament> getAllTournaments();
	public List<Tournament> getAllRegTournaments(int pid);
	public List<Tournament> getAllHostedTournaments(int hostId);
	public void deleteTournament(int tId);
	public User getParticipant(int userId);
	public List<Game> insertGamesInTournament (int tid);
	public void insertTournamentGames(int tid, List<Game> games);
	public void registerGame(int uid, int tid, int gid);
}
