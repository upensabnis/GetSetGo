package main.java.com.getsetgo.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.com.getsetgo.dao.TournamentDAO;
import main.java.com.getsetgo.model.ProxyTournament;
import main.java.com.getsetgo.model.Tournament;

@Controller
@RequestMapping("/particiapnt")
public class ParticipantController {
	
	@RequestMapping("/getalltournaments")
	public String addTournament() {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		
		ProxyTournament pro = new ProxyTournament();
		//pro.add(t);
		return "tournament";
	}
}
