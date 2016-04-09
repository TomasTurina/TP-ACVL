package Controller;

import Model.Share;
import Model.WalletModelInterface;
import Model.Operation;
import View.WalletView;
  
public class WalletController implements WalletControllerInterface {
	WalletModelInterface model;
	WalletView view;
   
	public WalletController(WalletModelInterface model) {
		this.model = model;
		view = new WalletView(this, model);
        view.startView();	
	}
  
 	public void setStart(int balance,int percentage) {
 		model.setBalance(balance);
 		model.setPercentage(percentage);
	}

 	public void deposit(int amount) {
 		model.setBalance(model.getBalance()+amount);
	}

	public void withdraw(int amount) {
		model.setBalance(model.getBalance()-amount);
	}	
	
	public float sell(int quantity,String company) {
		for(Operation c : model.getBoughtShares()) {
			if(c.getShare().getCompany()==company) {
				if(c.getQuantity()>quantity) {
					float cost = (int)((quantity*c.getShare().getPrice()-(quantity*c.getShare().getPrice()*((float)model.getPercentage()/100)))*100);
		        	cost=(cost/100);
		        	int newBalance = model.getBalance()+(int)cost;
		        	model.setBalance(newBalance);
					c.setQuantity(c.getQuantity()-quantity);					
					return cost;
				}
				else if(c.getQuantity()==quantity) {
					float cost = (int)((quantity*c.getShare().getPrice()-(quantity*c.getShare().getPrice()*((float)model.getPercentage()/100)))*100);
		        	cost=(cost/100);
		        	int newBalance = model.getBalance()+(int)cost;
		        	model.setBalance(newBalance);
					model.removeBoughtShare(c);
					return cost;
				}
				else {
					return 0;
				}
			}
		}
		return 0;
	}
	
	public float get(int quantity,String company) {
		for(Operation c : model.getSoldShares()) {
			if(c.getShare().getCompany().equals(company)) {
				if(c.getQuantity()>quantity) {
					float cost = (int)((quantity*c.getShare().getPrice()+(quantity*c.getShare().getPrice()*((float)model.getPercentage()/100)))*100);
		        	cost=(cost/100);
		        	int newBalance = model.getBalance()-(int)cost;
		        	if(newBalance>=0) {
		        		model.setBalance(newBalance);
		        		c.setQuantity(c.getQuantity()-quantity);					
		        		return cost;
		        	}
				}
				else if(c.getQuantity()==quantity) {
					float cost = (int)((quantity*c.getShare().getPrice()+(quantity*c.getShare().getPrice()*((float)model.getPercentage()/100)))*100);
		        	cost=(cost/100);
		        	int newBalance = model.getBalance()-(int)cost;
		        	if(newBalance>=0) {
		        		model.setBalance(newBalance);
		        		model.removeSoldShare(c);
		        		return cost;
		        	}
				}
				else {
					return 0;
				}
			}
		}
		return 0;
	}
	
	public float buy(int quantity,String company) {
		for(Share a : model.getMarketShares()) {
			if(a.getCompany().equals(company)) {
				float total = (int)((quantity*a.getPrice()+(quantity*a.getPrice()*((float)model.getPercentage()/100)))*100);
	        	total=(total/100);
	        	int newBalance = model.getBalance()-(int)total;
	        	if(newBalance>=0) {
	        		for(Operation c : model.getBoughtShares()) {
	        			if(c.getShare().getCompany().equals(company)) {
	        				if(c.getShare().getPrice()==c.getInitialPrice()) {
	        					c.setQuantity(c.getQuantity()+quantity);
		        				model.setBalance(newBalance);
		        				return total;
	        				}
	        			}		
	        		}	
	        		float cost = (int)((quantity*a.getPrice()*((float)model.getPercentage()/100))*100);
	        		cost=(cost/100);
	        		Operation op = new Operation(a,a.getPrice(),quantity,cost);
	        		model.addBoughtShare(op);
	        		model.setBalance(newBalance);					
	        		return total;
	        	}
			}
		}
		return 0;
	}
	
	public float shortSale(int quantity,String company) {
		for(Share a : model.getMarketShares()) {
			if(a.getCompany().equals(company)) {
				float total = (int)((quantity*a.getPrice()-(quantity*a.getPrice()*((float)model.getPercentage()/100)))*100);
	        	total=(total/100);
	        	int newBalance = model.getBalance()+(int)total;
	        	for(Operation c : model.getSoldShares()) {
	        		if(c.getShare().getCompany().equals(company)) {
	        			if(c.getShare().getPrice()==c.getInitialPrice()) {
	        				c.setQuantity(c.getQuantity()+quantity);
	        				model.setBalance(newBalance);
	        				return total;
	        			}
	        		}		
	        	}	
	        	float cost = (int)((quantity*a.getPrice()*((float)model.getPercentage()/100))*100);
	        	cost=(cost/100);
	        	Operation op = new Operation(a,a.getPrice(),quantity,cost);
	        	model.addSoldShare(op);
	        	model.setBalance(newBalance);					
	        	return total;
			}
		}
		return 0;
	}
}
