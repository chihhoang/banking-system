package com.bank.models;

import com.bank.services.AccountType;

public class Checking extends Account {

	public Checking(int accountId, double initialDeposit) {
		super(accountId);
		setBalance(initialDeposit);
	}


  @Override
  public AccountType getAccountType() {
    return AccountType.Checking;
  }
}
