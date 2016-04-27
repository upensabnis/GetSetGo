package main.java.com.getsetgo.model;

import java.util.ArrayList;
import java.util.List;

public class TournamentStateContext implements StateContext,ObserverSubject{
	
	private State currState;
	private List<Observer> stateChangedobservers = new ArrayList<Observer>();
	
	public TournamentStateContext(State state){
		this.currState = state;
	}
	
	public void setState(State state){
		currState = state;
		notifyAllObservers();
	}	
	
	public State getState(){
		return currState;
	}
	
	public void doActivity(){
		currState.nextState(this);
		notifyAllObservers();
	}
	
	public void doCancel(){
		currState.cancelState(this);
		notifyAllObservers();
	}	
	

	@Override
	public void attach(Observer observer){
		 stateChangedobservers.add(observer);		
	 }
	
	@Override
	public void detach(Observer observer){
		 stateChangedobservers.remove(observer);		
	 }
	
	@Override 
	public void notifyAllObservers(){
	      for (Observer observer : stateChangedobservers) {
	         observer.update(this.getState());
	      }
	}
}
