package main.java.com.getsetgo.customer.dao;

import main.java.com.getsetgo.customer.model.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
}