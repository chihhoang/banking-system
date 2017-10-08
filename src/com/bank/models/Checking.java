package com.bank.models;

public class Checking extends Account {

	private static String accountType = "Checking";

	public Checking() {}
	public Checking(double initialDeposit) {
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
}
