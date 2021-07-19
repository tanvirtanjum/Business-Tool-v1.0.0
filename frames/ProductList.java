package frames;

import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.sql.*;

import entity.*;
import repository.*;


public class ProductList extends JFrame implements MouseListener, ActionListener
{
	JLabel pdIdLabel, pdNameLabel, pdTypeLabel, pdBuyPriceLabel, pdAvailableQuantityLabel, pdSellPriceLabel;
	
	JTextField pdIdTF, pdNameTF, pdTypeTF, pdBuyPriceTF, pdAvailableQuantityTF, pdSellPriceTF ;
	
	JButton srchBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, LogoutBtn, backBtn, showTableBtn;
	
	JPanel panel;
	
	JTable productTable;
	
	JScrollPane productTableSP;
	
	Font f2, f3;
	
	String id;
	int sid;
	
	Login ck;
	Login l;
	
	Product lg;
	
	ProductRepo er;
	
	public ProductList(Login ck)
	{
		super("Product Details ~ ADMIN");
		this.setSize(1000,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ck = ck;
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~PRODUCT-LIST FRAME");
		
		er = new ProductRepo();
		
		f2 = new Font("Candara",Font.PLAIN,14);
		f3 = new Font("Consolas",Font.PLAIN,17);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		
		
		String data[][] = {{"", "", "", "", "", "", ""}};
		
		String head[] = {"PID", "NAME", "TYPE", "QUANTITY", "BUY_PRICE", "SELL_PRICE"};
		
		productTable = new JTable(data,head);
		productTableSP = new JScrollPane(productTable);
		productTableSP.setBounds(400, 100, 500, 270);
		productTableSP.setFont(f3);
		productTable.setFont(f3);
		productTable.setEnabled(false);
		panel.add(productTableSP);
		
		pdIdLabel = new JLabel("PRODUCT ID.:");
		pdIdLabel.setBounds(80,100,100,30);
		pdIdLabel.setFont(f2);
		pdIdLabel.setBackground(Color.DARK_GRAY);
		pdIdLabel.setForeground(Color.WHITE);
		pdIdLabel.setOpaque(true);
		panel.add(pdIdLabel);
		
		pdIdTF = new JTextField();
		pdIdTF.setBounds(250,100,100,30);
		pdIdTF.setFont(f3);
		panel.add(pdIdTF);
		
		pdNameLabel = new JLabel("PRODUCT NAME:");
		pdNameLabel.setBounds(80,150,150,30);
		pdNameLabel.setFont(f2);
		pdNameLabel.setBackground(Color.DARK_GRAY);
		pdNameLabel.setForeground(Color.WHITE);
		pdNameLabel.setOpaque(true);
		panel.add(pdNameLabel);
		
		pdNameTF = new JTextField();
		pdNameTF.setBounds(250,150,100,30);
		pdNameTF.setFont(f3);
		panel.add(pdNameTF);
		
		pdTypeLabel = new JLabel("TYPE:");
		pdTypeLabel.setBounds(80,200,150,30);
		pdTypeLabel.setFont(f2);
		pdTypeLabel.setBackground(Color.DARK_GRAY);
		pdTypeLabel.setForeground(Color.WHITE);
		pdTypeLabel.setOpaque(true);
		panel.add(pdTypeLabel);
		
		pdTypeTF = new JTextField();
		pdTypeTF.setBounds(250,200,100,30);
		pdTypeTF.setFont(f3);
		panel.add(pdTypeTF);
		
		pdAvailableQuantityLabel = new JLabel("AVAILABLE QUANTITY:");
		pdAvailableQuantityLabel.setBounds(80,250,150,30);
		pdAvailableQuantityLabel.setFont(f2);
		pdAvailableQuantityLabel.setBackground(Color.DARK_GRAY);
		pdAvailableQuantityLabel.setForeground(Color.WHITE);
		pdAvailableQuantityLabel.setOpaque(true);
		panel.add(pdAvailableQuantityLabel);
		
		pdAvailableQuantityTF = new JTextField();
		pdAvailableQuantityTF.setBounds(250,250,100,30);
		pdAvailableQuantityTF.setFont(f3);
		panel.add(pdAvailableQuantityTF);
		
		pdBuyPriceLabel = new JLabel("BUY-PRICE:");
		pdBuyPriceLabel.setBounds(80,300,150,30);
		pdBuyPriceLabel.setFont(f2);
		pdBuyPriceLabel.setBackground(Color.DARK_GRAY);
		pdBuyPriceLabel.setForeground(Color.WHITE);
		pdBuyPriceLabel.setOpaque(true);
		panel.add(pdBuyPriceLabel);
		
		pdBuyPriceTF = new JTextField();
		pdBuyPriceTF.setBounds(250,300,100,30);
		pdBuyPriceTF.setFont(f3);
		panel.add(pdBuyPriceTF);
		
		pdSellPriceLabel = new JLabel("SELL-PRICE:");
		pdSellPriceLabel.setBounds(80,350,100,30);
		pdSellPriceLabel.setFont(f2);
		pdSellPriceLabel.setBackground(Color.DARK_GRAY);
		pdSellPriceLabel.setForeground(Color.WHITE);
		pdSellPriceLabel.setOpaque(true);
		panel.add(pdSellPriceLabel);
		
		pdSellPriceTF = new JTextField();
		pdSellPriceTF.setBounds(250,350,100,30);
		pdSellPriceTF.setFont(f3);
		panel.add(pdSellPriceTF);
		
		srchBtn = new JButton("Search");
		srchBtn.setBounds(100,450,80,30);
		srchBtn.setFont(f2);
		srchBtn.setBackground(Color.DARK_GRAY);
		srchBtn.setForeground(Color.WHITE);
		srchBtn.setOpaque(true);
		srchBtn.addMouseListener(this);
		srchBtn.addActionListener(this);
		srchBtn.setEnabled(true);
		panel.add(srchBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,450,80,30);
		insertBtn.setFont(f2);
		insertBtn.setBackground(Color.DARK_GRAY);
		insertBtn.setForeground(Color.WHITE);
		insertBtn.setOpaque(true);
		insertBtn.addMouseListener(this);
		insertBtn.addActionListener(this);
		insertBtn.setEnabled(true);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,450,80,30);
		updateBtn.setFont(f2);
		updateBtn.setBackground(Color.DARK_GRAY);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setOpaque(true);
		updateBtn.addMouseListener(this);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400,450,80,30);
		deleteBtn.setFont(f2);
		deleteBtn.setBackground(Color.DARK_GRAY);
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setOpaque(true);
		deleteBtn.addMouseListener(this);
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);
		
		showTableBtn = new JButton("Show Table");
		showTableBtn.setBounds(775, 400, 120, 30);
		showTableBtn.setFont(f2);
		showTableBtn.setBackground(Color.DARK_GRAY);
		showTableBtn.setForeground(Color.WHITE);
		showTableBtn.setOpaque(true);
		showTableBtn.addMouseListener(this);
		showTableBtn.addActionListener(this);
		showTableBtn.setEnabled(true);
		panel.add(showTableBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(680, 400, 80, 30);
		refreshBtn.setFont(f2);
		refreshBtn.setBackground(Color.DARK_GRAY);
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setOpaque(true);
		refreshBtn.addMouseListener(this);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		LogoutBtn = new JButton("Logout");
		LogoutBtn.setBounds(800, 500, 80, 30);
		LogoutBtn.setFont(f2);
		LogoutBtn.setBackground(Color.DARK_GRAY);
		LogoutBtn.setForeground(Color.WHITE);
		LogoutBtn.setOpaque(true);
		LogoutBtn.addMouseListener(this);
		LogoutBtn.addActionListener(this);
		LogoutBtn.setEnabled(true);
		panel.add(LogoutBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(500,450,80,30); 
		backBtn.setFont(f2);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(this);
		backBtn.addActionListener(this);
		backBtn.setEnabled(true);
		panel.add(backBtn);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(backBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				MainFrame MF = new MainFrame(ck);
				MF.setVisible(true);
				this.setVisible(false);	
				MF.SellProdBtn.setEnabled(false);				
			}
			
			else if(ck.getSID() == 2)
			{
				MainFrame MF = new MainFrame(ck);
				MF.setVisible(true);
				this.setVisible(false);		
				MF.SellProdBtn.setEnabled(false);
			}
			
			else if(ck.getSID() == 3)
			{
				MainFrame MF = new MainFrame(ck);
				MF.setVisible(true);
				this.setVisible(false);
				MF.SellProdBtn.setEnabled(true);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
		}
		
		else if(command.equals(LogoutBtn.getText()))
		{
			LoginFrame l = new LoginFrame();
			l.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals(srchBtn.getText()))
		{
			if(!pdIdTF.getText().equals("") || !pdIdTF.getText().equals(null))
			{
				Product e = er.searchProduct(pdIdTF.getText());
				if(e != null)
				{
					pdNameTF.setText(e.getNAME());
					pdTypeTF.setText(e.getTYPE());
					pdAvailableQuantityTF.setText(e.getQUANTITY()+"");
					pdBuyPriceTF.setText(e.getBUYPRICE()+"");
					pdSellPriceTF.setText(e.getSELLPRICE()+"");
					
					pdIdTF.setEditable(false);
					pdNameTF.setEditable(true);
					pdTypeTF.setEditable(true);
					pdAvailableQuantityTF.setEditable(true);
					pdBuyPriceTF.setEditable(true);
					pdSellPriceTF.setEditable(true);
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					srchBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		
		else if(command.equals(showTableBtn.getText()))
		{
			String data[][] = er.getAllProduct();
			String head[] = {"PID", "NAME", "TYPE", "QUANTITY", "BUYPRICE", "SELLPRICE"};
			
			panel.remove(productTableSP);
			
			productTable = new JTable(data,head);
			productTable.setEnabled(false);
			productTableSP = new JScrollPane(productTable);
			productTableSP.setBounds(400, 100, 500, 270);
			productTableSP.setFont(f3);
			productTable.setFont(f3);
			panel.add(productTableSP);
			
			panel.revalidate();
			panel.repaint();
		}
		
		else if(command.equals(insertBtn.getText()))
		{
			if(ck.getSID() == 1 || ck.getSID() == 2)
			{
				if((!pdIdTF.getText().equals("") || !pdIdTF.getText().equals(null)) && (!pdNameTF.getText().equals("") || !pdNameTF.getText().equals(null)) && (!pdTypeTF.getText().equals("") || !pdTypeTF.getText().equals(null)) && (!pdAvailableQuantityTF.getText().equals("") || !pdAvailableQuantityTF.getText().equals(null)) && (!pdBuyPriceTF.getText().equals("") || !pdBuyPriceTF.getText().equals(null)) && (!pdSellPriceTF.getText().equals("") || !pdNameTF.getText().equals(null)))
				{
					Product ck;
					ck = er.searchProduct(pdIdTF.getText());
					if(ck == null && (Integer.parseInt(pdAvailableQuantityTF.getText()) >= 0 && Integer.parseInt(pdBuyPriceTF.getText()) >= 0 && Integer.parseInt(pdSellPriceTF.getText()) >=0) && (Integer.parseInt(pdBuyPriceTF.getText()) <= Integer.parseInt(pdSellPriceTF.getText())))
					{
						try
						{
							Product e = new Product();
							e.setPID(pdIdTF.getText());
							e.setNAME(pdNameTF.getText());
							e.setTYPE(pdTypeTF.getText());
							e.setQUANTITY(Integer.parseInt(pdAvailableQuantityTF.getText()));
							e.setBUYPRICE(Integer.parseInt(pdBuyPriceTF.getText()));
							e.setSELLPRICE(Integer.parseInt(pdSellPriceTF.getText()));
							
							er.insertIntoDataBase(e);
							
							JOptionPane.showMessageDialog(this, "Product Added, Id: "+pdIdTF.getText());
							
							pdIdTF.setText("");
							pdNameTF.setText("");
							pdTypeTF.setText("");
							pdAvailableQuantityTF.setText("");
							pdBuyPriceTF.setText("");
							pdSellPriceTF.setText("");
							
							srchBtn.setEnabled(true);
							insertBtn.setEnabled(true);
							updateBtn.setEnabled(false);
							deleteBtn.setEnabled(false);
							refreshBtn.setEnabled(false);
						}
						
						catch(Exception ex)
						{
							pdIdTF.setText("");
							pdNameTF.setText("");
							pdTypeTF.setText("");
							pdAvailableQuantityTF.setText("");
							pdBuyPriceTF.setText("");
							pdSellPriceTF.setText("");
							
							srchBtn.setEnabled(true);
							insertBtn.setEnabled(true);
							updateBtn.setEnabled(false);
							deleteBtn.setEnabled(false);
							refreshBtn.setEnabled(false);
							
							JOptionPane.showMessageDialog(this, "ERROR. Try Again");						
						}
					}
					
					else
					{
						pdIdTF.setText("");
						pdNameTF.setText("");
						pdTypeTF.setText("");
						pdAvailableQuantityTF.setText("");
						pdBuyPriceTF.setText("");
						pdSellPriceTF.setText("");
						
						JOptionPane.showMessageDialog(this,"ERROR. Try Again");
					}
				}
				
				else
				{
					pdIdTF.setText("");
					pdNameTF.setText("");
					pdTypeTF.setText("");
					pdAvailableQuantityTF.setText("");
					pdBuyPriceTF.setText("");
					pdSellPriceTF.setText("");
					
					JOptionPane.showMessageDialog(this,"Invalid Fill");				
				}
			}
			
			else
			{
				JOptionPane.showMessageDialog(this,"ACCESS DENIED");
			}
		}
		
		else if(command.equals(refreshBtn.getText()))
		{
			pdIdTF.setText("");
			pdNameTF.setText("");
			pdTypeTF.setText("");
			pdAvailableQuantityTF.setText("");
			pdBuyPriceTF.setText("");
			pdSellPriceTF.setText("");
			
			pdIdTF.setEnabled(true);
			srchBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		
		else if(command.equals(updateBtn.getText()))
		{
			if(ck.getSID() == 1 || ck.getSID() == 2)
			{
				try
				{
					Product e = new Product();
				
					e.setPID(pdIdTF.getText());
					e.setNAME(pdNameTF.getText());
					e.setTYPE(pdTypeTF.getText());
					e.setQUANTITY(Integer.parseInt(pdAvailableQuantityTF.getText()));
					e.setBUYPRICE(Double.parseDouble(pdBuyPriceTF.getText()));
					e.setSELLPRICE(Double.parseDouble(pdSellPriceTF.getText()));
					
					if((!e.getNAME().equals(null) || !e.getNAME().equals(null)) && (!e.getTYPE().equals(null) || !e.getTYPE().equals(null)) && (e.getQUANTITY() >= 0) && (e.getBUYPRICE() >=0 && e.getSELLPRICE() >= e.getBUYPRICE()))
					{
						er.updateIntoDataBase(e);
						JOptionPane.showMessageDialog(this, "Updated");
					}
					
					else
					{
						JOptionPane.showMessageDialog(this, "ERROR");
					}
				
					
					pdIdTF.setText("");
					pdNameTF.setText("");
					pdTypeTF.setText("");
					pdAvailableQuantityTF.setText("");
					pdBuyPriceTF.setText("");
					pdSellPriceTF.setText("");
					
					srchBtn.setEnabled(true);
					insertBtn.setEnabled(true);
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
					refreshBtn.setEnabled(false);
					
					pdIdTF.setEditable(true);
					pdNameTF.setEditable(true);
					pdTypeTF.setEditable(true);
					pdAvailableQuantityTF.setEditable(true);
					pdBuyPriceTF.setEditable(true);
					pdSellPriceTF.setEditable(true);
				}
				
				catch(Exception ex)
				{
					pdIdTF.setText("");
					pdNameTF.setText("");
					pdTypeTF.setText("");
					pdAvailableQuantityTF.setText("");
					pdBuyPriceTF.setText("");
					pdSellPriceTF.setText("");
							
					srchBtn.setEnabled(true);
					insertBtn.setEnabled(true);
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
					refreshBtn.setEnabled(false);
							
					JOptionPane.showMessageDialog(this, "ERROR. Try Again");						
				}
			}
			
			else
			{
				JOptionPane.showMessageDialog(this,"ACCESS DENIED");
			}
		}
		
		else if(command.equals(deleteBtn.getText()))
		{
			if(ck.getSID() == 1 || ck.getSID() == 2)
			{
				er.deleteFromDataBase(pdIdTF.getText());
			
				JOptionPane.showMessageDialog(this,"Deleted");
				
				pdIdTF.setText("");
				pdNameTF.setText("");
				pdTypeTF.setText("");
				pdAvailableQuantityTF.setText("");
				pdBuyPriceTF.setText("");
				pdSellPriceTF.setText("");
				
				pdIdTF.setEditable(true);
				pdNameTF.setEditable(true);
				pdTypeTF.setEditable(true);
				pdAvailableQuantityTF.setEditable(true);
				pdBuyPriceTF.setEditable(true);
				pdSellPriceTF.setEditable(true);
		
				srchBtn.setEnabled(true);
				insertBtn.setEnabled(true);
				updateBtn.setEnabled(false);
				deleteBtn.setEnabled(false);
				refreshBtn.setEnabled(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this,"ACCESS DENIED");
			}
		}
	
		else{}	
	}
	
	public void mouseClicked(MouseEvent me){}
	
	public void mousePressed(MouseEvent me){}
	
	public void mouseReleased(MouseEvent me){}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == srchBtn)
		{
			srchBtn.setBackground(Color.WHITE);
			srchBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == insertBtn)
		{
			if(ck.getSID() == 1 || ck.getSID() == 2)
			{
				insertBtn.setBackground(Color.WHITE);
				insertBtn.setForeground(Color.BLACK);
			}
			
			else
			{
				insertBtn.setBackground(Color.RED);
				insertBtn.setForeground(Color.BLACK);
			}
		}
		
		else if(me.getSource() == updateBtn)
		{
			if(ck.getSID() == 1 || ck.getSID() == 2)
			{
				updateBtn.setBackground(Color.WHITE);
				updateBtn.setForeground(Color.BLACK);
			}
			
			else
			{
				updateBtn.setBackground(Color.RED);
				updateBtn.setForeground(Color.BLACK);
			}
		}
		
		else if(me.getSource() == deleteBtn)
		{
			if(ck.getSID() == 1 || ck.getSID() == 2)
			{
				deleteBtn.setBackground(Color.WHITE);
				deleteBtn.setForeground(Color.BLACK);
			}
			
			else
			{
				deleteBtn.setBackground(Color.RED);
				deleteBtn.setForeground(Color.BLACK);
			}
		}
		
		else if(me.getSource() == refreshBtn)
		{
			refreshBtn.setBackground(Color.WHITE);
			refreshBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == backBtn)
		{
			backBtn.setBackground(Color.WHITE);
			backBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == showTableBtn)
		{
			showTableBtn.setBackground(Color.WHITE);
			showTableBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.RED);
			LogoutBtn.setForeground(Color.BLACK);
		}
		
		else{}
	}
	
	public void mouseExited(MouseEvent me)
	{	
		if(me.getSource() == srchBtn)
		{
			srchBtn.setBackground(Color.DARK_GRAY);
			srchBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == insertBtn)
		{
			insertBtn.setBackground(Color.DARK_GRAY);
			insertBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == updateBtn)
		{
			updateBtn.setBackground(Color.DARK_GRAY);
			updateBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == deleteBtn)
		{
			deleteBtn.setBackground(Color.DARK_GRAY);
			deleteBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == refreshBtn)
		{
			refreshBtn.setBackground(Color.DARK_GRAY);
			refreshBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == backBtn)
		{
			backBtn.setBackground(Color.DARK_GRAY);
			backBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == showTableBtn)
		{
			showTableBtn.setBackground(Color.DARK_GRAY);
			showTableBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.DARK_GRAY);
			LogoutBtn.setForeground(Color.WHITE);
		}
		
		else{}
	}
}