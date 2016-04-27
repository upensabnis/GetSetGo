package main.java.com.getsetgo.model;

public interface State {
	public void nextState(StateContext tContext);
	public void cancelState(StateContext tContext);
}
