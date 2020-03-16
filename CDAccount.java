package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


//is-A relationship
public class CDAccount extends BankAccount {
    public CDOffering offering = new CDOffering(1, 0.01);

    public CDAccount(CDOffering offering, double openingBalance){
        super(openingBalance, offering.getInterestRate());
        this.offering = offering;
    }
    
    public CDAccount(int accountNum, double balance, double interest
    		, java.util.Date accountOpenedOn, int term) {
    	super(accountNum, balance, interest, accountOpenedOn);
    	this.offering.setTerm(term);
    	this.offering.setInterestRate(interest);
    }

    public int getTerm(){
        return this.offering.getTerm();
    }
    
    static CDAccount readFromString(String accountData) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    	String dataArray[] = accountData.split(",");
    	int parsedAccount = Integer.parseInt(dataArray[0]);
    	double parsedBalance = Double.parseDouble(dataArray[1]);
    	double parsedInterest = Double.parseDouble(dataArray[2]);
    	Date formattedDate = dateFormatter.parse(dataArray[3]);
    	int parsedTerm = Integer.parseInt(dataArray[4]);
    	
    	CDAccount cdAccount = new CDAccount(parsedAccount, parsedBalance
    			,  parsedInterest , formattedDate, parsedTerm);
    	return cdAccount;
    	} catch (ParseException e) {
    		return null;
    	}
    }
    
    // override 
    public boolean withdraw(double amount){
        return false;
    }
	
	public boolean deposit(double amount) {
        return false;
    }
	
	public double futureValue() {
		double pv = this.getBalance();
        double fv = pv * (Math.pow((1 + this.getInterestRate()), this.offering.getTerm()));
        return fv;
	}
	@Override
	public String writeToString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    	return this.getAccountNumber() + "," + this.getBalance() + "," + this.getInterestRate()
    			+ "," + dateFormatter.format(this.getOpenedOn()) + "," + this.getTerm();
    }
}
