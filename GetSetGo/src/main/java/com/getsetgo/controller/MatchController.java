package main.java.com.getsetgo.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import main.java.com.getsetgo.dao.GameDAO;
import main.java.com.getsetgo.dao.MatchClassDAO;
import main.java.com.getsetgo.dao.MatchDAO;
import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.Match;
import main.java.com.getsetgo.model.MatchClass;
import main.java.com.getsetgo.model.ProxyTournament;

@Controller
@RequestMapping("/match")
@SessionAttributes({"tid", "UserID", "mid"})

public class MatchController {
	@RequestMapping("/addmatch")
	public String addMatch(@ModelAttribute("gameName") String gameName,
			@ModelAttribute("mName") String mName, @ModelAttribute("mTag") String mTag,
			@ModelAttribute("mDesc") String mDesc, @ModelAttribute("mGenderType") String mGenderType,
			@ModelAttribute("mCardinality") String mCard, @ModelAttribute("matchType") String matchType, 
			@ModelAttribute("mSDate") String mSDate, 
			@ModelAttribute("mSTime") String mSTime, @ModelAttribute("mEDate") String mEDate, 
			@ModelAttribute("mETime") String mETime,
			@ModelAttribute("mLoc") String mLoc, ModelMap model, @ModelAttribute("tid") int tid) {

		System.out.println("====================================================");
		System.out.println("ID: "+tid);
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		MatchDAO mDAO = (MatchDAO) context.getBean("matchDAO");
		GameDAO gDAO = (GameDAO) context.getBean("gameDAO");
		int gId = gDAO.getGameId(gameName);
		int mTypeId = mDAO.getMatchId(matchType);
		
		String[] sd = mSDate.split(" ");
		String[] ed = mEDate.split(" ");
		String givenStartMonth = sd[1].substring(0,sd[1].length()-1);
		String givenEndMonth = ed[1].substring(0,ed[1].length()-1);
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Novemeber", "December"};
		int startMonthIndex = -1;
		int endMonthIndex = -1;
		for(int i=0;i<months.length;i++){
			if(givenStartMonth.equals(months[i])){
				startMonthIndex = i;
			}
			if(givenEndMonth.equals(months[i])){
				endMonthIndex = i;
			}
			if(startMonthIndex!= -1 && endMonthIndex!=-1)
				break;
			
		}
		
		String Sdate = sd[2]+"-"+startMonthIndex+"-"+sd[0];
		Date StartDate = Date.valueOf(Sdate);
		
		String Edate = ed[2]+"-"+endMonthIndex+"-"+ed[0];
		Date EndDate = Date.valueOf(Edate);
		
		Time mSTime1 = Time.valueOf(mSTime);
		Time mETime1 = Time.valueOf(mETime);
		
		Match m = new Match(tid, gId, mName, mTag, mDesc, mGenderType, mCard, mTypeId, StartDate, mSTime1, EndDate, mETime1, mLoc);
		mDAO.insert(m);
		//List<Match> matchList = new ArrayList<>();
		//Tournament currTournament = ProxyTournament.getTournament(tid);
		//currTournament.addMatches(matchList);
		ProxyTournament.getTournament(tid).addMatch(m);
		return "match";
	}
	
	@RequestMapping("/addmatchclass")
	public String addMatchStrategy(@ModelAttribute("mClassName") String mClassName, @ModelAttribute("mClassDesc") String mClassDesc, ModelMap model) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		MatchClassDAO mcTypeDAO = (MatchClassDAO) context.getBean("matchClassDAO");
		MatchClass m = new MatchClass(mClassName, mClassDesc);
		mcTypeDAO.insert(m);
		return "game";
	}
	
	@RequestMapping("/matchform")
	public String matchForm(@ModelAttribute("tid") int tid, ModelMap model) {
		List<Game> games = ProxyTournament.getTournament(tid).getGames();
		model.addAttribute("allGames", games);
		return "addNewMatch";
	}
	
	@RequestMapping("/regmatch")
	public String regMatch(@ModelAttribute("mid") int mid, @ModelAttribute("UserID") String UserID, @ModelAttribute("tid") int tid) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		MatchDAO mDAO = (MatchDAO) context.getBean("matchDAO");
		
		mDAO.register(mid, Integer.parseInt(UserID), tid);
		return "regmatch";
	}
}
