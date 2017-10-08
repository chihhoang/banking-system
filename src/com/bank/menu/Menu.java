package com.bank.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.bank.exceptions.InvalidAccountTypeException;
import com.bank.models.Account;
import com.bank.models.Bank;
import com.bank.models.Checking;
import com.bank.models.Customer;
import com.bank.models.Savings;

public class Menu {

	Scanner keyboard = new Scanner(System.in);
	String input = "";
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
		displayHeader("Please choose an option");
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
				input = keyboard.nextLine();
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please choose a number 1-4");
				continue;
			}

			if (choice < 0 || choice > 4) {
				System.out.println("Option is out of range. Please choose a number 1-4");
			}
		} while (choice < 0 || choice > 4);

		return choice;
	}

	// perform action according to user input
	private void performAction(int choice) {
		switch (choice) {
			case 0 :
				System.out.println("Thank you for using our banking system");
				System.out.println("Exiting...");
				keyboard.close();
				System.exit(0);
				break;
			case 1 :
				try {
					createAccount();
				} catch (InvalidAccountTypeException e) {
					System.out.println("Error: Account was not created");
				}
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

	// helper method to get user info
	private String askQuestion(String question, List<String> answers) {
		String response = "";
		boolean firstRun = true;

		do {
    		if (!firstRun) { // print error for account invalid input
    			System.out.println("Invalid account type");
    		}
    		System.out.print(question);
    		response = keyboard.nextLine();
    		firstRun = false;
		} while (answers != null && !answers.contains(response.toLowerCase()));

		return response;
	}

	private double getInitialDeposit(String accountType) {
		double initialDeposit = 0.0;
		boolean valid = false;
		while (!valid) {
			System.out.print("Please enter an initial deposit: ");
			input = keyboard.nextLine();
			try {
				initialDeposit = Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("Initial deposit must be a number");
				continue;
			}
			if (accountType.equalsIgnoreCase("checking")) {
				if (initialDeposit < 100) {
					System.out.println(
					    "Initial deposit for checking account must be at least $100");
				} else {
					valid = true;
				}
			} else if (accountType.equalsIgnoreCase("savings")) {
				if (initialDeposit < 50) {
					System.out.println(
					    "Initial deposit for savings account must be at least $50");
				} else {
					valid = true;
				}
			} else {
				System.out.println("Shouldn't be here!");
			}
		}

		return initialDeposit;
	}

	private void createAccount() throws InvalidAccountTypeException {
		displayHeader("Create an account");
		String accountType;

		String firstName, lastName, ssn;
		accountType = askQuestion("Please enter an account type (checking/savings): ", Arrays.asList("checking", "savings"));
		firstName = askQuestion("Please enter your first name: ", null);
		lastName = askQuestion("Please enter your last name name: ", null);
		ssn = askQuestion("Please enter your social security number: ", null);
		double initialDeposit = getInitialDeposit(accountType);

		// Create an account
		Account account;
		if (accountType.equalsIgnoreCase("checking")) {
			account = new Checking(initialDeposit);
		} else if (accountType.equalsIgnoreCase("savings")) {
			account = new Savings(initialDeposit);
		} else {
			throw new InvalidAccountTypeException();
		}

		Customer customer = new Customer(firstName, lastName, ssn, account);
		bank.addCustomer(customer);
		System.out.println("Account was successfully created with id "
										+ customer.getAccount().getAccountNumber() + " and an initial balance of $" + customer.getAccount().getBalance());
	}

	private void listBalance() {
		displayHeader("Account details");
		int account = selectAccount();
		if (account >= 0) {
			System.out.println(bank.getCustomer(account).getAccount());
		}
	}

	// helper method to get the dollar amount to withdraw or deposit
	private double getDollarAmount(String message) {
		System.out.println(message);

		double amount = 0.0;
		try {
			input = keyboard.nextLine();
			amount = Double.parseDouble(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid amount - Processing with 0 amount");
			amount = 0.0;
		}

		return amount;
	}

	private void makeWithdrawal() {
		displayHeader("Make a withdrawal");
		int account = selectAccount();
		if (account >= 0) {
			double amount = getDollarAmount("How much would you like to withdraw? ");

//			bank.getCustomer(account).getAccount().withdraw(amount);
		}
	}

	private void makeDeposit() {
		displayHeader("Make a deposit");
		int account = selectAccount();
		if (account >= 0) {
			double amount = getDollarAmount("How much would you like to deposit? ");

//			bank.getCustomer(account).getAccount().deposit(amount);
		}
	}

	private int selectAccount() {
		List<Customer> customers = bank.getCustomers();
		if (customers.size() < 1) {
			System.out.println("There is no customer at your bank");
			return -1;
		}

		int account = -1;
		while (account < 0 || account > customers.size() - 1) {
			System.out.println("Select an account: ");
			for (int i = 0; i < customers.size(); i++) {
				System.out.println("\t" + (i + 1) + ". " + customers.get(i).basicInfo());
			}

			System.out.print("Please enter your selection: ");
			try {
				input = keyboard.nextLine();
				account = Integer.parseInt(input) - 1;
			} catch (NumberFormatException e) {
				System.out.println("Invalid account choice");
				account = -1;
			}
		}

		return account;
	}

	// helper method to display a header for each option
	private void displayHeader(String message) {
		System.out.println();
		int width = message.length() + 6;
		StringBuilder sb = new StringBuilder();
		sb.append("*");
		for (int i = 0; i < width - 2; i++) {
			sb.append("-");
		}
		sb.append("*");

		System.out.println(sb);
		System.out.println("|  " + message + "  |");
		System.out.println(sb);
	}

}
