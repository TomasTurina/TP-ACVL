package Model;

import java.util.ArrayList;

import View.SharesObserver;
import View.BalanceObserver;
  
public interface WalletModelInterface {
	void setBalance(int balance);
	int getBalance();
	void setPercentage(int percentage);
	int getPercentage();
	void addBoughtShare(Operation op);
	ArrayList<Operation> getBoughtShares();
	void removeBoughtShare(Operation op);
	void addSoldShare(Operation op);
	ArrayList<Operation> getSoldShares();
	void removeSoldShare(Operation op);
	void addMarketShare(Share a);
	ArrayList<Share> getMarketShares();
	
	void registerObserver(BalanceObserver o);
	void removeObserver(BalanceObserver o); 
	void registerObserver(SharesObserver o);
	void removeObserver(SharesObserver o);
}
