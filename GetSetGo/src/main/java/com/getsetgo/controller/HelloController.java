package main.java.com.getsetgo.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import main.java.com.getsetgo.dao.CustomerDAO;
import main.java.com.getsetgo.dao.GameDAO;
import main.java.com.getsetgo.dao.MatchDAO;
import main.java.com.getsetgo.dao.TournamentDAO;
import main.java.com.getsetgo.dao.UserDAO;
import main.java.com.getsetgo.model.Customer;
import main.java.com.getsetgo.model.Game;
import main.java.com.getsetgo.model.Match;
import main.java.com.getsetgo.model.ProxyGame;
import main.java.com.getsetgo.model.ProxyTournament;
import main.java.com.getsetgo.model.Tournament;
import main.java.com.getsetgo.model.User;
import main.java.com.getsetgo.model.UserFactory;



@Controller
@RequestMapping("/")
@SessionAttributes({"UserID", "Role", "tObj", "tid", "gid"})


public class HelloController {
	static ModelMap map1 = new ModelMap();
	
	@RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      model.addAttribute("message1", "Hi");
	      
	      ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
	      	 
	      CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
	      
	      Customer customer = new Customer(1, "upendra",28);
	      customerDAO.insert(customer);
	      
	      /*customer = new Customer(2, "Madhu",25);
	      //customerDAO.insert(customer);
	      
	      customer = new Customer(3, "Kavya",25);
	      //customerDAO.insert(customer);
	      	
	      //Customer customer1 = customerDAO.findByCustomerId(1);
	      model.addAttribute("customer1", customer1.getName());
	      
	      //customer1 = customerDAO.findByCustomerId(2);
	      model.addAttribute("customer2", customer1.getName());
	      
	      //customer1 = customerDAO.findByCustomerId(3);
	      model.addAttribute("customer3", customer1.getName());*/
	      
	      return "hello";
	   }
	
	public void addUserToDb(String name, String email, String pwd, char gender, String contact, String role){
		ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		System.out.println("Inside func");
		//User user = new User(name, email, pwd, Integer.parseInt(contact), gender, "online", role);
		UserFactory user= new UserFactory();
		User newUser = user.createUser(name, email, pwd, Double.parseDouble(contact), gender, "online", role); 
		userDAO.insert(newUser);
	}
	
	@RequestMapping("/hostLanding")
	public String hostLanding(){
		return "hostLanding";
	}
	
	@RequestMapping("/tournamentNewForm")
	public String addTournament(){
		return "addTournament";
	}
	
	@RequestMapping("/user")
	public String checkUser(@ModelAttribute("user_name") String email, @ModelAttribute("pwd") String pwd, 
			ModelMap model){
		//System.out.println("User name"+user.getName());
		ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		
		TournamentDAO tDAO = (TournamentDAO) context.getBean("tournamentDAO");
		
		String IDRole = userDAO.checkUser(email, pwd);
		
		if(IDRole.equals(""))
		{
			return "login";
		}
		
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzz");
		String[] IDRole_split = IDRole.split(" ");
		model.addAttribute("UserID",IDRole_split[0]);
		model.addAttribute("Role",IDRole_split[1]);
		map1.addAttribute("UserID",IDRole_split[0]);
		
		List<Tournament> allTournaments = tDAO.getAllTournaments();
		
		ProxyTournament.addAllTournaments(allTournaments);
		
		if(IDRole_split[1].equals("participant")){
			List<Tournament> allRegTournaments = tDAO.getAllRegTournaments(Integer.parseInt(IDRole_split[0]));
			ProxyTournament.addRegTournaments(allRegTournaments);
			model.addAttribute("regTournamentList", allRegTournaments);
			
			System.out.println("sssssssssssssssssssss Reg: "+allRegTournaments.size());
		} else if(IDRole_split[1].equals("host")){
			List<Tournament> allHostedTournaments = tDAO.getAllHostedTournaments(Integer.parseInt(IDRole_split[0]));
			ProxyTournament.addRegTournaments(allHostedTournaments);
			model.addAttribute("hostedTournamentList", allHostedTournaments);
		}
		
		List<Tournament> unRegTournaments = ProxyTournament.getAllUnRegTournaments();
		model.addAttribute("unRegTournamentList", unRegTournaments);
		System.out.println("sssssssssssssssssssss UnReg: "+unRegTournaments.size());
		return IDRole_split[1];
	}
	
	@RequestMapping("/test")
	public void test(@ModelAttribute("UserID") String id, @ModelAttribute("Role") String role){
		System.out.println("UserID:"+ id + "Role:"+role);
	}
	
	@RequestMapping(value = "/participant/tournament/{id}", method=RequestMethod.GET)
    public String tournamentPage(@PathVariable("id")int id, ModelMap model) {
		Tournament tObj = ProxyTournament.getTournament(id);
		model.addAttribute("tObj", tObj);
		model.addAttribute("tid", id);
		return "participantTournament";
    }
	
	@RequestMapping(value = "/participant/regtournament/{id}", method=RequestMethod.GET)
    public String regTournamentPage(@PathVariable("id")int id, ModelMap model, @ModelAttribute("UserID") String UserID) {
		ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
		GameDAO gDAO = (GameDAO) context.getBean("gameDAO");
		Tournament tObj = ProxyTournament.getTournament(id);
		List<Game> allRegGame = gDAO.getAllRegGames(Integer.parseInt(UserID));
		ProxyGame.addRegGame(allRegGame);
		
		List<Game> unRegMatches = ProxyGame.getAllUnRegGames();
		model.addAttribute("unRegGameList", unRegMatches);
		System.out.println("Size of unregmatches:"+unRegMatches.size());
		model.addAttribute("regGamesList", allRegGame);
		
		model.addAttribute("tid", id);
		model.addAttribute("tObj", tObj);
		return "participantRegisteredTournament";
    }
	
	@RequestMapping(value = "/participant/game/unreg/{id}", method=RequestMethod.GET)
    public String unRegGame(@PathVariable("id")int id, ModelMap model, @ModelAttribute("tid") int tid) {		
		Game currGame = ProxyGame.getGame(id);
		model.addAttribute("currGame", currGame);
		System.out.println("ccccccccccccccccccccccccc:"+currGame.getGame_name());
		model.addAttribute("gid", id);
		return "unreggame";
    }
	
	@RequestMapping(value = "/participant/game/reg/{id}", method=RequestMethod.GET)
    public String regMatch(@PathVariable("id")int id, ModelMap model, @ModelAttribute("tid") int tid) {
		ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
		GameDAO gDAO = (GameDAO) context.getBean("gameDAO");
		MatchDAO mDAO = (MatchDAO) context.getBean("matchDAO");
		Tournament currTournament = ProxyTournament.getTournament(tid);
		List<Match> currMatches = currTournament.getMatches();
		for(int i=0;i<currMatches.size();i++){
			if(currMatches.get(i).getMatchId()==id){
				Match m = currMatches.get(i);
				String gameName = gDAO.getGameName(m.getgId());
				String matchType = mDAO.getMatchType(m.getMatch_type_id());
				model.addAttribute("matchType",matchType);
				model.addAttribute("gameName",gameName);
				model.addAttribute("currMatch",m);
				model.addAttribute("mid",id);
				break;
			}
		}
		return "regmatch";
    }
	
	@RequestMapping(value = "/host/hostedtournament/{id}", method=RequestMethod.GET)
    public String hostedTournamentPage(@PathVariable("id")int id, ModelMap model) {
		Tournament tObj = ProxyTournament.getTournament(id);
		model.addAttribute("tObj", tObj);
		model.addAttribute("tid", id);
		return "match";
    }
	
	@RequestMapping(value = "/host/hostedtournament/match/{id}", method=RequestMethod.GET)
    public String hostedMatch(@PathVariable("id")int id, ModelMap model, @ModelAttribute("tid") int tid) {
		ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
		GameDAO gDAO = (GameDAO) context.getBean("gameDAO");
		MatchDAO mDAO = (MatchDAO) context.getBean("matchDAO");
		Tournament currTournament = ProxyTournament.getTournament(tid);
		List<Match> currMatches = currTournament.getMatches();
		for(int i=0;i<currMatches.size();i++){
			if(currMatches.get(i).getMatchId()==id){
				Match m = currMatches.get(i);
				String gameName = gDAO.getGameName(m.getgId());
				String matchType = mDAO.getMatchType(m.getMatch_type_id());
				model.addAttribute("matchType",matchType);
				model.addAttribute("gameName",gameName);
				model.addAttribute("currMatch",m);
				model.addAttribute("mid",id);
				break;
			}
		}
		return "regmatch";
    }
}
