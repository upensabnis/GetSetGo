package main.java.com.getsetgo.dao;

import java.util.List;

import main.java.com.getsetgo.model.Match;

public interface MatchDAO {
	public void insert(Match m);
	public int getMatchId(String mtype);
	public String getMatchType(int match_type_id);
	public void register(int mid, int pid, int tid);
	public List<Match> getAllRegMatches(int pid);
}
