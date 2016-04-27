package main.java.com.getsetgo.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Match {
	int matchId;
	int tId;
	int gId;
	String matchName;
	String matchDesc;
	String mTag;
	String match_gender_type;
	String match_cardinality;
	Date matchStartDate;
	Time matchStartTime;
	Date matchEndDate;
	Time matchEndTime;
	String matchLocation;
	MatchClassStrategy mClassStrategy;
	StateContext tc;
	List<User> participants = new ArrayList<>();
	int match_type_id;
	
	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getMatchDesc() {
		return matchDesc;
	}

	public void setMatchDesc(String matchDesc) {
		this.matchDesc = matchDesc;
	}

	public String getmTag() {
		return mTag;
	}

	public void setmTag(String mTag) {
		this.mTag = mTag;
	}

	public String getMatch_gender_type() {
		return match_gender_type;
	}

	public void setMatch_gender_type(String match_gender_type) {
		this.match_gender_type = match_gender_type;
	}

	public String getMatch_cardinality() {
		return match_cardinality;
	}

	public void setMatch_cardinality(String match_cardinality) {
		this.match_cardinality = match_cardinality;
	}

	public Date getMatchStartDate() {
		return matchStartDate;
	}

	public void setMatchStartDate(Date matchStartDate) {
		this.matchStartDate = matchStartDate;
	}

	public Time getMatchStartTime() {
		return matchStartTime;
	}

	public void setMatchStartTime(Time matchStartTime) {
		this.matchStartTime = matchStartTime;
	}

	public Date getMatchEndDate() {
		return matchEndDate;
	}

	public void setMatchEndDate(Date matchEndDate) {
		this.matchEndDate = matchEndDate;
	}

	public Time getMatchEndTime() {
		return matchEndTime;
	}

	public void setMatchEndTime(Time matchEndTime) {
		this.matchEndTime = matchEndTime;
	}

	public String getMatchLocation() {
		return matchLocation;
	}

	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	public MatchClassStrategy getmClassStrategy() {
		return mClassStrategy;
	}

	public void setmClassStrategy(MatchClassStrategy mClassStrategy) {
		this.mClassStrategy = mClassStrategy;
	}

	public StateContext getTc() {
		return tc;
	}

	public void setTc(StateContext tc) {
		this.tc = tc;
	}
	
	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public int getMatch_type_id() {
		return match_type_id;
	}

	public void setMatch_type_id(int match_type_id) {
		this.match_type_id = match_type_id;
	}

	public Match(int tId,int gId, String mName, String mTag, String mDesc, String mGenderType, String mCardinality, int matchTypeID, Date mSDate, Time mSTime, 
			Date mEDate, Time mETime, String mLoc) {
		this.tId = tId;
		this.gId = gId;
		this.matchName = mName;
		this.mTag = mTag;
		this.matchDesc = mDesc;
		this.match_gender_type = mGenderType;
		this.match_cardinality = mCardinality;
		this.match_type_id = matchTypeID;
		this.matchStartDate = mSDate;
		this.matchStartTime = mSTime;
		this.matchEndDate = mEDate;
		this.matchEndTime = mETime;
		this.matchLocation = mLoc;
		MatchStrategyFactory msf = new MatchStrategyFactory();
		this.mClassStrategy = msf.createMatchStrategy(match_type_id);
	}
}
