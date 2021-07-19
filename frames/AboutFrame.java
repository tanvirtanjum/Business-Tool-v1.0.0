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


public class AboutFrame extends JFrame implements ActionListener, MouseListener
{
	JLabel empIdLabel, empNameLabel, empMobNoLabel, empSalaryLabel, empSIDLabel;
	
	JTextField empIdTF, empNameTF, empMobNoTF, empSalaryTF, empSIDTF;
	
	JButton passChngBtn, LogoutBtn, backBtn, loadBtn, refreshBtn;
	
	JPanel panel;
	
	Font f2, f3;
	
	Login ck;
	Login lg;
	
	EmployeeRepo er;
	
	LoginRepo lgr; 
	
	String id;
	int sid;
	
	public AboutFrame(Login ck)
	{
		super("About Me");
		this.setSize(500,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ck = ck;
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~ABOUT FRAME");
			
		lg = new Login();
		er = new EmployeeRepo();
		lgr = new LoginRepo();
		Employee e = new Employee();
		
		e = er.searchEmployee(id);
		
		f2 = new Font("Candara",Font.PLAIN,14);
		f3 = new Font("Consolas",Font.PLAIN,17);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		empIdLabel = new JLabel("USER ID.: ");
		empIdLabel.setBounds(100,100,100,30);
		empIdLabel.setFont(f2);
		empIdLabel.setBackground(Color.DARK_GRAY);
		empIdLabel.setForeground(Color.WHITE);
		empIdLabel.setOpaque(true);
		panel.add(empIdLabel);
		
		empIdTF = new JTextField();
		empIdTF.setBounds(220,100,200,30);
		empIdTF.setFont(f3);
		empIdTF.setText(id+"");
		empIdTF.setEditable(false);
		panel.add(empIdTF);
		
		empNameLabel = new JLabel("NAME: ");
		empNameLabel.setBounds(100,150,100,30);
		empNameLabel.setFont(f2);
		empNameLabel.setBackground(Color.DARK_GRAY);
		empNameLabel.setForeground(Color.WHITE);
		empNameLabel.setOpaque(true);
		panel.add(empNameLabel);
		
		empNameTF = new JTextField();
		empNameTF.setBounds(220,150,200,30);
		empNameTF.setFont(f3);
		empNameTF.setText(e.getNAME());
		empNameTF.setEditable(false);
		panel.add(empNameTF);
		
		empSIDLabel = new JLabel("DESIGNATION: ");
		empSIDLabel.setBounds(100,200,100,30);
		empSIDLabel.setFont(f2);
		empSIDLabel.setBackground(Color.DARK_GRAY);
		empSIDLabel.setForeground(Color.WHITE);
		empSIDLabel.setOpaque(true);
		panel.add(empSIDLabel);
		
		empSIDTF = new JTextField();
		empSIDTF.setBounds(220,200,200,30);
		empSIDTF.setFont(f3);
		if(e.getSID() == 1)
		{
			empSIDTF.setText("ADMIN");
		}
		else if(e.getSID() == 2)
		{
			empSIDTF.setText("MANAGER");
		}
		else if(e.getSID() == 3)
		{
			empSIDTF.setText("SALES-MAN");
		}
		else
		{
			empSIDTF.setText("OTHER");
		}
		empSIDTF.setEditable(false);
		panel.add(empSIDTF);
		
		empSalaryLabel = new JLabel("SALARY: ");
		empSalaryLabel.setBounds(100,250,100,30);
		empSalaryLabel.setFont(f2);
		empSalaryLabel.setBackground(Color.DARK_GRAY);
		empSalaryLabel.setForeground(Color.WHITE);
		empSalaryLabel.setOpaque(true);
		panel.add(empSalaryLabel);
		
		empSalaryTF = new JTextField();
		empSalaryTF.setBounds(220,250,200,30);
		empSalaryTF.setFont(f3);
		empSalaryTF.setText(e.getSALARY()+"");
		empSalaryTF.setEditable(false);
		panel.add(empSalaryTF);
		
		empMobNoLabel = new JLabel("CONTACT: ");
		empMobNoLabel.setBounds(100,300,100,30);
		empMobNoLabel.setFont(f2);
		empMobNoLabel.setBackground(Color.DARK_GRAY);
		empMobNoLabel.setForeground(Color.WHITE);
		empMobNoLabel.setOpaque(true);
		panel.add(empMobNoLabel);
		
		empMobNoTF = new JTextField();
		empMobNoTF.setBounds(220,300,200,30);
		empMobNoTF.setFont(f3);
		empMobNoTF.setText("+880"+e.getCONTACT()+"");
		empMobNoTF.setEditable(false);
		panel.add(empMobNoTF);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(130,390,80,30);
		backBtn.setFont(f2);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(this);
		backBtn.setEnabled(true);
		backBtn.addActionListener(this);
		panel.add(backBtn);
	
		passChngBtn = new JButton("Change Password");
		passChngBtn.setBounds(100,350,150, 30);
		passChngBtn.setFont(f2);
		passChngBtn.setBackground(Color.DARK_GRAY);
		passChngBtn.setForeground(Color.WHITE);
		passChngBtn.setOpaque(true);
		passChngBtn.addMouseListener(this);
		passChngBtn.setEnabled(true);
		passChngBtn.addActionListener(this);
		panel.add(passChngBtn);
		
		LogoutBtn = new JButton("Logout");
		LogoutBtn.setBounds(405, 425, 80, 30);
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
		
		else if(command.equals(passChngBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				PasswordChange PC= new PasswordChange(ck);
				PC.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 2)
			{
				PasswordChange PC= new PasswordChange(ck);
				PC.setVisible(true);
				this.setVisible(false);
			}
			
			else if(ck.getSID() == 3)
			{
				PasswordChange PC= new PasswordChange(ck);
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
	
	public void mouseClicked(MouseEvent me){}
	
	public void mousePressed(MouseEvent me){}
	
	public void mouseReleased(MouseEvent me){}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == loadBtn)
		{
			loadBtn.setBackground(Color.WHITE);
			loadBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == passChngBtn)
		{
			passChngBtn.setBackground(Color.WHITE);
			passChngBtn.setForeground(Color.BLACK);
			
		}
		
		else if(me.getSource() == backBtn)
		{
			backBtn.setBackground(Color.WHITE);
			backBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.RED);
			LogoutBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == refreshBtn)
		{
			refreshBtn.setBackground(Color.WHITE);
			refreshBtn.setForeground(Color.BLACK);
		}
		
		else{}
	}
	
	public void mouseExited(MouseEvent me)
	{	
		if(me.getSource() == loadBtn)
		{
			loadBtn.setBackground(Color.DARK_GRAY);
			loadBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == passChngBtn)
		{
			passChngBtn.setBackground(Color.DARK_GRAY);
			passChngBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == backBtn)
		{
			backBtn.setBackground(Color.DARK_GRAY);
			backBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == LogoutBtn)
		{
			LogoutBtn.setBackground(Color.DARK_GRAY);
			LogoutBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == refreshBtn)
		{
			refreshBtn.setBackground(Color.DARK_GRAY);
			refreshBtn.setForeground(Color.WHITE);
		}
		
		else{}
	}
}