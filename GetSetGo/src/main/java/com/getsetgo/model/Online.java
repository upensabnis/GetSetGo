package main.java.com.getsetgo.model;

public class Online implements MatchClassStrategy{

	String url;
	String userName;
	int passCode;
	
	public Online(String url, String userName, String s, String co, int z){
		this.url = url;
		this.userName = userName;
		this.passCode = z;
	}
	
	@Override
	public void whereToPlay() {
		// TODO Auto-generated method stub
		
	}

}
