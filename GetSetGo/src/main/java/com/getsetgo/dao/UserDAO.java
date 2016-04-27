package main.java.com.getsetgo.dao;


import java.util.List;

import main.java.com.getsetgo.model.Tournament;
import main.java.com.getsetgo.model.User;

public interface UserDAO {
	public void insert(User user);
	//public Customer findByCustomerId(int custId);
	public String checkUser(String Email, String pwd);
	public List<Tournament> getAllTournaments();
	public List<Tournament> getAllRegTournaments(int pid);
}
