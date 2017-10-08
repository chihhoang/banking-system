package com.bank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.Account;
import com.bank.models.Checking;
import com.bank.models.Customer;
import com.bank.models.Savings;

public class DbService {

	private String url = "jdbc:mysql://localhost:3306/bankdb";
	private String user = "root";
	private String password = "root";
	private Connection connection;

	private Connection connect() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	private void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 *  CRUD operations
	 */

	// CREATE
	public int addAccount(String firstName, String lastName, String ssn, AccountType accountType, double balance) {
		int userId = -1;
		int accountId = -1;

		connection = connect();
		try {
			connection.setAutoCommit(false);

			// add user
			PreparedStatement stmt = connection.prepareStatement("insert into users(firstname, lastname, ssn) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, ssn);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
		}

		try {
			connection.setAutoCommit(false);

			// add account
			PreparedStatement stmt = connection.prepareStatement("insert into accounts(type, balance) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, accountType.name());
			stmt.setDouble(2, balance);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				accountId = rs.getInt(1);
			}

			// link the user to account
			if (userId > 0 && accountId > 0) {
				stmt = connection.prepareStatement("insert into mappings(userid, accountid) values(?,?)");
				stmt.setInt(1, userId);
				stmt.setInt(2, accountId);
				stmt.executeUpdate();
				connection.commit();
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return accountId;
	}

	// READ
	public Customer getAccountById(int accountId) {

		Customer customer = null;
		connection = connect();

		try {
			PreparedStatement stmt = connection.prepareStatement("select firstname, lastname, ssn, type, balance"
									+ " from USERS u join MAPPINGS m on u.id = m.userid"
									+ " join ACCOUNTS a on a.id = m.accountid"
									+ " where a.id = ?");
			stmt.setInt(1, accountId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String ssn = rs.getString("ssn");
				AccountType accountType = AccountType.valueOf(rs.getString("type"));
				double balance = rs.getDouble("balance");

				Account account = null;
				if (accountType == AccountType.Checking) {
					account = new Checking(accountId, balance);
				} else if (accountType == AccountType.Savings) {
					account = new Savings(accountId, balance);
				} else {
					throw new Exception("Unknown account type");
				}

				if (account != null) {
					customer = new Customer(firstName, lastName, ssn, account);
				} else {
					System.out.println("Cannot retrieve account");
				}
			} else {
				System.out.println("No user account was found for ID " + accountId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return customer;
	}

	// UPDATE
	public boolean updateAccount(int accountId, double balance) {
		boolean success = false;
		connection = connect();

		try {
			PreparedStatement stmt = connection.prepareStatement("UPDATE ACCOUNTS SET BALANCE = ? WHERE ID = ?");
			stmt.setDouble(1, balance);
			stmt.setInt(2, accountId);
			if (stmt.executeUpdate() > 0) {
				success = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return success;
	}

	// DELETE
	public boolean deleteAccount(int accountId) {
		boolean success = false;
		connection = connect();

		try {
			PreparedStatement stmt = connection.prepareStatement("delete USERS, ACCOUNTS"
					+ " from USERS u join MAPPINGS m on u.id = m.userid"
					+ " join ACCOUNTS a on a.id = m.accountid"
					+ " where a.id = ?");
			stmt.setInt(1, accountId);
			if (stmt.executeUpdate() > 0) {
				success = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return success;
	}

	// RETRIEVE ALL ACCOUNTS
	public List<Customer> getAllAccounts() {
		List<Customer> customers = new ArrayList<>();

		connection = connect();
		try {
  		PreparedStatement stmt = connection.prepareStatement("select accountid, firstname, lastname, ssn, type, balance"
  				+ " from USERS u join MAPPINGS m on u.id = m.userid"
  				+ " join ACCOUNTS a on a.id = m.accountid");

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        	String firstName = rs.getString("firstname");
        	String lastName = rs.getString("lastname");
        	String ssn = rs.getString("ssn");
        	AccountType accountType = AccountType.valueOf(rs.getString("type"));
        	double balance = rs.getDouble("balance");
        	int accountId = rs.getInt("accountid");

        	Account account = null;
        	if (accountType == AccountType.Checking) {
        		account = new Checking(accountId, balance);
        	} else if (accountType == AccountType.Savings) {
        		account = new Savings(accountId, balance);
        	} else {
        		throw new Exception("Unknown account type");
        	}

        	if (account != null) {
        		customers.add(new Customer(firstName, lastName, ssn, account));
        	} else {
        		System.out.println("Cannot retrieve account");
        	}
      }
		} catch (Exception e) {
			e.printStackTrace();
		}


	}







}
