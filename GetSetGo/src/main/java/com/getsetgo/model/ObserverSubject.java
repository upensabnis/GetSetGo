package main.java.com.getsetgo.model;

public interface ObserverSubject {

	public void attach(Observer observer);	
	public void detach(Observer observer);	 
	public void notifyAllObservers();

}
