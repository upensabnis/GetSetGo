package main.java.com.getsetgo.model;

public class FutureState implements State{
	public void nextState(StateContext tContext){
		System.out.println("Moving to next state\n");
		tContext.setState(new RunningState()); 
	}
	
	public void cancelState(StateContext tContext){
		System.out.println("Tournament has been cancelled\n");
		tContext.setState(new CancelledState()); 
	}
}

