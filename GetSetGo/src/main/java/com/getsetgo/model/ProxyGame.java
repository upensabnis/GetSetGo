package main.java.com.getsetgo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyGame {
	private static Map<Game,Boolean> gRegMap = new HashMap<>();
	private static Map<Integer, Game> gIDMap = new HashMap<>();

	
	public static void addAllGames(List<Game> allGames){
		
			System.out.println("eeeeeeeeeeeeeeee:"+gIDMap.size());
			for(Game g: allGames){
				System.out.println(g.getGame_id());
				gIDMap.put(g.getGame_id(), g);
			}
			System.out.println(gIDMap.size());
	}

	public static void addRegGame(List<Game> allRegGames) {

			for(Game g : allRegGames){
				gRegMap.put(gIDMap.get(g.getGame_id()), true);
			}
		
		
	}
	
	public static List<Game> getAllUnRegGames(){
		List<Game> returnList = new ArrayList<>();
		boolean flag = false;
		for(int gid : gIDMap.keySet()){
				for(Game g: gRegMap.keySet()){
					if(g.getGame_id()==gid){
						flag = true;
					}
				}
				if(flag==false){
					returnList.add(gIDMap.get(gid));
				}else
					flag=false;
		}
		return returnList;
	}

	public static Game getGame(int id) {
		System.out.println("dddddddddddddddd:"+gIDMap.size());
		System.out.println(id);
		System.out.println(gIDMap.get(id).getGame_name());
		return gIDMap.get(id);
	}

	public static void regGame(int gid) {
		gRegMap.put(gIDMap.get(gid), true);
		
	}
	
	public static void clearMaps(){
		if(gIDMap!=null)
			gIDMap.clear();
		if(gRegMap!=null)
			gRegMap.clear();
	}
}
