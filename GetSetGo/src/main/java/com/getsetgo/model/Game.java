package main.java.com.getsetgo.model;

public class Game {
	int game_id;
	String game_name;
	String game_desc;
	
	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getGame_desc() {
		return game_desc;
	}

	public void setGame_desc(String game_desc) {
		this.game_desc = game_desc;
	}
	
	public Game(String game_name, String game_desc) {
		this.game_name = game_name;
		this.game_desc = game_desc;		
	}	

}
