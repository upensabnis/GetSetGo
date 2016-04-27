package main.java.com.getsetgo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import main.java.com.getsetgo.dao.GameDAO;
import main.java.com.getsetgo.dao.TournamentDAO;
import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.ProxyGame;
import main.java.com.getsetgo.model.ProxyTournament;

@Controller
@RequestMapping("/game")
@SessionAttributes({"gid", "tid", "UserID"})

public class GameController {
	@RequestMapping("/addgame")
	public String addGame(@ModelAttribute("gName") String gName, 
			@ModelAttribute("gDesc") String gDesc, ModelMap model) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		GameDAO gDAO = (GameDAO) context.getBean("gameDAO");
		Game g = new Game(gName, gDesc);
		gDAO.insert(g);
		return "game";
	}
	
	@RequestMapping("/gameform")
	public String newGameForm(){
		return "addNewGame";
	}
	
	@RequestMapping("/reggame")
	public String regGame(@ModelAttribute("gid") int gid, @ModelAttribute("UserID") String UserID, @ModelAttribute("tid") int tid, ModelMap model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		tDAO.registerGame(Integer.parseInt(UserID), tid, gid);
		ProxyTournament.regTournament(tid);
		ProxyGame.regGame(gid);
		
		model.addAttribute("unRegTournamentList", ProxyTournament.getAllUnRegTournaments());
		model.addAttribute("regTournamentList", ProxyTournament.getAllRegTournaments());
		return "participant";
	}
}