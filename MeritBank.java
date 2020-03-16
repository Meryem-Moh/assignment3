package com.meritamerica.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;



public class MeritBank {
    static AccountHolder accountHolder[] = new AccountHolder[1];
    static CDOffering cdOfferingHolder[] = new CDOffering[1];
    static long lastAccountNumber = 0;

    static void addAccountHolder(AccountHolder accountHolderX){
        accountHolder[accountHolder.length - 1] = accountHolderX;
        AccountHolder arrayTemp[] = new AccountHolder[accountHolder.length + 1];
        for(int i = 0; i < accountHolder.length; i++) {
        	arrayTemp[i] = accountHolder[i];
        }
        accountHolder = arrayTemp;
    }

    static AccountHolder[] getAccountHolders() {
        return accountHolder;
    }

    static CDOffering[] getCDOfferings() {
        return cdOfferingHolder;
    }
    //Max
    static CDOffering getBestCDOffering(double depositAmount){
        int best = 0;
        double tv;
        double fv;
        if (cdOfferingHolder.length < 2){
            System.out.println("Unable to complete action. ");
            return cdOfferingHolder[0];
        }
        for(int i = 0; i < cdOfferingHolder.length; i++){
            double pv = depositAmount;
            tv = pv * (Math.pow((1 + cdOfferingHolder[i].getInterestRate()), cdOfferingHolder[i].getTerm()));
            fv = pv * (Math.pow((1 + cdOfferingHolder[best].getInterestRate()), cdOfferingHolder[best].getTerm()));
            if (tv > fv){
                best = i;
            }
        }
        return cdOfferingHolder[best];
    }

    static CDOffering getSecondBestCDOffering(double depositAmount){
        int best = 0;
        int second = 0;
        double tv;
        double fv;
        double sv;
        if (cdOfferingHolder.length < 2){
            System.out.println("Unable to complete action.");
            return null;
        }
        for(int i = 0; i < cdOfferingHolder.length; i++){
            double pv = depositAmount;
            tv = pv * (Math.pow((1 + cdOfferingHolder[i].getInterestRate()), cdOfferingHolder[i].getTerm()));
            fv = pv * (Math.pow((1 + cdOfferingHolder[best].getInterestRate()), cdOfferingHolder[best].getTerm()));
            sv = pv * (Math.pow((1 + cdOfferingHolder[second].getInterestRate()), cdOfferingHolder[second].getTerm()));
            if (tv > fv){
                second = best;
                best = i;
            }
            else if(tv > sv && tv != fv){
                second = i;
            }
        }
        return cdOfferingHolder[second];
    }

    static void clearCDOfferings(){
        CDOffering newArr[] = null;
        cdOfferingHolder = newArr;
    }

    static void setCDOfferings(CDOffering[] offerings){
        cdOfferingHolder = offerings;
    }

    static double totalBalances(){
        double totalBalance = 0;
        for(int i = 0; i < accountHolder.length - 1; i++){
            totalBalance += accountHolder[i].getCombinedBalance();
        }
        return totalBalance;
    }

    static long getNextAccountNumber(){
    
    	return MeritBank.lastAccountNumber;
    }
    
    static void setNextAccountNumber(long nextAccountNumber) {
    	MeritBank.lastAccountNumber = nextAccountNumber;
    }
    
    static AccountHolder[] sortAccountHolders() {
    	Arrays.sort(accountHolder, 0, accountHolder.length - 1);
    	return accountHolder;
    }
    
    static boolean readFromFile(String fileName) {
    	
    	MeritBank.accountHolder = new AccountHolder[1];
    	
    	try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bf = new BufferedReader( fileReader );
			
			String line;
			
			if((line = bf.readLine()) != null)
				MeritBank.setNextAccountNumber(Long.parseLong(line));
		//CDOffering
			if((line = bf.readLine()) != null) {
				CDOffering tempCDArray[] = new CDOffering[Integer.parseInt(line)];
				for(int x = 0; x < tempCDArray.length; x++) {
					if((line = bf.readLine()) != null)
					tempCDArray[x] = CDOffering.readFromString(line);
				}
				MeritBank.setCDOfferings(tempCDArray);
			}
		//AccountHolder
			if((line = bf.readLine()) != null) {
				AccountHolder tempHolderArray[] = new AccountHolder[Integer.parseInt(line)];
				for(int x = 0; x < tempHolderArray.length; x++) {
					if((line = bf.readLine()) != null) {
						tempHolderArray[x] = AccountHolder.readFromString(line);
					}
		//addCheckingAccount
					if((line = bf.readLine()) != null) {
						int checkingNum = Integer.parseInt(line);
						for(int y = 0; y < checkingNum; y++) {
							if((line = bf.readLine()) != null)
								tempHolderArray[x].addCheckingAccount(CheckingAccount.readFromString(line));
						}
		//addSavingsAccount
					}
					if((line = bf.readLine()) != null) {
						int savingsNum = Integer.parseInt(line);
						for(int y = 0; y < savingsNum; y++) {
							if((line = bf.readLine()) != null)
								tempHolderArray[x].addSavingsAccount(SavingsAccount.readFromString(line));
						}
					}
		//addCDAccount
					if((line = bf.readLine()) != null) {
						int cdAccountNum = Integer.parseInt(line);
						for(int y = 0; y < cdAccountNum; y++) {
							if((line = bf.readLine()) != null)
								tempHolderArray[x].addCDAccount(CDAccount.readFromString(line));
						}
					}
				}
				for(int x = 0; x < tempHolderArray.length; x++) {
					MeritBank.addAccountHolder(tempHolderArray[x]);
				}
			}
			 fileReader.close();
			
			return true;
		} catch (IOException e) {
			System.out.println("ERROR!");
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
    }
    
    static String writeToFile() {
    	String offeringList = "";
    	for(int x = 0; x < cdOfferingHolder.length; x++) {
    		offeringList += cdOfferingHolder[x].writeToString() + "\n";
    	}
    	String accountList = "";
    	accountList += accountHolder.length - 1 + "\n";
    	for (int x = 0; x < accountHolder.length - 1; x++) {
    		accountList += accountHolder[x].writeToString() + "\n";
    		
    		CheckingAccount arrayCheck[] = accountHolder[x].getCheckingAccounts();
    		
    		accountList += accountHolder[x].getNumberOfCheckingAccounts() + "\n";
    		
    		for(int y = 0; y < arrayCheck.length - 1; y++) {
    			accountList += arrayCheck[y].writeToString() + "\n";
    		}
    		
    		SavingsAccount arraySave[] = accountHolder[x].getSavingsAccounts();
    		
    		accountList += accountHolder[x].getNumberOfSavingsAccounts() + "\n";
    		
    		for(int y = 0; y < arraySave.length - 1; y++) {
    			accountList += arraySave[y].writeToString() + "\n";
    		}
    		
    		CDAccount arrayCD[] = accountHolder[x].getCDAccounts();
    		
    		accountList += accountHolder[x].getNumberOfCDAccounts() + "\n";
    		
    		for(int y = 0; y < arrayCD.length - 1; y++) {
    			accountList += arrayCD[y].writeToString() + "\n";
    		}
    	}
    	String str= MeritBank.getNextAccountNumber() + "\n"
    			+ cdOfferingHolder.length + "\n"
    			+ offeringList
    			+ accountList;
    			
    	
    	return str;
    }

}
