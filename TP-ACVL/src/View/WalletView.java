package View;
    
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import Controller.WalletControllerInterface;
import Model.Share;
import Model.WalletModelInterface;
import Model.Operation;

public class WalletView implements ActionListener, BalanceObserver, SharesObserver {
	WalletModelInterface model;
	WalletControllerInterface controller;
	
    JFrame startFrame;
    JPanel startPanel;
    JTextField balanceText;
    JTextField percentageText;
    JButton createButton;
    
    JFrame walletFrame;
    JPanel walletPanel;
    JLabel balanceLabel;
    
    JFrame accountFrame;
    JPanel accountPanel;
    JTextField amountText;
    JButton depositButton;
    JButton withdrawButton;
    boolean flag=true;
    
    JFrame sharesFrame;
    JPanel sharesPanel;
    JButton buySharesButton;
    JButton sellSharesButton;
    boolean flag2=true;
    
    JFrame marketFrame;
    JPanel marketPanel;
    
    JFrame operationFrame;
    JPanel operationPanel;
    JTextField companyText;
    JTextField quantityText;
    JLabel companyLabel;
    JButton buyButton;
    JButton sellButton;
    JButton buyShareButton;
    JButton sellShareButton;
    boolean flag3=true;
    
    public WalletView(WalletControllerInterface controller, WalletModelInterface model) {	
		this.controller = controller;
		this.model = model;
		model.registerObserver((BalanceObserver)this);
		model.registerObserver((SharesObserver)this);
    }
    
    public void startView() {
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	startPanel = new JPanel(new GridLayout(2, 1));
        startFrame = new JFrame("Build wallet");              
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(new Dimension(100, 80));       
        
        JLabel outputLabel = new JLabel("Initial balance", SwingConstants.CENTER);
        balanceText = new JTextField(2);
        JLabel outputLabel2 = new JLabel("Taxes (%)", SwingConstants.CENTER);
        percentageText = new JTextField(2);
        createButton = new JButton("Create");
        createButton.addActionListener(this);
        
        JPanel balancePanel = new JPanel(new GridLayout(2, 2));
        balancePanel.add(outputLabel);
        balancePanel.add(balanceText);
        balancePanel.add(outputLabel2);
        balancePanel.add(percentageText);
        JPanel okPanel = new JPanel(new GridLayout(1, 1));
        okPanel.add(createButton);        
        startPanel.add(balancePanel);
        startPanel.add(okPanel);
        startFrame.getContentPane().add(startPanel, BorderLayout.CENTER);
              
        JMenuBar select = new JMenuBar();
        JMenu menu = new JMenu("Options");           
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        menu.add(exit);        
        select.add(menu);
        
        startFrame.setJMenuBar(select);
        startFrame.pack();
        startFrame.setLocation(250,200);
        startFrame.setVisible(true);
	}
    
    public void walletView() {
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	walletPanel = new JPanel(new GridLayout(1, 1));
        walletFrame = new JFrame("Wallet");              
        walletFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        walletFrame.setSize(new Dimension(100, 80));       
        
        JLabel outputLabel = new JLabel("Balance", SwingConstants.CENTER);
        balanceLabel = new JLabel("", SwingConstants.CENTER);
        balanceLabel.setText(""+model.getBalance()); 
        JLabel outputLabel2 = new JLabel("Taxes (%)", SwingConstants.CENTER);
        JLabel numLabel2 = new JLabel("", SwingConstants.CENTER);
        numLabel2.setText(""+model.getPercentage()); 
        
        JPanel balancePanel = new JPanel(new GridLayout(2, 2));
        balancePanel.add(outputLabel);
        balancePanel.add(balanceLabel);
        balancePanel.add(outputLabel2);
        balancePanel.add(numLabel2);
        
        walletPanel.add(balancePanel);
        walletFrame.getContentPane().add(walletPanel, BorderLayout.CENTER);
              
        JMenuBar select = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenu menu2 = new JMenu("Shares");
        
        JMenuItem deposit = new JMenuItem("Deposit");      
        deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(flag==true)
				{
					flag=false;
					accountView("Deposit",true);
				}
			}
        });
        menu.add(deposit); 
        
        JMenuItem withdraw = new JMenuItem("Withdraw");      
        withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(flag==true)
				{
					flag=false;
					accountView("Withdraw",false);
				}
			}
        });
        menu.add(withdraw); 
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        menu.add(exit);
        
        JMenuItem boughtShares = new JMenuItem("Bought shares");      
        boughtShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(flag2==true)
				{
					flag2=false;
					sharesView("Bought shares",true);
				}
			}
        });
        menu2.add(boughtShares); 
        
        JMenuItem shortSaleShares = new JMenuItem("Short sale shares");      
        shortSaleShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(flag2==true)
				{
					flag2=false;
					sharesView("Short sale shares",false);
				}
			}
        });
        menu2.add(shortSaleShares); 
        
        select.add(menu);
        select.add(menu2);
        
        walletFrame.setJMenuBar(select);
        walletFrame.pack();
        walletFrame.setLocation(250,200);
        walletFrame.setVisible(true);
	}
  
    public void accountView(String s, boolean b) {
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	accountPanel = new JPanel(new GridLayout(2, 1));
        accountFrame = new JFrame(s);              
        accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accountFrame.setSize(new Dimension(100, 80));       
        
        JLabel outputLabel = new JLabel("Amount", SwingConstants.CENTER);
        amountText = new JTextField(2);
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        
        JPanel balancePanel = new JPanel(new GridLayout(1, 2));
        balancePanel.add(outputLabel);
        balancePanel.add(amountText);
        JPanel okPanel = new JPanel(new GridLayout(1, 1));
        if(b)
        okPanel.add(depositButton);
        else
        okPanel.add(withdrawButton);
        
        JMenuBar select = new JMenuBar();
        JMenu menu = new JMenu("Options");    
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	accountFrame.dispose();
				flag=true;
            }
        });
        menu.add(close);        
        select.add(menu);
        
        accountFrame.setJMenuBar(select);        
        accountPanel.add(balancePanel);
        accountPanel.add(okPanel);
        accountFrame.getContentPane().add(accountPanel, BorderLayout.CENTER);
        accountFrame.pack();
        accountFrame.setLocation(450,200);
        accountFrame.setVisible(true);
    }
    
    public void sharesView(final String s, final boolean b) {
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	sharesPanel = new JPanel(new GridLayout(1, 1));
        sharesFrame = new JFrame(s);              
        sharesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sharesFrame.setSize(new Dimension(100, 80));       
        
        JLabel outputLabel = new JLabel("Shares", SwingConstants.CENTER);
        JLabel outputLabel2 = new JLabel("Unitary price", SwingConstants.CENTER);
        JLabel outputLabel3 = new JLabel("Quantity", SwingConstants.CENTER);
        JLabel outputLabel4 = new JLabel("Cost", SwingConstants.CENTER);
        JLabel outputLabel5 = new JLabel("Initial value", SwingConstants.CENTER);
        JLabel outputLabel6 = new JLabel("% Var price", SwingConstants.CENTER);
        JLabel outputLabel7 = new JLabel("Actual value", SwingConstants.CENTER);
        JLabel outputLabel8 = new JLabel("Gain", SwingConstants.CENTER);
        JLabel outputLabel9 = new JLabel("Option", SwingConstants.CENTER);
        
        JPanel companyPanel;
        if(b)
        companyPanel = new JPanel(new GridLayout(model.getBoughtShares().size()+1, 9));
        else
        companyPanel = new JPanel(new GridLayout(model.getSoldShares().size()+1, 9));
		
        companyPanel.add(outputLabel);
        companyPanel.add(outputLabel2);
        companyPanel.add(outputLabel3);
        companyPanel.add(outputLabel4);
        companyPanel.add(outputLabel5);
        companyPanel.add(outputLabel6);
        companyPanel.add(outputLabel7);
        companyPanel.add(outputLabel8);
        companyPanel.add(outputLabel9);
        
        if(b)
        addShares(companyPanel,model.getBoughtShares(),true);
        else
        addShares(companyPanel,model.getSoldShares(),false);
        
        JMenuBar select = new JMenuBar();
        JMenu menu = new JMenu("Options");           
        JMenuItem buy = new JMenuItem("Buy");
        buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(flag3==true) {
					flag3=false;
    				operationView("Buy",true,true);
				}
			}
        });
        JMenuItem shortSale = new JMenuItem("Short sale");
        shortSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(flag3==true) {
					flag3=false;
    				operationView("Short sale",true,false);
				}
			}
        });
        JMenuItem update = new JMenuItem("Update");      
        update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sharesFrame.dispose(); 
				sharesView(s,b);
			}
        });
        JMenuItem close = new JMenuItem("Close");      
        close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sharesFrame.dispose();
				flag2=true;
			}
        });
        if(b)
        menu.add(buy);
        else
        menu.add(shortSale);
        
        menu.add(update);
        menu.add(close);
              
        select.add(menu);        
        sharesFrame.setJMenuBar(select);  
        sharesPanel.add(companyPanel);
        sharesFrame.getContentPane().add(sharesPanel, BorderLayout.CENTER);
        sharesFrame.pack();
        sharesFrame.setLocation(100,300);
        sharesFrame.setVisible(true);
    }
    
    public void marketView() {
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	marketPanel = new JPanel(new GridLayout(1, 1));
        marketFrame = new JFrame("Market");              
        marketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marketFrame.setSize(new Dimension(100, 80));               
        
        JPanel companyPanel = new JPanel(new GridLayout(model.getMarketShares().size()+1, 3));
		
		JLabel outputLabel = new JLabel("Company", SwingConstants.CENTER);
        JLabel outputLabel2 = new JLabel("Unitary price", SwingConstants.CENTER);
        JLabel outputLabel3 = new JLabel("% var", SwingConstants.CENTER);
        companyPanel.add(outputLabel);
        companyPanel.add(outputLabel2);
        companyPanel.add(outputLabel3);
        
        for(Share b : model.getMarketShares()) {
        	JLabel outputLabel4 = new JLabel(b.getCompany(), SwingConstants.CENTER);
            JLabel outputLabel5 = new JLabel(""+b.getPrice(), SwingConstants.CENTER);
            JLabel outputLabel6 = new JLabel(""+b.getVariation(), SwingConstants.CENTER);
            companyPanel.add(outputLabel4);
            companyPanel.add(outputLabel5);
            companyPanel.add(outputLabel6);
		}
        
        marketPanel.add(companyPanel);
        marketFrame.getContentPane().add(marketPanel, BorderLayout.CENTER);
        marketFrame.pack();
        marketFrame.setLocation(990,15);
        marketFrame.setVisible(true);
    }
    
    public void operationView(String s, boolean b, boolean c) {
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	operationPanel = new JPanel(new GridLayout(2, 1));
        operationFrame = new JFrame(s);              
        operationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        operationFrame.setSize(new Dimension(100, 80));       
        
        JLabel outputLabel = new JLabel("Company", SwingConstants.CENTER);
        companyText = new JTextField(2);
        companyLabel = new JLabel("", SwingConstants.CENTER);
        JLabel outputLabel3 = new JLabel("Quantity", SwingConstants.CENTER);
        quantityText = new JTextField(2);
        
        buyButton = new JButton("Buy");
        buyButton.addActionListener(this);
        sellButton = new JButton("Sell");
        sellButton.addActionListener(this);
        buyShareButton = new JButton("Buy share");
        buyShareButton.addActionListener(this);
        sellShareButton = new JButton("Sell share");
        sellShareButton.addActionListener(this);
        
        JPanel balancePanel = new JPanel(new GridLayout(2, 2));
        
        balancePanel.add(outputLabel); 
        if(b)
        	balancePanel.add(companyText);
        else
        	balancePanel.add(companyLabel);
        balancePanel.add(outputLabel3);
        balancePanel.add(quantityText);
        
        JPanel okPanel = new JPanel(new GridLayout(1, 1));
        if(!b && !c)
        	okPanel.add(buyButton);
        else if(!b && c)
        	okPanel.add(sellButton);
        else if(b && c)
        	okPanel.add(buyShareButton);
        else
        	okPanel.add(sellShareButton);
        
        JMenuBar select = new JMenuBar();
        JMenu menu = new JMenu("Options");    
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	operationFrame.dispose();
				flag3=true;
            }
        });
        menu.add(close);        
        select.add(menu);
        
        operationFrame.setJMenuBar(select);        
        operationPanel.add(balancePanel);
        operationPanel.add(okPanel);
        operationFrame.getContentPane().add(operationPanel, BorderLayout.CENTER);
        operationFrame.pack();
        operationFrame.setLocation(450,200);
        operationFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
		if (event.getSource() == createButton) {
			try
			{
				int balance;
				balance = Integer.parseInt(balanceText.getText());
				int percentage;
				percentage = Integer.parseInt(percentageText.getText());
				if(balance>0 && percentage>0 && percentage<=50)
				{
					controller.setStart(balance,percentage);
					startFrame.dispose();
					walletView();
					marketView();
				}
				else
			    JOptionPane.showMessageDialog(null, "Wrong input");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong input");
			};
		} 
		
		if (event.getSource() == depositButton) {
			try
			{
				int amount;
				amount = Integer.parseInt(amountText.getText());
				if(amount>0)
				{
					controller.deposit(amount);
					accountFrame.dispose();
					flag=true;
				}
				else
			    JOptionPane.showMessageDialog(null, "Wrong amount");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong amount");
			};
		} 
		
		if (event.getSource() == withdrawButton) {
			try
			{
				int amount;
				amount = Integer.parseInt(amountText.getText());
				if(amount>0 && amount<=model.getBalance())
				{
					controller.withdraw(amount);
					accountFrame.dispose();
					flag=true;
				}
				else
			    JOptionPane.showMessageDialog(null, "Wrong amount");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong amount");
			};
		} 
		
		if (event.getSource() == buyButton) {
			try
			{
				int c = Integer.parseInt(quantityText.getText());
				String s = companyLabel.getText();
				float ok;
				if(c>0) {
					ok = controller.get(c,s);
					if(ok!=0) {
						JOptionPane.showMessageDialog(null, "Operation successful\nPrice(with taxes): "+ok);
						operationFrame.dispose();
						flag3=true;
					}
					else 
						JOptionPane.showMessageDialog(null, "Operation unsuccessful");
				}
				else
					JOptionPane.showMessageDialog(null, "Wrong quantity");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong quantity");
			};
		}  
		
		if (event.getSource() == sellButton) {
			try
			{
				int c = Integer.parseInt(quantityText.getText());
				String s = companyLabel.getText();
				float ok;
				if(c>0) {
					ok = controller.sell(c,s);
					if(ok!=0) {
						JOptionPane.showMessageDialog(null, "Operation successful\nPrice(with taxes): "+ok);
						operationFrame.dispose();
						flag3=true;
					}
					else 
						JOptionPane.showMessageDialog(null, "Operation unsuccessful");
				}
				else
					JOptionPane.showMessageDialog(null, "Wrong quantity");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong quantity");
			};
		} 
				
		if (event.getSource() == buyShareButton) {
			try
			{
				int c = Integer.parseInt(quantityText.getText());
				String s = companyText.getText();
				float ok;
				if(c>0) {
					ok = controller.buy(c,s);
					if(ok!=0) {
						JOptionPane.showMessageDialog(null, "Operation successful\nPrice(with taxes): "+ok);
						operationFrame.dispose();
						flag3=true;
					}
					else 
						JOptionPane.showMessageDialog(null, "Operation unsuccessful");
				}
				else
					JOptionPane.showMessageDialog(null, "Wrong quantity");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong company/quantity");
			};
		}  
		
		if (event.getSource() == sellShareButton) {
			try
			{
				int c = Integer.parseInt(quantityText.getText());
				String s = companyText.getText();
				float ok;
				if(c>0) {
					ok = controller.shortSale(c,s);
					if(ok!=0) {
						JOptionPane.showMessageDialog(null, "Operation successful\nPrice(with taxes): "+ok);
						operationFrame.dispose();
						flag3=true;
					}
					else 
						JOptionPane.showMessageDialog(null, "Operation unsuccessful");
				}
				else
					JOptionPane.showMessageDialog(null, "Wrong quantity");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong company/quantity");
			};
		} 
    }
	
	public void updateBalance() {
		balanceLabel.setText(""+model.getBalance()); 
	}

	public void updateShares() {		
		for(Share b : model.getMarketShares()) {
			Random r = new Random();
			float variation = (r.nextInt(2000)-1000);
			variation=(variation/100);
			b.setVariation(variation);
			float update = (int)(b.getPrice()*(variation/100)*100);
			update=(update/100);
			float price = (int)((b.getPrice()+update)*100);
			price=(price/100);
			b.setPrice(price); 
		}
		marketFrame.dispose();
		marketView();
	}
	
	public void addShares(JPanel companyPanel,ArrayList<Operation> operations,boolean b) {
        for(final Operation c : operations) {
        	JLabel outputLabel = new JLabel(c.getShare().getCompany(), SwingConstants.CENTER);
        	JLabel outputLabel1 = new JLabel(""+c.getInitialPrice(), SwingConstants.CENTER);
        	JLabel outputLabel2 = new JLabel(""+c.getQuantity(), SwingConstants.CENTER);
        	JLabel outputLabel3 = new JLabel(""+c.getCost(), SwingConstants.CENTER);
        	float initialValue = (int)((c.getInitialPrice()*c.getQuantity()-c.getCost())*100);
        	initialValue=(initialValue/100);
        	JLabel outputLabel4 = new JLabel(""+initialValue, SwingConstants.CENTER);
        	float priceVariation = (int)((((c.getShare().getPrice()/c.getInitialPrice())-1)*100)*100);
        	priceVariation=(priceVariation/100);
        	JLabel outputLabel5 = new JLabel(""+priceVariation, SwingConstants.CENTER);
        	float actualWithoutCost = c.getShare().getPrice()*c.getQuantity();
        	float actualCost = actualWithoutCost*((float)model.getPercentage()/100);
        	float actualValue = (int)((actualWithoutCost-actualCost)*100);
        	actualValue=(actualValue/100);
        	JLabel outputLabel6 = new JLabel(""+actualValue, SwingConstants.CENTER);
        	JLabel outputLabel7;
        	float gain;
        	if(b)
        	{
        		gain = (int)(((actualValue-initialValue)-c.getCost()-actualCost)*100);
            	gain=(gain/100);
        		outputLabel7 = new JLabel(""+gain, SwingConstants.CENTER);
        	}
        	else
        	{
        		gain = (int)(((initialValue-actualValue)-c.getCost()-actualCost)*100);
            	gain=(gain/100);
        		outputLabel7 = new JLabel(""+gain, SwingConstants.CENTER);	
        	}
        	JButton button;
        	if(b) {
        		button = new JButton("Sell");
        		button.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent event) {
        				if(flag3==true) {
        					flag3=false;
            				operationView("Sell",false,true);
            				companyLabel.setText(c.getShare().getCompany());
        				}
        			}
        		});
        	}
        	else {
        		button = new JButton("Get");
        		button.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent event) {
        				if(flag3==true) {
        					flag3=false;
            				operationView("Get",false,false);
            				companyLabel.setText(c.getShare().getCompany());
        				}
        			}
        		});
        	}            
            	companyPanel.add(outputLabel);
            	companyPanel.add(outputLabel1);
            	companyPanel.add(outputLabel2);
            	companyPanel.add(outputLabel3);
            	companyPanel.add(outputLabel4);
            	companyPanel.add(outputLabel5);
            	companyPanel.add(outputLabel6);
            	companyPanel.add(outputLabel7);
            	companyPanel.add(button);
        }
	}
}
