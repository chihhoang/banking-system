package com.bank.models;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

public abstract class Account {

	private double balance = 0.0;
	private double interest = 0.0;
  private double transactionFee = 0.0;
	int accountNumber;
	private static int numberOfAccounts = 1000;

	public Account() {
		accountNumber = numberOfAccounts++;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
  
  public void setTransactionFee(double fee) {
    this.transactionFee = fee;
  }
  
  public double getTransactionfee() {
    return transactionFee;
  }

	public void withdraw(double amount) throws InsufficientFundsException {
		if (amount + transactionFee > balance) {
      throw new InsufficientFundsException();
		}

		balance -= amount + transactionFee;
		System.out.println("You have withdrawn $" + amount + " and incurred a fee of $5");
		System.out.println("You now have a balance of $" + balance);
	}

	public void deposit(double amount) throws InvalidAmountException {
		if (amount < 0) {
			throw new InvalidAmountException();
		}

		checkInterest(); // from balance
		if (amount >= 10000) {
			interest = 0.05;
		}
		amount = amount + amount * interest;
		balance += amount;
	}

	public void checkInterest() {
		if(balance > 10000) {
			interest = 0.05;
		} else {
			interest = 0.02;
		}
	}

}
