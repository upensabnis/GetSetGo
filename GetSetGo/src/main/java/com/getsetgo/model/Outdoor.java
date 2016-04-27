package main.java.com.getsetgo.model;

public class Outdoor implements MatchClassStrategy {

	String address;
	String city;
	String state;
	String country;
	int zipcode;
	
	public Outdoor(String add, String c, String s, String co, int z) {
		this.address = add;
		this.city = c;
		this.state = s;
		this.country = co;
		this.zipcode = z;		
	}
	
	@Override
	public void whereToPlay() {
		// TODO Auto-generated method stub
		
	}

}
