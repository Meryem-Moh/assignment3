package com.meritamerica.assignment3;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class AccountHolder implements Comparable<AccountHolder> {
	// Variables of Class
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    public CheckingAccount checking[] = new CheckingAccount[1];
    public SavingsAccount savings[] = new SavingsAccount[1];
    public CDAccount cdAccount[] = new CDAccount[1];


    // Constructors
    public AccountHolder() {
        this.firstName = "John";
        this.middleName = "M";
        this.lastName = "Doe";
        this.ssn = "n/a";
    }

    public AccountHolder(String firstname, String middlename, String lastname, String ssn){
        this.firstName = firstname;
        this.middleName = middlename;
        this.lastName = lastname;
        this.ssn = ssn;
    }

    // Setters and Getters
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setMiddleName(String middlename){
        this.middleName = middlename;
    }

    public String getMiddleName(){
        return this.middleName;
    }

    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setSSN(String sn){
        this.ssn = sn;
    }

    public String getSSN(){
        return this.ssn;
    }

    // Methods from Assign 2

  
    public CheckingAccount addCheckingAccount(double checkingX){
        double thisBalance = 0;
        double thisChecking = 0;
        double thisSaving = 0;
        thisChecking = this.getCheckingBalance();
        thisSaving = this.getSavingsBalance();
        double checkAdd = checkingX;
        thisBalance = thisChecking + thisSaving + checkAdd;
        if (thisBalance > 250000){
            System.out.println("Unable to Complete Action, Accounts Exceed Balance Limit.");
            return checking[checking.length - 1];
        } else {
            checking[checking.length - 1] = new CheckingAccount(checkingX);
            checking[checking.length - 1].setAccountNumber(MeritBank.getNextAccountNumber());
            CheckingAccount arrayTemp[] = new CheckingAccount[checking.length + 1];
            for(int x = 0; x < checking.length; x++) {
            	arrayTemp[x] = checking[x];
            }
            checking = arrayTemp;
            return checking[checking.length - 2];
        }
    }

    public CheckingAccount addCheckingAccount(CheckingAccount checkingAccountX){
        double thisBalance = 0;
        double thisChecking = 0;
        double thisSaving = 0;
        thisChecking = this.getCheckingBalance();
        thisSaving = this.getSavingsBalance();
        double checkAdd = checkingAccountX.getBalance();
        thisBalance = thisChecking + thisSaving + checkAdd;
        if (thisBalance > 250000){
            System.out.println("Unable to Complete Action, Accounts Exceed Balance Limit.");
            return checking[checking.length - 1];
        } else {
            checking[checking.length - 1] = checkingAccountX;
            checking[checking.length - 1].setAccountNumber(MeritBank.getNextAccountNumber());
            CheckingAccount arrayTemp[] = new CheckingAccount[checking.length + 1];
            for(int x = 0; x < checking.length; x++) {
            	arrayTemp[x] = checking[x];
            }
            checking = arrayTemp;
            return checking[checking.length - 2];
        }
    }

    public CheckingAccount[] getCheckingAccounts(){
        return this.checking;
    }

    public int getNumberOfCheckingAccounts(){
        return this.checking.length - 1;
    }

    public double getCheckingBalance(){
        double thisBalance = 0;
        for(int x = 0; x < checking.length - 1; x++){
            thisBalance += checking[x].getBalance();
        }
        return thisBalance;
    }

    public SavingsAccount addSavingsAccount(double openingBalance){
        double thisBalance = 0;
        double thisChecking = 0;
        double thisSaving = 0;
        thisChecking = this.getCheckingBalance();
        thisSaving = this.getSavingsBalance();
        double checkAdd = openingBalance;
        thisBalance = thisChecking + thisSaving + checkAdd;
        if (thisBalance > 250000){
            System.out.println("Unable to Complete Action, Accounts Exceed Balance Limit.");
            return savings[savings.length - 1];
        } else {
            savings[savings.length - 1] = new SavingsAccount(openingBalance);
            savings[savings.length - 1].setAccountNumber(MeritBank.getNextAccountNumber());
            SavingsAccount arrayTemp[] = new SavingsAccount[savings.length + 1];
            for(int x = 0; x < savings.length; x++) {
            	arrayTemp[x] = savings[x];
            }
            savings = arrayTemp;
            return savings[savings.length - 2];
        }
    }

    public SavingsAccount addSavingsAccount(SavingsAccount savingsAccountX){
        double thisBalance = 0;
        double thisChecking = 0;
        double thisSaving = 0;
        thisChecking = this.getCheckingBalance();
        thisSaving = this.getSavingsBalance();
        double checkAdd = savingsAccountX.getBalance();
        thisBalance = thisChecking + thisSaving + checkAdd;
        if (thisBalance > 250000){
            System.out.println("Unable to Complete Action, Accounts Exceed Balance Limit.");
            return savings[savings.length - 1];
        } else {
        	savings[savings.length - 1] = savingsAccountX;
        	savings[savings.length - 1].setAccountNumber(MeritBank.getNextAccountNumber());
            SavingsAccount arrayTemp[] = new SavingsAccount[savings.length + 1];
            for(int x = 0; x < savings.length; x++) {
            	arrayTemp[x] = savings[x];
            }
            savings = arrayTemp;
            return savings[savings.length - 2];
        }
    }

    public SavingsAccount[] getSavingsAccounts(){
        return this.savings;
    }

    public int getNumberOfSavingsAccounts(){
        return this.savings.length - 1;
    }

    public double getSavingsBalance(){
        double thisBalance = 0;
        for(int x = 0; x < savings.length - 1; x++){
            thisBalance += savings[x].getBalance();
        }
        return thisBalance;
    }

    public CDAccount addCDAccount(CDOffering offering, double openingBalance){
        cdAccount[cdAccount.length - 1] = new CDAccount(offering, openingBalance);
        cdAccount[cdAccount.length - 1].setAccountNumber(MeritBank.getNextAccountNumber());
        CDAccount arrayTemp[] = new CDAccount[cdAccount.length + 1];
        for(int x = 0; x < cdAccount.length; x++) {
        	arrayTemp[x] = cdAccount[x];
        }
        cdAccount = arrayTemp;
        return cdAccount[cdAccount.length - 2];
    }

    public CDAccount addCDAccount(CDAccount cdAccountX) {
        if(cdAccount.equals(null)){
            System.out.println("Unable to Complete Action, Null CD Offer.");
            return null;
        }
        cdAccount[cdAccount.length - 1] = cdAccountX;
        cdAccount[cdAccount.length - 1].setAccountNumber(MeritBank.getNextAccountNumber());
        CDAccount arrayTemp[] = new CDAccount[cdAccount.length + 1];
        for(int x = 0; x < cdAccount.length; x++) {
        	arrayTemp[x] = cdAccount[x];
        }
        cdAccount = arrayTemp;
        return this.cdAccount[cdAccount.length - 2];
    }

    public CDAccount[] getCDAccounts(){
        return this.cdAccount;
    }

    public int getNumberOfCDAccounts(){
        return this.cdAccount.length - 1;
    }

    public double getCDBalance(){
        double thisBalance = 0;
        for(int x = 0; x < this.cdAccount.length - 1; x++) {
            thisBalance += cdAccount[x].getBalance();
        }
        return thisBalance;
    }

    public double getCombinedBalance(){
        double thisBalance;
        thisBalance = this.getCheckingBalance();
        thisBalance += this.getSavingsBalance();
        thisBalance += this.getCDBalance();
        return thisBalance;
    }

    public String toString(){
    	//// "##.00" place holder, *Read :https://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html
        DecimalFormat format = new DecimalFormat("##.00");      
        return "Name: " + this.firstName + " " + this.middleName + " " + this.lastName + "\n"
                + "SSN: " + this.ssn + "\n";
    }
    //implementing compareTo()  
    @Override
    public int compareTo(AccountHolder otherAccount) {
    	if(this.getCombinedBalance() > otherAccount.getCombinedBalance()) {
    		return 1; //getCombinedBalance() > otherAccount.getCombinedBalance()
    	} else
    		return -1; //otherAccount.getCombinedBalance() > getCombinedBalance()
    }
    //overriding to String()
    public String writeToString() {
    	return this.lastName + "," + this.middleName + "," + this.firstName + ","
    			+ this.ssn;
    }
   
    //throw a java.lang.Exception
    static AccountHolder readFromString(String accountData) {
    	String dataArray1[] = accountData.split(",");
    	try {
    	AccountHolder ah1 = new AccountHolder(dataArray1[0], dataArray1[1]
    			, dataArray1[2], dataArray1[3]);
    	return ah1;
    	} catch (Exception e) {
    		System.out.println("Parsing Error!");
    		return null;
    	}
    }

} 
