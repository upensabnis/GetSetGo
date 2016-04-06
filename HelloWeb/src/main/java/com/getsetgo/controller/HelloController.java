package main.java.com.getsetgo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.com.getsetgo.customer.dao.CustomerDAO;
import main.java.com.getsetgo.customer.model.Customer;

import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")

public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      
	      ApplicationContext context = 
	      		new ClassPathXmlApplicationContext("Spring-Module.xml");
	      	 
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
	      
	      return "hello";
	   }
}
