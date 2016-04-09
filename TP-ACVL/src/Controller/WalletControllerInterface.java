package Controller;
  
public interface WalletControllerInterface {
 	void setStart(int balance,int percentage);
 	void deposit(int amount);
 	void withdraw(int amount);
 	float sell(int quantity, String company);
 	float get(int quantity, String company);
 	float buy(int quantity, String company);
 	float shortSale(int quantity, String company);
}
