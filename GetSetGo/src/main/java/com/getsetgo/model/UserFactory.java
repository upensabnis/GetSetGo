package main.java.com.getsetgo.model;

public class UserFactory {

	public User createUser(String name, String emailID, String password, double d, char gender, String status, String role) {
		User user = null;
		if(role.equals("admin")){
			System.out.println("Admin created");
			user = new Admin(name, emailID, password, d, gender, status, role);
		}
		else if(role.equals("host")){
			user = new Host(name, emailID, password, d, gender, status, role);
		}
		else if(role.equals("participant")){
			user = new Participant(name, emailID, password, d, gender, status, role);
		}
		return user;
	}
}
