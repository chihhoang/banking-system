package com.bank.models;

public class Savings extends Account {

	private static String accountType = "Savings";

	public Savings() {}
	public Savings(double initialDeposit) {
		setBalance(initialDeposit);
		checkInterest();
    setTransactionFee(5);
	}

	@Override
	public String toString() {
		return "Account type: " + accountType + " account\n"
				+ "Account number: " + getAccountNumber() + "\n"
				+ "Balance: $" + getBalance() + "\n"
				+ "Interest: " + getInterest() * 100 + "%";
	}

  @Override
  public String getAccountType() {
    return accountType;
  }
}
