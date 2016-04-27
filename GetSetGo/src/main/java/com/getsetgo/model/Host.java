package main.java.com.getsetgo.model;

import java.util.ArrayList;
import java.util.List;

public class Host extends User {
	List<String> pastTournaments = new ArrayList<String>();
	public Host(String name, String emailID, String password, double d, char gender, String status, String role) {
		/*this.name = name;
		this.emailID = emailID;
		this.password = password;
		this.contact = contact;
		this.gender = gender;
		this.status = status;
		this.role = role;*/
		super(name, emailID, password, d, gender, status, role);
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void UpdateHistory(List<String> history) {
		// TODO Auto-generated method stub
		this.pastTournaments = history;
	}
}
