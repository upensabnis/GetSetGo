package main.java.com.getsetgo.model;

public class EditedTournamentObserver implements Observer{

	@Override
	public void update(State currState) {
		// TODO Auto-generated method stub
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + currState.getClass().getName());
		
	}

}
