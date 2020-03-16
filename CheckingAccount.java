package com.meritamerica.assignment3;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


	//is-A relationship
public class CheckingAccount extends BankAccount{
	//Variables are extended from super class
    
    //Constructors
    public CheckingAccount(){    //Default
        super(0.00, 0.001);
    }

    public CheckingAccount(double checkingB){
        super(checkingB, 0.001);
    }
    
    public CheckingAccount(long accountNumber, double balance, double interestRate
    		, java.util.Date accountOpenedOn) {
    	super(accountNumber, balance, interestRate, accountOpenedOn);
    }

    //Methods

  //Overriding
    public String toString(){
        DecimalFormat format = new DecimalFormat("##.00");
        return "\nChecking Account Balance: $" + format.format(this.getBalance()) + "\n"
                + "Checking Account Interest Rate: " + this.getInterestRate() + "\n"
                + "Checking Account Balance in 3 years: $" + format.format(this.futureValue(3));
    }
    
 
  //Only primitive types are parsed. objects are formatted
    static CheckingAccount readFromString(String accountData) {
    	//date format from test class
    	try {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String dataArray[] = accountData.split(",");
    	int parsedAccount = Integer.parseInt(dataArray[0]);
    	double parsedBalance = Double.parseDouble(dataArray[1]);
    	double parsedInterest = Double.parseDouble(dataArray[2]);
    	Date formatedDate = dateFormatter.parse(dataArray[3]);
    	
    	CheckingAccount checking = new CheckingAccount(parsedAccount, parsedBalance
    			, parsedInterest, formatedDate);
    	return checking;
    	} catch(ParseException e) {
    		System.out.println("Parsing Error!");
    		return null;
    	}
}
    
    
    
}
