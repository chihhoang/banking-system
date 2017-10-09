package com.bank.models;

import com.bank.services.AccountType;

public class Savings extends Account {

	public Savings(int accountId, double initialDeposit) {
		super(accountId);
		setBalance(initialDeposit);
	}

  @Override
  public AccountType getAccountType() {
    return AccountType.Savings;
  }
}
