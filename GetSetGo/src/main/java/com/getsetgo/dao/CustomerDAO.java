package main.java.com.getsetgo.dao;

import main.java.com.getsetgo.model.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
}