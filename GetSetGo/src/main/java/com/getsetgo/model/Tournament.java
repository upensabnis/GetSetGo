package main.java.com.getsetgo.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tournament {
	int tournamentId;
	String tournamentName;
	String tournamentDesc;
	Date tournamentStartDate;
	Time tournamentStartTime;
	Date tournamentEndDate;
	Time tournamentEndTime;
	String tournamentAddress;
	String tournamentCity;
	String tournamentState;
	String tournamentCountry;
	int tournamentZip;
	List<Game> games = new ArrayList<Game>();
	List<Match> matches = new ArrayList<Match>();
	TournamentStateContext tc;
	int hostID;

	public int getHostID() {
		return hostID;
	}

	public void setHostID(int hostID) {
		this.hostID = hostID;
	}

	public Tournament(String tName, String tDesc, Date tStartDate, Time tStartTime,
						Date tEndDate, Time tEndTime, String tAddress, String tCity, String tState, String tCountry, int tZip) {
		this.tournamentName = tName;
		this.tournamentDesc = tDesc;
		this.tournamentStartDate = tStartDate;
		this.tournamentStartTime = tStartTime;
		this.tournamentEndDate = tEndDate;
		this.tournamentEndTime = tEndTime;
		this.tournamentAddress = tAddress;
		this.tournamentCity = tCity;
		this.tournamentState = tState;
		this.tournamentCountry = tCountry;
		this.tournamentZip = tZip;
		this.setTc(new TournamentStateContext(new FutureState()));
	}
	
	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public String getTournamentDesc() {
		return tournamentDesc;
	}

	public void setTournamentDesc(String tournamentDesc) {
		this.tournamentDesc = tournamentDesc;
	}

	public Date getTournamentStartDate() {
		return tournamentStartDate;
	}

	public void setTournamentStartDate(Date tournamentStartDate) {
		this.tournamentStartDate = tournamentStartDate;
	}

	public Time getTournamentStartTime() {
		return tournamentStartTime;
	}

	public void setTournamentStartTime(Time tournamentStartTime) {
		this.tournamentStartTime = tournamentStartTime;
	}

	public Date getTournamentEndDate() {
		return tournamentEndDate;
	}

	public void setTournamentEndDate(Date tournamentEndDate) {
		this.tournamentEndDate = tournamentEndDate;
	}

	public Time getTournamentEndTime() {
		return tournamentEndTime;
	}

	public void setTournamentEndTime(Time tournamentEndTime) {
		this.tournamentEndTime = tournamentEndTime;
	}

	public String getTournamentAddress() {
		return tournamentAddress;
	}

	public void setTournamentAddress(String tournamentAddress) {
		this.tournamentAddress = tournamentAddress;
	}

	public String getTournamentCity() {
		return tournamentCity;
	}

	public void setTournamentCity(String tournamentCity) {
		this.tournamentCity = tournamentCity;
	}

	public String getTournamentState() {
		return tournamentState;
	}

	public void setTournamentState(String tournamentState) {
		this.tournamentState = tournamentState;
	}

	public String getTournamentCountry() {
		return tournamentCountry;
	}

	public void setTournamentCountry(String tournamentCountry) {
		this.tournamentCountry = tournamentCountry;
	}

	public int getTournamentZip() {
		return tournamentZip;
	}

	public void setTournamentZip(int tournamentZip) {
		this.tournamentZip = tournamentZip;
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		if(games != null)
			this.games = games;
	}
	
	public TournamentStateContext getTc() {
		return tc;
	}

	public void setTc(TournamentStateContext tc) {
		this.tc = tc;
	}
	
	public void addGames(ArrayList<Game> g){
		if(g != null){
			if(g.size() > 0){
				Iterator<Game> iterator = g.iterator();
		        while (iterator.hasNext()) {
		            this.games.add((Game)iterator.next());
		        }
			}
		}
	}
	
	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	public void addMatches(List<Match> matchList){
		if(matchList != null){
			if(matchList.size() > 0){
				Iterator<Match> iterator = matchList.iterator();
		        while (iterator.hasNext()) {
		            this.matches.add((Match)iterator.next());
		        }
			}
		}
	}

	public void addMatch(Match m) {
		this.matches.add(m);
		
	}
}
