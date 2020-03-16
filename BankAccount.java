package com.meritamerica.assignment3;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {
	// Variables
	private double balance;
	private double interestRate;
	private java.util.Date accountOpenedOn; 
	private long accountNumber;
	
	//Constructors
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = new java.util.Date();
	}
	
	public BankAccount(double balance, double interestRate
			, java.util.Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate
			, java.util.Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
	}
	
	// Getters
	public long getAccountNumber() {
		return this.accountNumber;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public java.util.Date getOpenedOn(){
		return this.accountOpenedOn;
	}
	// Setters
	public void setAccountNumber(long accountNum) {
		this.accountNumber = accountNum;
	}
	
	//Methods
	//Boolean Withdraw
	public boolean withdraw(double amount){
        if (amount < 0){
            System.out.println("Negative amount not allowed! Thank You");
            return false;
        } else {
            if((this.getBalance() - amount) < 0){
                System.out.println("Insufficient Balance! Thank You.");
                return false;
            } else {
                double currentBalance = this.getBalance() - amount;
                this.balance = currentBalance;
                System.out.println("Withdraw Succesful! Thank You");
                return true;
            }
        }

    }
	// Boolean deposit
	public boolean deposit(double amount){
        if(amount <= 0){
            System.out.println("Deposit Failed! Negative amount");
            return false;
        } else {
            this.balance = this.getBalance() + amount;
            System.out.println("Deposit Successful! Thank You");
            return true;
        }
    }
	
	public double futureValue(int years){
        double pv = this.getBalance();
        double fv = pv * (Math.pow((1 + this.getInterestRate()), years));
        return fv;
    }
	
	//toString()
	public String toString(){
        DecimalFormat format = new DecimalFormat("##.00");
        return "\nChecking Account Balance: $" + format.format(this.getBalance()) + "\n"
                + "Checking Account Interest Rate: " + this.getInterestRate() + "\n"
                + "Checking Account Balance in 3 years: $" + format.format(this.futureValue(3));
    }
	//Only primitive types are parsed. objects are formatted
	// "dd/MM/yyyy" from test file
	
	static BankAccount readFromString(String accountData) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    	String dataArray1[] = accountData.split(",");
    	int parsedAccount = Integer.parseInt(dataArray1[0]);
    	double parsedBalance = Double.parseDouble(dataArray1[1]);
    	double parsedInterest = Double.parseDouble(dataArray1[2]);
    	Date formattedDate = dateFormatter.parse(dataArray1[3]);
    	
    	BankAccount bankAccount = new BankAccount(parsedAccount,parsedBalance
    			,  parsedInterest, formattedDate);
    	return bankAccount;
    	} catch (ParseException e) {
    		System.out.println("String Parsing Error!");
    		return null;
    	}
    }
	//overriding
	public String writeToString() {
		//the date format from test class
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    	return this.accountNumber + "," + this.balance + "," + this.interestRate
    			+ "," + dateFormatter.format(this.accountOpenedOn);
    }
}
