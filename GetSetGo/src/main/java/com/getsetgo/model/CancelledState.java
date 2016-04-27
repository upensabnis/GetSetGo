package main.java.com.getsetgo.model;

public class CancelledState implements State{
	public void nextState(StateContext tContext){
		System.out.println("Tournament is already cancelled\n");
		tContext.setState(this); 
	}
	
	public void cancelState(StateContext tContext){
		System.out.println("Tournament is already cancelled\n");
		tContext.setState(new CancelledState()); 
	}
}
