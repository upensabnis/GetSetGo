package main.java.com.getsetgo.model;

public class MatchStrategyFactory {
	
	public MatchClassStrategy createMatchStrategy(int matchTypeId) {
		MatchClassStrategy mClassStrategy = null;
		if(matchTypeId==1){
			mClassStrategy = new Online("url", "Name", "", "", 1234);
		}else if(matchTypeId==2){
			mClassStrategy = new Outdoor("address", "cityName", "stateName", "countryName", 1234);
		}
		
		return mClassStrategy;
	}
}
