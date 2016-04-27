package main.java.com.getsetgo.model;

public class FinishedState implements State{
	public void nextState(StateContext tContext){
		System.out.println("Tournament is over\n");
		tContext.setState(this); 
	}
	
	public void cancelState(StateContext tContext){
		System.out.println("You can not cancel past tournament\n");
		tContext.setState(this); 
	}
}
