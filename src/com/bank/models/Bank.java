package com.bank.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.services.AccountType;
import com.bank.services.DbService;

public class Bank implements Serializable {

	private DbService database = new DbService();

	public int openAccount(String firstName, String lastName, String ssn, AccountType accountType, double balance) {
		return database.addAccount(firstName, lastName, ssn, accountType, balance);
	}

	public Customer getCustomer(int accountId) {
		return database.getAccountById(accountId);
	}

	public List<Customer> getCustomers() {
		return database.getAllAccounts();
	}

  public boolean closeAccount(int accountId) {
    return database.deleteAccount(accountId);
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public void withdraw(int accountId, double amount) throws InsufficientFundsException {
  		Customer customer = getCustomer(accountId);
  		double transactionFee = getTransactionFee();
		if (amount + transactionFee > customer.getAccount().getBalance()) {
      throw new InsufficientFundsException();
		}

		double newBalance = customer.getAccount().getBalance() - amount - transactionFee;
		database.updateAccount(accountId, newBalance);

		System.out.println("You have withdrawn $" + amount + " and incurred a fee of $5");
		System.out.println("You now have a balance of $" + newBalance);
	}

	public void deposit(int accountId, double amount) throws InvalidAmountException {
		Customer customer = getCustomer(accountId);

		if (amount < 0) {
			throw new InvalidAmountException();
		}

		double interest = checkInterest(customer.getAccount().getBalance(), amount); // from balance
		if (amount >= 10000) {
			interest = 0.05;
		}
		amount = amount + (amount * interest);
		database.updateAccount(accountId, customer.getAccount().getBalance() + amount);
	}

	public double checkInterest(double balance, double amount) {
		double interest = 0.0;

		if(balance + amount > 10000) {
			interest = 0.05;
		} else {
			interest = 0.02;
		}

		return interest;
	}

	public double getTransactionFee() {
    double transactionFee = 5.0;

    return transactionFee;
  }

}
