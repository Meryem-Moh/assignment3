package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CDOffering {
	private int term;
    private double interestRate;

    public CDOffering(int term, double interestRate){
        this.term = term;
        this.interestRate = interestRate;
    }
//Getters ans Setters
    public int getTerm(){
        return this.term;
    }

    public double getInterestRate(){
        return this.interestRate;
    }
    
    public void setTerm(int term) {
    	this.term = term;
    }
    
    public void setInterestRate(double interest) {
    	this.interestRate = interest;
    }

    public String toString(){
        return 
        		"Term: " + this.getTerm() + 
        		" Interest Rate: " + this.getInterestRate();
    }
    
    static CDOffering readFromString(String accountData) {
    	
    	String dataArray[] = accountData.split(",");
    	int parsedTerm = Integer.parseInt(dataArray[0]);
    	double parsedInterest = Double.parseDouble(dataArray[1]);
    	
    	CDOffering newOffering = new CDOffering(parsedTerm, parsedInterest);
    	return newOffering;
    	
    }
	
	public String writeToString() {
    	return this.term + "," +
    			this.interestRate;
    }
}
