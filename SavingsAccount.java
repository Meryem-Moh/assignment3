package com.meritamerica.assignment3;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class SavingsAccount extends BankAccount{
	//Variables are extended from super class

    //Constructors
    public SavingsAccount(){
        super(0.00, 0.01);
    }

    public SavingsAccount(double savingB){
        super(savingB, 0.001);
    }
    
    public SavingsAccount(int accountNum, double balance, double interestRate
    		, java.util.Date accountOpenedOn) {
    	super(accountNum, balance, interestRate, accountOpenedOn);
    }

  

    //Methods

    public String toString(){
    //Objects are formatted
        DecimalFormat format = new DecimalFormat("##.00");
        return "\nSaving Account Balance: $" + format.format(this.getBalance()) + "\n"
                + "Saving Account Interest Rate: " + this.getInterestRate() + "\n"
                + "Saving Account Balance in 3 years: $" + format.format(this.futureValue(3));
    }
    
    static SavingsAccount readFromString(String accountData) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    	String array1[] = accountData.split(",");
    	int parsedAccount = Integer.parseInt(array1[0]);
    	double parsedBalance = Double.parseDouble(array1[1]);
    	double parsedInterest = Double.parseDouble(array1[2]);
    	Date formattedDate = dateFormatter.parse(array1[3]);
    	
    	SavingsAccount savings = new SavingsAccount(parsedAccount, parsedBalance
    			, parsedInterest, formattedDate);
    	return savings;
    	} catch (ParseException e) {
    		System.out.println("Parsing Error!");
    		return null;
    	}
    }
}
