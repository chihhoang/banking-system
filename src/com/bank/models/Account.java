package com.bank.models;

import java.io.Serializable;

import com.bank.services.AccountType;

public abstract class Account implements Serializable {

	private double balance = 0.0;
	private int accountNumber;

	public Account(int accountId) {
		this.accountNumber = accountId;
	}

  public abstract AccountType getAccountType();

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

	@Override
	public String toString() {
		return "Account type: " + getAccountType() + " account\n"
				+ "Account number: " + getAccountNumber() + "\n"
				+ "Balance: $" + getBalance() + "\n";
	}


}
