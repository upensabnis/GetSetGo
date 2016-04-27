package main.java.com.getsetgo.model;

public interface StateContext {

	public void setState(State state);
	public State getState();
	public void doActivity();
	public void doCancel();
}