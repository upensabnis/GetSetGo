package main.java.com.getsetgo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.com.getsetgo.model.ProxyGame;
import main.java.com.getsetgo.model.ProxyTournament;

@Controller
@RequestMapping("/logout")

public class LogoutController {
	@RequestMapping(method = RequestMethod.GET)
	   public String logout(HttpSession session, ModelMap model) {
	    session.invalidate();  
	    model.clear();
	    
	    ProxyTournament.clearMaps();
	    ProxyGame.clearMaps();
		return "login";
	   }
}
