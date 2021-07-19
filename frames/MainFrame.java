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

public class MainFrame extends JFrame implements ActionListener, MouseListener
{
	
	JButton EmpListBtn, ProdListBtn, SellProdBtn, SellsHistBtn, abtBtn, LogoutBtn;
	
	JPanel panel;
	
	Product p;
	
	Login ck;
	Login login;
	
	Product product;
	
	String id;
	int sid;
	
	Font f1,f2;
	
	public MainFrame(Login ck)
	{
		super("Main Page");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.ck = ck; 
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~MAIN FRAME");
		
		f1 = new Font("Candara",Font.BOLD,14);
		f2 = new Font("Candara",Font.PLAIN,14);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		EmpListBtn = new JButton("EMPLOYEE LIST");
		EmpListBtn.setBounds(150, 100, 150, 30);
		EmpListBtn.setFont(f1);
		EmpListBtn.setBackground(Color.DARK_GRAY);
		EmpListBtn.setForeground(Color.WHITE);
		EmpListBtn.setOpaque(true);
		EmpListBtn.addMouseListener(this);
		EmpListBtn.addActionListener(this);
		EmpListBtn.setEnabled(true);
		panel.add(EmpListBtn);
		
		ProdListBtn = new JButton("PRODUCT LIST");
		ProdListBtn.setBounds(150, 150, 150, 30);
		ProdListBtn.setFont(f1);
		ProdListBtn.setBackground(Color.DARK_GRAY);
		ProdListBtn.setForeground(Color.WHITE);
		ProdListBtn.setOpaque(true);
		ProdListBtn.addMouseListener(this);
		ProdListBtn.addActionListener(this);
		ProdListBtn.setEnabled(true);
		panel.add(ProdListBtn);
		
		SellsHistBtn = new JButton("SELLS HISTORY");
		SellsHistBtn.setBounds(150, 200, 150, 30);
		SellsHistBtn.setFont(f1);
		SellsHistBtn.setBackground(Color.DARK_GRAY);
		SellsHistBtn.setForeground(Color.WHITE);
		SellsHistBtn.setOpaque(true);
		SellsHistBtn.addMouseListener(this);
		SellsHistBtn.addActionListener(this);
		SellsHistBtn.setEnabled(true);
		panel.add(SellsHistBtn);
		
		SellProdBtn = new JButton("SELL PRODUCT");
		SellProdBtn.setBounds(500, 100, 150, 30);
		SellProdBtn.setFont(f1);
		SellProdBtn.setBackground(Color.DARK_GRAY);
		SellProdBtn.setForeground(Color.WHITE);
		SellProdBtn.setOpaque(true);
		SellProdBtn.addMouseListener(this);
		SellProdBtn.addActionListener(this);
		SellProdBtn.setEnabled(false);
		panel.add(SellProdBtn);
		
		abtBtn = new JButton("ABOUT ME");
		abtBtn.setBounds(500, 200, 150, 30);
		abtBtn.setFont(f2);
		abtBtn.setBackground(Color.DARK_GRAY);
		abtBtn.setForeground(Color.WHITE);
		abtBtn.setOpaque(true);
		abtBtn.addMouseListener(this);
		abtBtn.addActionListener(this);
		abtBtn.setEnabled(true);
		panel.add(abtBtn);
		
		LogoutBtn = new JButton("Logout");
		LogoutBtn.setBounds(650, 400, 80, 30);
		LogoutBtn.setFont(f1);
		LogoutBtn.setBackground(Color.DARK_GRAY);
		LogoutBtn.setForeground(Color.WHITE);
		LogoutBtn.setOpaque(true);
		LogoutBtn.addMouseListener(this);
		LogoutBtn.addActionListener(this);
		LogoutBtn.setEnabled(true);
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
	
		else if(command.equals(EmpListBtn.getText()))
		{ 
	        if(ck.getSID() == 1)
			{
				EmployeeList EL = new EmployeeList(ck);
				EL.setVisible(true);
				this.setVisible(false);	
			}
			
			else if(ck.getSID() == 2)
			{
				EmployeeList EL = new EmployeeList(ck);
				EL.setVisible(true);
				this.setVisible(false);	
				EL.insertBtn.setEnabled(false); 
				EL.updateBtn.setEnabled(false); 
				EL.deleteBtn.setEnabled(false);
			}
			
			else if(ck.getSID() == 3)
			{
				EmployeeList EL = new EmployeeList(ck);
				EL.setVisible(true);
				this.setVisible(false);	
				EL.insertBtn.setEnabled(false); 
				EL.updateBtn.setEnabled(false); 
				EL.deleteBtn.setEnabled(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
			
		}
		
		else if(command.equals(ProdListBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				ProductList PL = new ProductList(ck);
				PL.setVisible(true);
				this.setVisible(false);	
			}
			
			else if(ck.getSID() == 2)
			{
				ProductList PL = new ProductList(ck);
				PL.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 3)
			{
				ProductList PL = new ProductList(ck);
				PL.setVisible(true);
				this.setVisible(false);
				PL.insertBtn.setEnabled(false); 
				PL.updateBtn.setEnabled(false); 
				PL.deleteBtn.setEnabled(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
		}
		
		else if(command.equals(SellsHistBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				SellsHistory SH = new SellsHistory(ck);
				SH.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 2)
			{
				SellsHistory SH = new SellsHistory(ck);
				SH.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 3)
			{
				SellsHistory SH = new SellsHistory(ck);
				SH.setVisible(true);
				this.setVisible(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
		}
		
		else if(command.equals(SellProdBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
			
			else if(ck.getSID() == 2)
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
			
			else if(ck.getSID() == 3)
			{
				SellProduct SP= new SellProduct(ck);
				SP.setVisible(true);
				this.setVisible(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
		}
		
		else if(command.equals(abtBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				AboutFrame PC= new AboutFrame(ck);
				PC.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 2)
			{
				AboutFrame PC= new AboutFrame(ck);
				PC.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 3)
			{
				AboutFrame PC= new AboutFrame(ck);
				PC.setVisible(true);
				this.setVisible(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}
		}
		
		else{}
	}
	
	public void mousePressed(MouseEvent me){}
	
	public void mouseReleased(MouseEvent me){}
	
	public void mouseClicked(MouseEvent me){}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == EmpListBtn)
		{
			EmpListBtn.setBackground(Color.WHITE);
			EmpListBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == ProdListBtn)
		{
			ProdListBtn.setBackground(Color.WHITE);
			ProdListBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == SellProdBtn)
		{
			if(ck.getSID() == 1)
			{
				SellProdBtn.setBackground(Color.RED);
				SellProdBtn.setForeground(Color.BLACK);
			}
			
			else if(ck.getSID() == 2)
			{
				SellProdBtn.setBackground(Color.RED);
				SellProdBtn.setForeground(Color.BLACK);
			}
			
			else
			{
				SellProdBtn.setBackground(Color.BLUE);
				SellProdBtn.setForeground(Color.BLACK);
			}
		}
		
		else if(me.getSource() == SellsHistBtn)
		{
			SellsHistBtn.setBackground(Color.WHITE);
			SellsHistBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == abtBtn)
		{
			abtBtn.setBackground(Color.BLUE);
			abtBtn.setForeground(Color.BLACK);
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
		if(me.getSource() == EmpListBtn)
		{
			EmpListBtn.setBackground(Color.DARK_GRAY);
			EmpListBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == ProdListBtn)
		{
			ProdListBtn.setBackground(Color.DARK_GRAY);
			ProdListBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == SellProdBtn)
		{
			SellProdBtn.setBackground(Color.DARK_GRAY);
			SellProdBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == SellsHistBtn)
		{
			SellsHistBtn.setBackground(Color.DARK_GRAY);
			SellsHistBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == abtBtn)
		{
			abtBtn.setBackground(Color.DARK_GRAY);
			abtBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.DARK_GRAY);
			LogoutBtn.setForeground(Color.WHITE);
		}		
	}	
}	
