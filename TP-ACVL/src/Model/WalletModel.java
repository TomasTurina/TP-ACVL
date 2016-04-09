package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import View.SharesObserver;
import View.BalanceObserver;

public class WalletModel implements WalletModelInterface, Runnable {
	ArrayList<SharesObserver> sharesObservers = new ArrayList<SharesObserver>(); 
	ArrayList<BalanceObserver> balanceObservers = new ArrayList<BalanceObserver>();  

    ArrayList<Operation> boughtShares = new ArrayList<Operation>();
	ArrayList<Operation> soldShares = new ArrayList<Operation>();
	ArrayList<Share> marketShares = new ArrayList<Share>();
	
    int balance;
    int percentage;
	int counter=600;
    Thread thread;

    public WalletModel() 
	{
    	readFile();
		thread =new Thread (this);
		thread.start();
	}
    
    public void run() {
		for(;;)
		{
			try {
				Thread.sleep(50);
				try {
					notifyBalanceObservers();
					counter--;
					if(counter==0) {
						notifySharesObservers();
						counter=600;
					}
				}
				catch(NullPointerException e){};
			} 
			catch (InterruptedException e1) {e1.printStackTrace();}
		}
	}
    
    public void setBalance(int balance) {
		this.balance = balance;
    }
  
	public int getBalance() {
		return balance;
	}
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getPercentage() {
		return percentage;
	}
  
	public void addBoughtShare(Operation op) {
		boughtShares.add(op);
	}

	public ArrayList<Operation> getBoughtShares() {
		return boughtShares;
	}
	
	public void removeBoughtShare(Operation op) {
		boughtShares.remove(op);
	}

	public void addSoldShare(Operation op) {
		soldShares.add(op);
	}

	public ArrayList<Operation> getSoldShares() { 
		return soldShares;
	}
	
	public void removeSoldShare(Operation op) {
		soldShares.remove(op);
	}
	
	public void addMarketShare(Share a) {
		marketShares.add(a);
	}

	public ArrayList<Share> getMarketShares() {
		return marketShares;
	}
	
	public void registerObserver(BalanceObserver o) {
		balanceObservers.add(o);
	}
	
	public void removeObserver(BalanceObserver o) {
		int i = balanceObservers.indexOf(o);
		if (i >= 0) {
			balanceObservers.remove(i);
		}
	}
	
	public void notifyBalanceObservers() {
		for(int i = 0; i < balanceObservers.size(); i++) {
			BalanceObserver observer = (BalanceObserver)balanceObservers.get(i);
			observer.updateBalance();
		}
	}
	
	public void registerObserver(SharesObserver o) {
		sharesObservers.add(o);
	}

	public void removeObserver(SharesObserver o) {
		int i = sharesObservers.indexOf(o);
		if (i >= 0) {
			sharesObservers.remove(i);
		}
	}
	
	public void notifySharesObservers() {
		for(int i = 0; i < sharesObservers.size(); i++) {
			SharesObserver observer = (SharesObserver)sharesObservers.get(i);
			observer.updateShares();
		}
	}
	
	public void readFile() {
	    	String filename = "bourse_data.txt";
	    	String filepath = "/home/tomas/workspace/TP-ACVL/src/read_file/" + filename;
	    	String company;
	    	float price;
	    	float variation;
	    	Scanner sc = null;
	    	
	    	try {
	    		sc = new Scanner(new File(filepath));
	    	}
	    	catch(FileNotFoundException e) {
	    		JOptionPane.showMessageDialog(null, "File not found");
	    		System.exit(1);
	    	}
	    	
	    	while(sc.hasNext()) {
	    		String line = sc.nextLine();      		
	   		    String[] words = line.split(" ");
	   		    company = words[0];
	   		    price = Float.parseFloat(words[1]);
	   		    variation = Float.parseFloat(words[2]);
	   		    if(variation < -10 || variation > 10)
	   		    	throw new NumberFormatException();
	   		    
	   		    addMarketShare(new Share(company,price,variation));
	    	}
	}
}
