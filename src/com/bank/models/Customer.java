package com.bank.models;

import java.io.Serializable;

public class Customer implements Serializable {

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
		    +  account;
	}

	public String basicInfo() {
		return "Account number: " + account.getAccountNumber()
			+ " | Name: " + firstName + " " + lastName;
	}

}