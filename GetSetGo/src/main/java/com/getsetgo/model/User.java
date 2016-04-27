package main.java.com.getsetgo.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public abstract class User {

	protected String name;
	protected int id;
	protected String emailID;
	protected String password;
	protected double contact;
	protected char gender;
	protected String status;
	protected String role;
	
	public User(String name, String emailID, String password, double d, char gender, String status, String role) {
		this.name = name;
		this.emailID = emailID;
		this.password = password;
		this.contact = d;
		this.gender = gender;
		this.status = status;
		this.role = role;
	}
	
	public User(String name, String emailID, int contact, char gender, String status, String role) {
		this.name = name;
		this.emailID = emailID;
		this.contact = contact;
		this.gender = gender;
		this.status = status;
		this.role = role;
	}

	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return emailID;
	}
	
	public String getPwd(){
		return password;
	}
	
	public double getContact(){
		return contact;
	}
	
	public char getGender(){
		return gender;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String getRole(){
		return role;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public abstract void login();
	
	public abstract void UpdateHistory(List<String> history);
	
}
