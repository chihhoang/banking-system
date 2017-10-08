package com.bank.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {

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
    if (customers.size() >= 1) {
      customer.getAccount().setAccountNumber(getNextAccountNumber());
    }

		customers.add(customer);
	}

  public int getNextAccountNumber() {
    int max = -1;
    for (Customer c : customers) {
      max = Math.max(max, c.getAccount().getAccountNumber());
    }

    return max + 1;
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

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
