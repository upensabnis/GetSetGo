package main.java.com.getsetgo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProxyTournament {
	private static Map<Tournament,Boolean> tRegMap = new HashMap<>();
	private static Map<Integer, Tournament> tIDMap = new HashMap<>();
	
	/*public void add(Tournament t){
		tMap.put(t, false);
	}*/
	
	public static void addAllTournaments(List<Tournament> allTournaments){
		
			
			for(Tournament t: allTournaments){
				t.setTc(new TournamentStateContext(new FutureState()));
				t.getTc().attach(new EditedTournamentObserver());
				tIDMap.put(t.getTournamentId(), t);
				ProxyGame.addAllGames(t.getGames());
			}
		
		
	}
	
	/*public void remove(Tournament t){
		tMap.remove(t);
	}
	
	public void addRegistered(Tournament t){
		tMap.put(t,true);
	}*/
	
	public static void addRegTournaments(List<Tournament> allRegTournaments){
		
			
			for(Tournament t: allRegTournaments){
				tRegMap.put(tIDMap.get(t.getTournamentId()), true);
			}
		
	}
	
	/*public void removeRegistered(Tournament t){
		tMap.put(t,false);
	}*/
	
	public static void clearMaps(){
		if(tIDMap!=null)
			tIDMap.clear();
		if(tRegMap!=null)
			tRegMap.clear();
	}

	public static Tournament getTournament(int tid) {
		return tIDMap.get(tid);
	}

	public static void delTournament(int tId) {
		tRegMap.remove(tIDMap.get(tId));
		tIDMap.remove(tId);
		
	}
	
	public static List<Tournament> getAllRegTournaments(){
		List<Tournament> returnList = new ArrayList<>();
		System.out.println("Reg size" + tRegMap.size());
		for(Tournament t : tRegMap.keySet()){
			returnList.add(t);
		}
		System.out.println("rl size"+ returnList.size());
		return returnList;
	}
	
	public static List<Tournament> getAllUnRegTournaments(){
		List<Tournament> returnList = new ArrayList<>();
		boolean flag = false;
		for(int tid : tIDMap.keySet()){
				for(Tournament t: tRegMap.keySet()){
					if(t.getTournamentId()==tid){
						//returnList.add(tIDMap.get(tid));
						flag = true;
					}
				}
				if(flag==false){
					returnList.add(tIDMap.get(tid));
				}else
					flag=false;
		}
		return returnList;
	}

	public static void regTournament(int tid) {
		tRegMap.put(tIDMap.get(tid), true);
		
	}

	public static void addTournament(Tournament t) {
		tIDMap.put(t.getTournamentId(), t);
		tRegMap.put(tIDMap.get(t.getTournamentId()), true);
		
	}
	
}
