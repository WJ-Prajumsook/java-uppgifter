package org.wj.prajumsook;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;


public class JavaUppgift01 {
	
	private static List<Account> accounts = new ArrayList<>();
	
	public static void main(String[] args) {
		generateAccountList();
		
		Set uniqueSet = accounts.stream()
		                .collect(Collectors.toCollection(
		                        () -> new TreeSet<>(
		                                Comparator.comparing(Account::getAccountName))
		                        )
		                );
		
		System.out.println(accounts);
		System.out.println(uniqueSet);
	}
	
	
	private static void generateAccountList() {
		accounts.add(
			new Account()
				.setAccountName("Test Account")
				.setBalance("310.000")
		);
		accounts.add(
			new Account()
				.setAccountName("Savings Account")
				.setBalance("2310.000")
		);
		accounts.add(
			new Account()
				.setAccountName("Checking Account")
				.setBalance("2010.000")
		);
		accounts.add(
			new Account()
				.setAccountName("Salary Account")
				.setBalance("20310.000")
		);
		accounts.add(
			new Account()
				.setAccountName("Test Account")
				.setBalance("10.000")
		);
		accounts.add(
			new Account()
				.setAccountName("Test Account")
				.setBalance("20.000")
		);
		accounts.add(
			new Account()
		  	.setAccountName("Savings Account")
		    .setBalance("20.000")
			);
	}
	
}

class Account {
	private String accountName;
	private String balance;
	
	public Account setAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}
	
	public Account setBalance(String balance) {
		this.balance = balance;
		return this;
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public String getBalance() {
		return this.balance;
	}
	
	public String toString() {
		return "Account name: '" + this.accountName + "', balance: '" + this.balance + "'"; 
	}
}