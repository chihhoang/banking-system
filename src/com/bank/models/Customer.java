package com.bank.models;

public class Customer {

	private String firstName;
	private String lastName;
	private String ssn;
	private Account account;

	public Customer() { }

	public Customer(String firstName, String lastName, String ssn,
	    Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Customer information: \n"
				+ "First name: " + firstName + "\n"
				+ "Last name: " + lastName + "\n"
		    + "SSN: " + ssn + "\n"
		    +  account +"\n";
	}

	public String basicInfo() {
		return "First name: " + firstName
				+ " | Last name: " + lastName
		    + " | SSN: " + ssn
		    + " | Account number: " + account.getAccountNumber() + "\n";
	}

}