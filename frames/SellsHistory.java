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


public class SellsHistory extends JFrame implements ActionListener, MouseListener
{
	JLabel IdLabel, CustomerLabel, MobileLabel, ProductLabel, QuantityLabel, PriceLabel;
	
	JTextField IdTF, CustomerTF, MobileTF, MobileExTF, ProductTF, QuantityTF,PriceTF ;
	
	JButton SearchBtn, LogoutBtn, backBtn, showTableBtn,refreshBtn;
	
	JPanel panel;
	
	JTable sellTable;
	
	JScrollPane sellTableSP;
	
	Font f2, f3;
	
	String id;
	int sid;
	
	Login login;
	Login ck;
	
	Sales s;
	
	SalesRepo sr;
	
	public SellsHistory(Login ck)
	{
		super("Sells History ~ ADMIN");
		this.setSize(1000,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ck = ck;
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~SALES-HISTORY FRAME");
		
		sr = new SalesRepo();
		
		f2 = new Font("Candara",Font.PLAIN,14);
		f3 = new Font("Consolas",Font.PLAIN,17);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		
		String data[][] = {{"", "", "", "", "", "", ""}};
		
		String head[] = {"CID", "NAME", "MOBILE", "PID","QUANTITY", "PAID AMMOUNT"};
		
		sellTable = new JTable(data,head);
		sellTableSP = new JScrollPane(sellTable);
		sellTableSP.setFont(f3);
		sellTable.setFont(f3);
		sellTableSP.setBounds(400, 100, 500, 250);
		sellTable.setEnabled(false);
		panel.add(sellTableSP);
		
		IdLabel = new JLabel("PURCHASE ID.:");
		IdLabel.setBounds(80,100,100,30);
		IdLabel.setFont(f2);
		IdLabel.setBackground(Color.DARK_GRAY);
		IdLabel.setForeground(Color.WHITE);
		IdLabel.setOpaque(true);
		panel.add(IdLabel);
		
		IdTF = new JTextField();
		IdTF.setBounds(250,100,100,30);
		IdTF.setEditable(true);
		IdTF.setFont(f3);
		panel.add(IdTF);
		
		ProductLabel = new JLabel("PRODUCT ID.:");
		ProductLabel.setBounds(80,150,150,30);
		ProductLabel.setFont(f2);
		ProductLabel.setBackground(Color.DARK_GRAY);
		ProductLabel.setForeground(Color.WHITE);
		ProductLabel.setOpaque(true);
		panel.add(ProductLabel);
		
		ProductTF = new JTextField();
		ProductTF.setBounds(250,150,100,30);
		ProductTF.setEditable(false);
		ProductTF.setFont(f3);
		panel.add(ProductTF);
		
		PriceLabel = new JLabel("PAID AMMOUNT:");
		PriceLabel.setBounds(80,250,150,30);
		PriceLabel.setFont(f2);
		PriceLabel.setBackground(Color.DARK_GRAY);
		PriceLabel.setForeground(Color.WHITE);
		PriceLabel.setOpaque(true);
		panel.add(PriceLabel);
		
		PriceTF = new JTextField();
		PriceTF.setBounds(250,250,100,30);
		PriceTF.setEditable(false);
		PriceTF.setFont(f3);
		panel.add(PriceTF);
		
		QuantityLabel = new JLabel("QUANTITY:");
		QuantityLabel.setBounds(80,200,100,30);
		QuantityLabel.setFont(f2);
		QuantityLabel.setBackground(Color.DARK_GRAY);
		QuantityLabel.setForeground(Color.WHITE);
		QuantityLabel.setOpaque(true);
		panel.add(QuantityLabel);
		
		QuantityTF = new JTextField();
		QuantityTF.setBounds(250,200,100,30);
		QuantityTF.setFont(f3);
		QuantityTF.setEditable(false);
		panel.add(QuantityTF);
		
		CustomerLabel = new JLabel("CUSTOMER NAME:");
		CustomerLabel.setBounds(80,300,150,30);
		CustomerLabel.setFont(f2);
		CustomerLabel.setBackground(Color.DARK_GRAY);
		CustomerLabel.setForeground(Color.WHITE);
		CustomerLabel.setOpaque(true);
		panel.add(CustomerLabel);
		
		CustomerTF = new JTextField();
		CustomerTF.setBounds(250,300,100,30);
		CustomerTF.setEditable(false);
		CustomerTF.setFont(f3);
		panel.add(CustomerTF);
		
		MobileLabel = new JLabel("CONTACT:");
		MobileLabel.setBounds(80,350,80,30);
		MobileLabel.setFont(f2);
		MobileLabel.setBackground(Color.DARK_GRAY);
		MobileLabel.setForeground(Color.WHITE);
		MobileLabel.setOpaque(true);
		panel.add(MobileLabel);
		
		MobileExTF = new JTextField();
		MobileExTF.setBounds(210,350,45,30);
		MobileExTF.setText("+880");
		MobileExTF.setEditable(false);
		MobileExTF.setFont(f3);
		panel.add(MobileExTF);
		
		MobileTF = new JTextField();
		MobileTF.setBounds(250,350,100,30);
		MobileTF.setEditable(false);
		MobileTF.setFont(f3);
		panel.add(MobileTF);
		
		
		SearchBtn = new JButton("Search");
		SearchBtn.setBounds(100,400,80,30);
		SearchBtn.setFont(f2);
		SearchBtn.setBackground(Color.DARK_GRAY);
		SearchBtn.setForeground(Color.WHITE);
		SearchBtn.setOpaque(true);
		SearchBtn.addMouseListener(this);
		SearchBtn.setEnabled(true);
		SearchBtn.addActionListener(this);
		panel.add(SearchBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(200,400,80,30);
		backBtn.setFont(f2);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(this);
		backBtn.setEnabled(true);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(690, 400, 80, 30);
		refreshBtn.setFont(f2);
		refreshBtn.setBackground(Color.DARK_GRAY);
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setOpaque(true);
		refreshBtn.addMouseListener(this);
		refreshBtn.setEnabled(true);
		refreshBtn.addActionListener(this);
		panel.add(refreshBtn);
		
		showTableBtn = new JButton("Show Table");
		showTableBtn.setBounds(775, 400, 120, 30);
		showTableBtn.setFont(f2);
		showTableBtn.setBackground(Color.DARK_GRAY);
		showTableBtn.setForeground(Color.WHITE);
		showTableBtn.setOpaque(true);
		showTableBtn.addMouseListener(this);
		showTableBtn.setEnabled(true);
		showTableBtn.addActionListener(this);
		panel.add(showTableBtn);
		
		LogoutBtn = new JButton("Logout");
		LogoutBtn.setBounds(850, 500, 80, 30);
		LogoutBtn.setFont(f2);
		LogoutBtn.setBackground(Color.DARK_GRAY);
		LogoutBtn.setForeground(Color.WHITE);
		LogoutBtn.setOpaque(true);
		LogoutBtn.addMouseListener(this);
		LogoutBtn.setEnabled(true);
		LogoutBtn.addActionListener(this);
		panel.add(LogoutBtn);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		if(command.equals(LogoutBtn.getText()))
		{
			LoginFrame l = new LoginFrame();
			l.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals(backBtn.getText()))
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
		
		else if(command.equals(showTableBtn.getText()))
		{
			String data[][] = sr.getAllSales();
			String head[] = {"CID", "NAME", "MOBILE", "PID","QUANTITY", "PAID AMMOUNT"};
			
			panel.remove(sellTableSP);
			
			sellTable = new JTable(data,head);
			sellTable.setEnabled(false);
			sellTableSP = new JScrollPane(sellTable);
			sellTableSP.setFont(f3);
			sellTable.setFont(f3);
			sellTableSP.setBounds(400, 100, 500, 270);
			panel.add(sellTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		
		else if(command.equals(SearchBtn.getText()))
		{
			if(!IdTF.getText().equals("") || !IdTF.getText().equals(null))
			{
				Sales e = sr.searchProduct(IdTF.getText());
				if(e!= null)
				{
					IdTF.setText(e.getCID());
					CustomerTF.setText(e.getNAME());
					MobileTF.setText(e.getCONTACT()+"");
					ProductTF.setText(e.getPID());
					QuantityTF.setText(e.getQUANTITY()+"");
					PriceTF.setText(e.getSELLPRICE()+"");
					
					IdTF.setEditable(false);
					CustomerTF.setEditable(false);
					MobileTF.setEditable(false);
					ProductTF.setEditable(false);
					QuantityTF.setEditable(false);
					PriceTF.setEditable(false);
					
					refreshBtn.setEnabled(true);
					
					
					
					SearchBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		else if(command.equals(refreshBtn.getText()))
		{
			IdTF.setText("");
			CustomerTF.setText("");
			MobileTF.setText("");
			ProductTF.setText("");
			QuantityTF.setText("");
			PriceTF.setText("");
			
			IdTF.setEditable(true);
			
			SearchBtn.setEnabled(true);
			refreshBtn.setEnabled(false);
		}
		else{}
		
	}
	
	public void mouseClicked(MouseEvent me){}
	
	public void mousePressed(MouseEvent me){}
	
	public void mouseReleased(MouseEvent me){}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == SearchBtn)
		{
			SearchBtn.setBackground(Color.WHITE);
			SearchBtn.setForeground(Color.BLACK);
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
		if(me.getSource() == SearchBtn)
		{
			SearchBtn.setBackground(Color.DARK_GRAY);
			SearchBtn.setForeground(Color.WHITE);
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