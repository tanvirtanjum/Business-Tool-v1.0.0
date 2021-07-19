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

public class LoginFrame extends JFrame implements ActionListener,MouseListener
{
	JLabel title, LoginLabel, passLabel, imgLabel;
	
	JTextField LoginTF;
	
	JPasswordField passPF;
	
	JButton loginBtn, exitBtn, showPassBtn;
	
	JPanel panel;
	
	ImageIcon img;

	Font f1,f2,f3;
	
	Login login;
	
	public LoginFrame()
	{
		super("Business Tool - Login Window");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		login = new Login();
		
		f1 = new Font("Comic Sans MS",Font.ITALIC | Font.BOLD,20);
		f2 = new Font("Candara",Font.PLAIN,18);
		f3 = new Font("Consolas",Font.PLAIN | Font.BOLD,18);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		img = new ImageIcon("BUSINESS TOOL LOGO v1.0.0.jpg");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(72,110,250,250);
		panel.add(imgLabel);
		
		title = new JLabel("Sign In With Your Organization Id Number.");
		title.setBounds(350, 110, 500, 70);
		title.setFont(f1);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.WHITE);
		title.setOpaque(true);
		panel.add(title);
		
		LoginLabel = new JLabel("USER ID :");
		LoginLabel.setBounds(400, 200, 80, 30);
		LoginLabel.setFont(f2);
		LoginLabel.setBackground(Color.BLACK);
		LoginLabel.setForeground(Color.WHITE);
		LoginLabel.setOpaque(true);
		panel.add(LoginLabel);
		
		LoginTF = new JTextField();
		LoginTF.setBounds(500, 200, 200, 30);
		LoginTF.setFont(f3);
		LoginTF.setBackground(Color.WHITE);
		LoginTF.setForeground(Color.BLACK);
		LoginTF.setOpaque(true);
		panel.add(LoginTF);
		
		passLabel = new JLabel("PASSWORD :");
		passLabel.setBounds(400, 250, 100, 30);
		passLabel.setFont(f2);
		passLabel.setBackground(Color.BLACK);
		passLabel.setForeground(Color.WHITE);
		passLabel.setOpaque(true);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(500, 250, 200, 30);
		passPF.setFont(f3);
		passPF.setBackground(Color.WHITE);
		passPF.setForeground(Color.BLACK);
		passPF.setOpaque(true);
		passPF.setEchoChar('\u25CF');
		panel.add(passPF);
		
		showPassBtn = new JButton("Show");
		showPassBtn.setBounds(705,250,80,30);
		showPassBtn.setFont(f2);
		showPassBtn.setBackground(Color.BLACK);
		showPassBtn.setForeground(Color.WHITE);
		showPassBtn.setOpaque(true);
		showPassBtn.addMouseListener(this);
		panel.add(showPassBtn);
		
		loginBtn = new JButton("Log-In");
		loginBtn.setBounds(520, 300, 160, 30);
		loginBtn.setFont(f2);
		loginBtn.setBackground(Color.BLACK);
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setOpaque(true);
		loginBtn.addMouseListener(this);
		loginBtn.addActionListener(this);
		panel.add(loginBtn);
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(705, 425, 80, 30);
		exitBtn.setFont(f2);
		exitBtn.setBackground(Color.BLACK);
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setOpaque(true);
		exitBtn.addMouseListener(this);
		exitBtn.addActionListener(this);
		panel.add(exitBtn);	
		
		this.add(panel);
	}
			
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		passPF.setEchoChar('\u25CF');
		
		if(command.equals(loginBtn.getText()))
		{
			LoginRepo lg = new LoginRepo();
			login = lg.getLogin(LoginTF.getText(), passPF.getText());
			
			if(login != null)
			{
				passPF.setEchoChar('\u25CF');
				if(login.getSID() == 1)
				{
					MainFrame mf = new MainFrame(login);
					mf.setVisible(true);
					this.setVisible(false);
					mf.SellProdBtn.setEnabled(false);
				}
				
				else if(login.getSID() == 2)
				{
					MainFrame mf = new MainFrame(login);
					mf.setVisible(true);
					this.setVisible(false);
					mf.SellProdBtn.setEnabled(false);
				}
				
				else if(login.getSID() == 3)
				{
					MainFrame mf = new MainFrame(login);
					mf.setVisible(true);
					this.setVisible(false);
					mf.SellProdBtn.setEnabled(true);
				}
		
				else
				{
					passPF.setEchoChar('\u25CF');
					JOptionPane.showMessageDialog(this, "Invaild Id or Password");
					passPF.setEchoChar('\u25CF');
				}
			}
			else
			{
				passPF.setEchoChar('\u25CF');
				JOptionPane.showMessageDialog(this, "Invaild Id or Password");
				passPF.setEchoChar('\u25CF');
			}
						
		}
		
		else if(command.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		
		else{}
	}
	
	public void mouseClicked(MouseEvent me){}
	
	public void mousePressed(MouseEvent me)
	{
		passPF.setEchoChar((char)0);
	}
	
	public void mouseReleased(MouseEvent me)
	{
		passPF.setEchoChar('\u25CF');
	}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == loginBtn)
		{
			loginBtn.setBackground(Color.WHITE);
			loginBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == showPassBtn)
		{
			showPassBtn.setBackground(Color.WHITE);
			showPassBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == exitBtn)
		{
			exitBtn.setBackground(Color.RED);
			exitBtn.setForeground(Color.BLACK);
		}
		else{}
	}
	
	public void mouseExited(MouseEvent me)
	{	
		if(me.getSource() == loginBtn)
		{
			loginBtn.setBackground(Color.BLACK);
			loginBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == showPassBtn)
		{
			showPassBtn.setBackground(Color.BLACK);
			showPassBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == exitBtn)
		{
			exitBtn.setBackground(Color.BLACK);
			exitBtn.setForeground(Color.WHITE);
		}
		
		else{}
	}
}