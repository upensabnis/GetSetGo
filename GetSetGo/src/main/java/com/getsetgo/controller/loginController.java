package main.java.com.getsetgo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import main.java.com.getsetgo.model.Admin;
import main.java.com.getsetgo.model.User;

@Controller
@RequestMapping("/login")
@SessionAttributes({"userKey"})
public class loginController {
	
	@RequestMapping(method = RequestMethod.GET)
	   public String login(ModelMap model, HttpServletRequest req) {
	      
	      ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
	      
	      User user = new Admin("name", "emailID", "password", 1234, 'c', "status", "role");
	      model.addAttribute("userKey", user);
	      System.out.println("Email: "+user.getEmail());
	      //req.setAttribute("model", new String("hello"));
	      //System.out.println(req.getSession().getAttributeNames());
	      /*CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
	      
	      Customer customer = new Customer(1, "upendra",28);
	      //customerDAO.insert(customer);
	      
	      customer = new Customer(2, "Madhu",25);
	      //customerDAO.insert(customer);
	      
	      customer = new Customer(3, "Kavya",25);
	      //customerDAO.insert(customer);
	      	
	      //Customer customer1 = customerDAO.findByCustomerId(1);
	      model.addAttribute("customer1", customer1.getName());
	      
	      //customer1 = customerDAO.findByCustomerId(2);
	      model.addAttribute("customer2", customer1.getName());
	      
	      //customer1 = customerDAO.findByCustomerId(3);
	      model.addAttribute("customer3", customer1.getName());*/
	      
	      return "login";
	   }

}
