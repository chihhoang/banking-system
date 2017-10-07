package com.bank.menu;

import java.util.List;
import java.util.Scanner;

import com.bank.models.Account;
import com.bank.models.Bank;
import com.bank.models.Checking;
import com.bank.models.Customer;
import com.bank.models.Savings;

public class Menu {

	Scanner keyboard = new Scanner(System.in);
	Bank bank = new Bank();

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.runMenu();
	}

	public void runMenu() {

		printHeader();
		boolean exit = false;
		int choice = -1;
		while (!exit) {
			printMenu();
			choice = getInput();
			if (choice == 0) {
				exit = true;
			}
			performAction(choice);
		}
	}

	public void printHeader() {
		System.out.println("|-----------------------|");
		System.out.println("|-------Wecome to-------|");
		System.out.println("|--Banking Application--|");
		System.out.println("|-----------------------|");
		System.out.println("|-----------------------|");

	}

	public void printMenu() {
		System.out.println("Please choose an option");
		System.out.println("1. Create new account");
		System.out.println("2. Make a deposit");
		System.out.println("3. Make a withdraw");
		System.out.println("4. List account balance");
		System.out.println("0. Exit");
	}

	public int getInput() {
		System.out.print("Enter your option: ");
		int choice = -1;
		do {
			try {
				choice = Integer.parseInt(keyboard.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please choose a number 1-4");
			}

			if (choice < 0 || choice > 4) {
				System.out
				    .println("Option is out of range. Please choose a number 1-4");
			}
		} while (choice < 0 || choice > 4);

		return choice;
	}

	private void performAction(int choice) {
		switch (choice) {
			case 0 :
				System.out.println("Thank you for using our banking system");
				System.out.println("Exiting...");
				System.exit(0);
				break;
			case 1 :
				createAccount();
				break;
			case 2 :
				makeDeposit();
				break;
			case 3 :
				makeWithdrawal();
				break;
			case 4 :
				listBalance();
				break;
			default :
				System.out.println();
		}
	}

	private void createAccount() {
		String firstName, lastName, ssn, accountType = "";
		double initialDeposit = 0;
		boolean valid = false;

		// Get user information
		while (!valid) {
			System.out.print("Please enter an accout type (checking/savings): ");
			accountType = keyboard.nextLine();
			if (accountType.equalsIgnoreCase("checking")
			    || accountType.equalsIgnoreCase("savings")) {
				valid = true;
			} else {
				System.out.println(
				    "Invalid account type. Please enter 'checking' or 'savings'");
			}
		}

		System.out.print("Please enter your first name: ");
		firstName = keyboard.nextLine();
		System.out.print("Please enter your last name name: ");
		lastName = keyboard.nextLine();
		System.out.print("Please enter your social security number: ");
		ssn = keyboard.nextLine();

		valid = false;
		while (!valid) {
			System.out.print("Please enter an initial deposit: ");
			try {
				initialDeposit = Double.parseDouble(keyboard.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Initial deposit must be a number");
			}
			if (accountType.equalsIgnoreCase("checking")) {
				if (initialDeposit < 100) {
					System.out.println(
					    "Initial deposit for checking account must be more than $100");
				} else {
					valid = true;
				}
			} else if (accountType.equalsIgnoreCase("savings")) {
				if (initialDeposit < 50) {
					System.out.println(
					    "Initial deposit for savings account must be more than $50");
				} else {
					valid = true;
				}
			} else {
				System.out.println("Shouldn't be here!");
			}
		}

		// Create an account
		Account account;
		if (accountType.equalsIgnoreCase("checking")) {
			account = new Checking(initialDeposit);
		} else {
			account = new Savings(initialDeposit);
		}

		Customer customer = new Customer(firstName, lastName, ssn, account);
		bank.addCustomer(customer);

	}

	private void listBalance() {
		int account = selectAccount();
		if (account >= 0) {
			System.out.println(bank.getCustomer(account).getAccount());
		}
	}

	private void makeWithdrawal() {
		int account = selectAccount();
		if (account >= 0) {
			System.out.println("How much would you like to withdraw?");
			double amount = 0.0;
			try {
				amount = Double.parseDouble(keyboard.nextLine());
			} catch (NumberFormatException e) {
				amount = 0.0;
			}

			bank.getCustomer(account).getAccount().withdraw(amount);
		}

	}

	private void makeDeposit() {
		int account = selectAccount();
		if (account >= 0) {
			System.out.print("How much would you like to deposit? ");
			double amount = 0.0;
			try {
				amount = Double.parseDouble(keyboard.nextLine());
			} catch (NumberFormatException e) {
				amount = 0.0;
			}

			bank.getCustomer(account).getAccount().deposit(amount);
		}
	}

	private int selectAccount() {
		List<Customer> customers = bank.getCustomers();
		if (customers.size() < 1) {
			System.out.println("There is no customer at your bank");
			return -1;
		}

		System.out.println("Select an account: ");
		for (int i = 0; i < customers.size(); i++) {
			System.out.println(i + 1 + ". " + customers.get(i).basicInfo());
		}
		int account = -1;
		System.out.print("Please enter your selection: ");
		while (account < 0 || account > customers.size() - 1) {
			try {
				account = Integer.parseInt(keyboard.nextLine()) - 1;
			} catch (NumberFormatException e) {
				System.out.println("Invalid account choice");
			}
		}

		return account;
	}

}
