package com.bank.models;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<>();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public int selectAccount() {
		return 1;
	}

	public Customer getCustomer(int account) {
		return customers.get(account);
	}

  public Customer getCustomerByAccountNumber(int accountNumber) {
    Customer customer = null;
    for (Customer c : customers) {
      if (c.getAccount().getAccountNumber() == accountNumber) {
        customer = c;
        break;
      }
    }
    
    return customer;
  }

  public void remove(Customer customer) {
    customers.remove(customer);
  }


}
