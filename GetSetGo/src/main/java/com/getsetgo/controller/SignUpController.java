package main.java.com.getsetgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.com.getsetgo.model.User;

@Controller
@RequestMapping("/signup")

public class SignUpController {
	HelloController hc = new HelloController();
	
	@RequestMapping(method = RequestMethod.GET)
	   public String signUpform(ModelMap model) {
	      return "signUp";
	   }
	
	@RequestMapping("/signupformfilled")
	   public String userSignIn(@ModelAttribute("name") String name,@ModelAttribute("email") String email,
			   @ModelAttribute("pwd") String pwd, 
			   @ModelAttribute("gender") char gender, 
			   @ModelAttribute("contact") String contact, 
			   @ModelAttribute("role") String role, ModelMap model) {
		//String insert=(String) request.getAttribute("insert");
	      //model.addAttribute("loginName", "Kavya");
		System.out.println(role);  
		hc.addUserToDb(name, email, pwd, gender, contact, role);
	    return "login";
	   }
}
