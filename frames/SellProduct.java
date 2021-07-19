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


public class SellProduct extends JFrame implements ActionListener, MouseListener
{
	JLabel IDLabel, CustomLabel, MobLabel, ProdLabel, QuantLabel, PriceLabel;
	
	JTextField IDTF, CustomTF, MobTF, MobileExTF, ProdTF, QuantTF , PriceTF;
	
	JButton sellBtn, LogoutBtn, backBtn,showTableBtn;
	
	JPanel panel;
	
	JTable productTable;
	
	JScrollPane productTableSP;
	
	Font f2, f3;
	
	String id;
	int sid;
	
	Login l;
	Login ck;
	Product p;
	
	ProductRepo pr;
	SalesRepo sr;
	
	public SellProduct(Login ck)
	{
		super("Sell Product ~ SALESMAN");
		this.setSize(1000,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ck = ck;
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~SELL-PRODUCT FRAME");
		
		pr=new ProductRepo();
		sr = new SalesRepo();
		p = new Product();
		
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
		
		IDLabel = new JLabel("PURCHASE ID.:");
		IDLabel.setBounds(80,100,100,30);
		IDLabel.setFont(f2);
		IDLabel.setBackground(Color.DARK_GRAY);
		IDLabel.setForeground(Color.WHITE);
		IDLabel.setOpaque(true);
		panel.add(IDLabel);
		
		IDTF = new JTextField();
		IDTF.setBounds(250,100,100,30);
		IDTF.setEditable(true);
		IDTF.setFont(f3);
		panel.add(IDTF);
		
		ProdLabel = new JLabel("PRODUCT ID.:");
		ProdLabel.setBounds(80,150,150,30);
		ProdLabel.setFont(f2);
		ProdLabel.setBackground(Color.DARK_GRAY);
		ProdLabel.setForeground(Color.WHITE);
		ProdLabel.setOpaque(true);
		panel.add(ProdLabel);
		
		ProdTF = new JTextField();
		ProdTF.setBounds(250,150,100,30);
		ProdTF.setEditable(true);
		ProdTF.setFont(f3);
		panel.add(ProdTF);
		
		PriceLabel = new JLabel("PRICE (/unit):");
		PriceLabel.setBounds(80,250,150,30);
		PriceLabel.setFont(f2);
		PriceLabel.setBackground(Color.DARK_GRAY);
		PriceLabel.setForeground(Color.WHITE);
		PriceLabel.setOpaque(true);
		panel.add(PriceLabel);
		
		PriceTF = new JTextField();
		PriceTF.setBounds(250,250,100,30);
		PriceTF.setEditable(true);
		PriceTF.setFont(f3);
		panel.add(PriceTF);
		
		QuantLabel = new JLabel("QUANTITY:");
		QuantLabel.setBounds(80,200,100,30);
		QuantLabel.setFont(f2);
		QuantLabel.setBackground(Color.DARK_GRAY);
		QuantLabel.setForeground(Color.WHITE);
		QuantLabel.setOpaque(true);
		panel.add(QuantLabel);
		
		QuantTF = new JTextField();
		QuantTF.setBounds(250,200,100,30);
		QuantTF.setFont(f3);
		QuantTF.setEditable(true);
		panel.add(QuantTF);
		
		CustomLabel = new JLabel("CUSTOMER NAME:");
		CustomLabel.setBounds(80,300,150,30);
		CustomLabel.setFont(f2);
		CustomLabel.setBackground(Color.DARK_GRAY);
		CustomLabel.setForeground(Color.WHITE);
		CustomLabel.setOpaque(true);
		panel.add(CustomLabel);
		
		CustomTF = new JTextField();
		CustomTF.setBounds(250,300,100,30);
		CustomTF.setEditable(true);
		CustomTF.setFont(f3);
		panel.add(CustomTF);
		
		MobLabel = new JLabel("CONTACT:");
		MobLabel.setBounds(80,350,80,30);
		MobLabel.setFont(f2);
		MobLabel.setBackground(Color.DARK_GRAY);
		MobLabel.setForeground(Color.WHITE);
		MobLabel.setOpaque(true);
		panel.add(MobLabel);
		
		MobileExTF = new JTextField();
		MobileExTF.setBounds(210,350,45,30);
		MobileExTF.setText("+880");
		MobileExTF.setEditable(false);
		MobileExTF.setFont(f3);
		panel.add(MobileExTF);
		
		MobTF = new JTextField();
		MobTF.setBounds(250,350,100,30);
		MobTF.setEditable(true);
		MobTF.setFont(f3);
		panel.add(MobTF);
		
		sellBtn = new JButton("Sell Product");
		sellBtn.setBounds(100,400,180,30);
		sellBtn.setFont(f2);
		sellBtn.setBackground(Color.DARK_GRAY);
		sellBtn.setForeground(Color.WHITE);
		sellBtn.setOpaque(true);
		sellBtn.addMouseListener(this);
		sellBtn.addActionListener(this);
		panel.add(sellBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(150, 450, 80, 30);
		backBtn.setFont(f2);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(this);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		showTableBtn = new JButton("Show Table");
		showTableBtn.setBounds(775, 400, 120, 30);
		showTableBtn.setFont(f2);
		showTableBtn.setBackground(Color.DARK_GRAY);
		showTableBtn.setForeground(Color.WHITE);
		showTableBtn.setOpaque(true);
		showTableBtn.addMouseListener(this);
		showTableBtn.addActionListener(this);
		panel.add(showTableBtn);
		
		
		LogoutBtn = new JButton("Logout");
		LogoutBtn.setBounds(850, 500, 80, 30);
		LogoutBtn.setFont(f2);
		LogoutBtn.setBackground(Color.DARK_GRAY);
		LogoutBtn.setForeground(Color.WHITE);
		LogoutBtn.setOpaque(true);
		LogoutBtn.addMouseListener(this);
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
			MainFrame MF = new MainFrame(ck);
			MF.setVisible(true);
			this.setVisible(false);
			MF.SellProdBtn.setEnabled(true);
		}
		
		else if(command.equals(showTableBtn.getText()))
		{
			String data[][] = pr.getAllProduct();
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
		
		else if(command.equals(sellBtn.getText()))
		{
			if(!IDTF.getText().equals("") || !IDTF.getText().equals(null) && !CustomTF.getText().equals("") || !CustomTF.getText().equals(null) && !MobTF.getText().equals("") || !MobTF.getText().equals(null) && !QuantTF.getText().equals("") || !QuantTF.getText().equals(null) && !PriceTF.getText().equals("") || !PriceTF.getText().equals(null) && !ProdTF.getText().equals("") || !ProdTF.getText().equals(null)  )
			{
				ProductRepo nn = new ProductRepo(); 
				double BuyPrice = nn.searchBuyPrice(ProdTF.getText());
				
				Sales e4 = new Sales();
				e4.setSELLPRICE(Double.parseDouble(PriceTF.getText()));
				double SellPrice = e4.getSELLPRICE();
				
				if((SellPrice >= BuyPrice) && (SellPrice >= 0 && BuyPrice >= 0))
				{
					ProductRepo pn = new ProductRepo(); 
					int Quantity = pn.searchQuantity(ProdTF.getText());
					
					Sales e3 = new Sales();
					e3.setQUANTITY(Integer.parseInt(QuantTF.getText()));
					int Sell = e3.getQUANTITY();
					
					if((Sell <= Quantity) && (Sell >= 0 && Quantity >= 0))
					{
						Sales sck;
						Product pck;
						
						pck = pr.searchProduct(ProdTF.getText());
						sck = sr.searchProduct(IDTF.getText());
						if(sck == null && pck != null)
						{
							try
							{
								Sales e = new Sales();
								Sales e2 = new Sales();
								Product p = new Product();
								
								e2.setQUANTITY(Integer.parseInt(QuantTF.getText()));
								e2.setSELLPRICE(Double.parseDouble(PriceTF.getText()));
								double calculatedAmmout = (e2.getQUANTITY())*(e2.getSELLPRICE()); 
								
								e.setCID(IDTF.getText());
								e.setNAME(CustomTF.getText());
								e.setCONTACT(Integer.parseInt(MobTF.getText()));    
								e.setQUANTITY(Integer.parseInt(QuantTF.getText()));
								e.setPID(ProdTF.getText());   
								e.setSELLPRICE(calculatedAmmout);          
								
								ProductRepo pc = new ProductRepo();
								p.setPID(ProdTF.getText());  
								int updatedQuantity = pc.searchQuantity(ProdTF.getText())- e.getQUANTITY();
								p.setQUANTITY(updatedQuantity);
								
								
								sr.updateIntoDataBase(p);
								sr.insertIntoDataBase(e);
								
								
								try
								{
									String write = "Purchase ID.: "+IDTF.getText()+"\nProduct ID.: "+ProdTF.getText()+"\nCustomer Name: "+CustomTF.getText()+"\nContact No.: "+MobTF.getText()+"\nBuying Quantity: "+QuantTF.getText()+"\nPrice Per Unit: "+PriceTF.getText()+" BDT"+"\nPaid Ammount: "+calculatedAmmout+" BDT";
									String loc = "C:\\Users\\tanju\\Desktop\\BT Updated - Copy\\Data\\Sell Receits";
									String FileName = IDTF.getText()+".txt";
									
									File file = new File(loc);
									FileWriter writer = new FileWriter(new File(file,FileName));
									file.createNewFile();
									writer.write(write); 
									writer.flush();
									writer.close();
									
									JOptionPane.showMessageDialog(this, "Sell Done. You can print recit.");
								}
								
								catch(Exception ex)
								{
									System.out.println("FILE NOT CREATED");
								}
								
								IDTF.setText("");
								CustomTF.setText("");
								MobTF.setText("");
								QuantTF.setText("");
								ProdTF.setText("");
								PriceTF.setText("");
							
							
								sellBtn.setEnabled(true);
							}
				
							catch(Exception ex)
							{		
								IDTF.setText("");
								CustomTF.setText("");
								MobTF.setText("");
								QuantTF.setText("");
								ProdTF.setText("");
								PriceTF.setText("");

								sellBtn.setEnabled(true);
								
								JOptionPane.showMessageDialog(this, "ERROR. Try Again.");				
							}		
						}
							
						else
						{
							IDTF.setText("");
							CustomTF.setText("");
							MobTF.setText("");
							QuantTF.setText("");
							ProdTF.setText("");
							PriceTF.setText("");

							sellBtn.setEnabled(true);
							
							JOptionPane.showMessageDialog(this, "ERROR. Try Again.");
						}		
					}
					
					else
					{
						IDTF.setText("");
						CustomTF.setText("");
						MobTF.setText("");
						QuantTF.setText("");
						ProdTF.setText("");
						PriceTF.setText("");

						sellBtn.setEnabled(true);
						
						JOptionPane.showMessageDialog(this, "Product Limit Error.");
						
					}
				}
				else
				{
					IDTF.setText("");
					CustomTF.setText("");
					MobTF.setText("");
					QuantTF.setText("");
					ProdTF.setText("");
					PriceTF.setText("");

					sellBtn.setEnabled(true);
					
					JOptionPane.showMessageDialog(this, "Can't Sell Without Profiting. Update Sell Price.");
					
				}
					
						
			}
			
			else
			{
				IDTF.setText("");
				CustomTF.setText("");
				MobTF.setText("");
				QuantTF.setText("");
				ProdTF.setText("");
				PriceTF.setText("");
				
				JOptionPane.showMessageDialog(this,"Invalid ID.");				
			}
			
		}
			
	else{}			
		
	}
		
	
	public void mouseClicked(MouseEvent me){}
	
	public void mousePressed(MouseEvent me){}
	
	public void mouseReleased(MouseEvent me){}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == sellBtn)
		{
			sellBtn.setBackground(Color.WHITE);
			sellBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.WHITE);
			LogoutBtn.setForeground(Color.BLACK);
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
		
		else{}
	}
	
	public void mouseExited(MouseEvent me)
	{	
		if(me.getSource() == sellBtn)
		{
			sellBtn.setBackground(Color.DARK_GRAY);
			sellBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.DARK_GRAY);
			LogoutBtn.setForeground(Color.WHITE);
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
		
		else{}
	}
}