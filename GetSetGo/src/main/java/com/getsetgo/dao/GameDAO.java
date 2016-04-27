package main.java.com.getsetgo.dao;

import java.util.List;

import main.java.com.getsetgo.model.Game;

public interface GameDAO {
	public void insert(Game g);
	public int getGameId(String gameName);
	public String getGameName(int gameId);
	public List<Game> getAllGames(List<Integer> gameIds);
	public List<Game> getAllRegGames(int userId);
}
