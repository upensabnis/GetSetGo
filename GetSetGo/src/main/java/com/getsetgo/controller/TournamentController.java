package main.java.com.getsetgo.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import main.java.com.getsetgo.dao.GameDAO;
import main.java.com.getsetgo.dao.TournamentDAO;
import main.java.com.getsetgo.model.EditedTournamentObserver;
import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.ProxyTournament;
import main.java.com.getsetgo.model.Tournament;

@Controller
@RequestMapping("/tournament")
@SessionAttributes({"tid", "UserID"})

public class TournamentController {
	
	@RequestMapping("/addtournament")
	public String addTournament(@ModelAttribute("tName") String tName, @ModelAttribute("tDesc") String tDesc,
			@ModelAttribute("tSDate") String tSDate, @ModelAttribute("tSTime") String tSTime,
			@ModelAttribute("tEDate") String tEDate, @ModelAttribute("tETime") String tETime,
			@ModelAttribute("tAddress") String tAddress, @ModelAttribute("tCity") String tCity,
			@ModelAttribute("tState") String tState, @ModelAttribute("tCountry") String tCountry,
			@ModelAttribute("tZip") String tZip, @ModelAttribute("gid1") String gid1,
			 @ModelAttribute("gid2") String gid2,  @ModelAttribute("gid3") String gid3,
			 @ModelAttribute("gid4") String gid4, ModelMap model, @ModelAttribute("UserID") String UserID) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		GameDAO gDAO = (GameDAO)context.getBean("gameDAO");
		
		java.util.Date today = new java.util.Date();
		//Time tSTime1 = new Time(today.getTime());
		//Time tETime1 = new Time(today.getTime());
		
		String[] sd = tSDate.split(" ");
		String[] ed = tEDate.split(" ");
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
		
		Time tSTime1 = Time.valueOf(tSTime);
		Time tETime1 = Time.valueOf(tETime);
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		List<Integer> GameIds = new ArrayList<>();
		if(!gid1.equals(""))
			GameIds.add(Integer.parseInt(gid1));
		if(!gid2.equals(""))
			GameIds.add(Integer.parseInt(gid2));
		if(!gid3.equals(""))
			GameIds.add(Integer.parseInt(gid3));
		if(!gid4.equals(""))
			GameIds.add(Integer.parseInt(gid4));
		for(int i=0;i<GameIds.size();i++)
			System.out.println(GameIds.get(i));
		
		
		List<Game> gameObjs = gDAO.getAllGames(GameIds);
		Tournament t = new Tournament(tName, tDesc, StartDate, tSTime1, EndDate, tETime1, tAddress, tCity, tState, tCountry,
				Integer.parseInt(tZip));
		t.setHostID(Integer.parseInt(UserID));
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiii"+t.getTc().getState());
		t.getTc().attach(new EditedTournamentObserver());
		t.setGames(gameObjs);
		tDAO.insert(t);
		model.addAttribute("tObj", t);
		//System.out.println("Map Value: "+HelloController.map1.get("UserID"));
		//getTournamentsForHost(Integer.parseInt((String) (HelloController.map1.get("UserID"))));
		ProxyTournament.addTournament(t);
		model.addAttribute("hostedTournamentList", ProxyTournament.getAllRegTournaments());
		return "host";
	}
	
	/*public void getTournamentsForHost(int hostID) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		
		//tDAO.getAllTournaments(hostID);
	}*/

	@RequestMapping("/addgamestotournament")
	public String addGames(@ModelAttribute("tId") String tId, @ModelAttribute("tGames") Object[] tGames,
			ModelMap model) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		
		//tDAO.insert();
		return "tournamentGamesAdded";
		
	}
	
	@RequestMapping("/canceltournament")
	public String cancelTournament(@ModelAttribute("tid") int tId, ModelMap model) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		
		Tournament currTournament = ProxyTournament.getTournament(tId);
		currTournament.getTc().doCancel();
		System.out.println(currTournament.getTc().getClass().getName());
		ProxyTournament.delTournament(tId);
		
		tDAO.deleteTournament(tId);
		//tDAO.insert();
		List<Tournament> allHostedTournaments = ProxyTournament.getAllRegTournaments();
		model.addAttribute("hostedTournamentList", allHostedTournaments);
		return "host";
		
	}
}
