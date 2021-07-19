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

public class PasswordChange extends JFrame implements ActionListener, MouseListener
{
	JPanel panel;
	
	JButton  backBtn, allshowPassBtn, saveBtn; 
	
	JLabel  userLabel,oldPassLabel,newpassLabel, confirmpassLabel;
	
	JTextField userTF;
	
	JPasswordField newpassPF, confirmpassPF, oldPassPF;
	
	String id;
	int sid;
	
	Font f2,f3;
	
	Login ck;
	Login login;
	
	LoginRepo lr;

	public PasswordChange(Login ck)
	{
		super("Change Password");
		this.setSize(500,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ck = ck;
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~PASSWORD-CHANGE FRAME");
		
		login = new Login();
		lr = new LoginRepo();
		f2 = new Font("Candara",Font.PLAIN,14);
		f3 = new Font("Consolas",Font.PLAIN,17);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		userLabel = new JLabel("USER ID:");
		userLabel.setBounds(100,50,130,30);
		userLabel.setFont(f2);
		userLabel.setBackground(Color.DARK_GRAY);
		userLabel.setForeground(Color.WHITE);
		panel.add(userLabel);
		
		userTF = new JTextField();
		userTF.setBounds(220,50,120,30);
		userTF.setOpaque(true);
		userTF.setFont(f3);
		userTF.setText(ck.getUID()+"");
		userTF.setEditable(false);
		panel.add(userTF);
		
		oldPassLabel = new JLabel("Old Password: ");
		oldPassLabel.setBounds(100,100,100,30);
		oldPassLabel.setFont(f2);
		oldPassLabel.setBackground(Color.DARK_GRAY);
		oldPassLabel.setForeground(Color.WHITE);
		oldPassLabel.setOpaque(true);
		panel.add(oldPassLabel);
		
		oldPassPF = new JPasswordField();
		oldPassPF.setBounds(220,100,120,30);
		oldPassPF.setFont(f3);
		oldPassPF.setEchoChar('\u25CF');
		panel.add(oldPassPF);
		
		allshowPassBtn = new JButton("Show");
		allshowPassBtn.setBounds(350,200,80,30); 
		allshowPassBtn.setFont(f2);
		allshowPassBtn.setBackground(Color.DARK_GRAY);
		allshowPassBtn.setForeground(Color.WHITE);
		allshowPassBtn.setOpaque(true);
		allshowPassBtn.addMouseListener(this);
		allshowPassBtn.setEnabled(true);
		allshowPassBtn.addMouseListener(this);
		panel.add(allshowPassBtn);
		
		newpassLabel = new JLabel("New Password: ");
		newpassLabel.setBounds(100,150,100,30);
		newpassLabel.setFont(f2);
		newpassLabel.setBackground(Color.DARK_GRAY);
		newpassLabel.setForeground(Color.WHITE);
		newpassLabel.setOpaque(true);
		panel.add(newpassLabel);
		
		newpassPF = new JPasswordField();
		newpassPF.setBounds(220,150,120,30);
		newpassPF.setEchoChar('\u25CF');
		newpassPF.setFont(f3);
		panel.add(newpassPF);
		
		confirmpassLabel = new JLabel("Confirm Password: ");
		confirmpassLabel.setBounds(100,200,130,30);
		confirmpassLabel.setFont(f2);
		confirmpassLabel.setBackground(Color.DARK_GRAY);
		confirmpassLabel.setForeground(Color.WHITE);
		confirmpassLabel.setOpaque(true);
		panel.add(confirmpassLabel);
		
		confirmpassPF = new JPasswordField();
		confirmpassPF.setBounds(220,200,120,30);
		confirmpassPF.setEchoChar('\u25CF');
		confirmpassPF.setFont(f3);
		panel.add(confirmpassPF);
		
		
		backBtn = new JButton("Back");
		backBtn.setBounds(100,300,80,30);
		backBtn.setFont(f2);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(this);
		backBtn.setEnabled(true);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		saveBtn=  new JButton("Save Change");
		saveBtn.setBounds(100,250,120,30);
		saveBtn.setFont(f2);
		saveBtn.setBackground(Color.DARK_GRAY);
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setOpaque(true);
		saveBtn.addMouseListener(this);
		saveBtn.setEnabled(true);
		saveBtn.addActionListener(this);
		panel.add(saveBtn);
		
			
		
		this.add(panel);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		if(command.equals(backBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				AboutFrame MF = new AboutFrame(ck);
				MF.setVisible(true);
				this.setVisible(false);				
			}
			
			else if(ck.getSID() == 2)
			{
				AboutFrame MF = new AboutFrame(ck);
				MF.setVisible(true);
				this.setVisible(false);		
			}
			
			else if(ck.getSID() == 3)
			{
				AboutFrame MF = new AboutFrame(ck);
				MF.setVisible(true);
				this.setVisible(false);
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "ACCESS DENIED");
			}						
		}

		else if(command.equals(saveBtn.getText()))
		{
			newpassPF.setEchoChar('\u25CF');
			Login lck;
			
			String newPass = newpassPF.getText();
			int newPassLength = newPass.length();
			
			lck = lr.srchLogin(oldPassPF.getText(),userTF.getText());
			
			if(lck != null)
			{
				if(newPassLength >= 4)
				{
					if((newpassPF.getText().equals(confirmpassPF.getText()))  && (!newpassPF.getText().equals(oldPassPF.getText())))
					{
						Login l = new Login();
						l.setUID(userTF.getText());
						l.setPASS(newpassPF.getText());
						
						lr.updateLoginPass(l);
						
						try
						{
							String write = "Id: "+id+"\nPassword: "+newpassPF.getText();
							String loc = "C:\\Users\\tanju\\Desktop\\BT Updated - Copy\\Data\\Password";
							String FileName = id+".txt";
							
							File file = new File(loc);
							FileWriter writer = new FileWriter(new File(file,FileName));
							file.createNewFile();
							writer.write(write); 
							writer.flush();
							writer.close();
							
							JOptionPane.showMessageDialog(this, "PASSWORD CHANGED. PLEASE, LOG-IN WITH NEW PASSWORD.");
						}
						
						catch(Exception ex)
						{
							System.out.println("FILE NOT CREATED");
						}
						
						LoginFrame LF = new LoginFrame();
						this.setVisible(false);
						LF.setVisible(true);
					}
					
					else if(newpassPF.getText().equals(oldPassPF.getText()))
					{
						oldPassPF.setEchoChar('\u25CF');
						newpassPF.setEchoChar('\u25CF');
						confirmpassPF.setEchoChar('\u25CF');
						
						newpassPF.setText("");
						confirmpassPF.setText("");
						
						JOptionPane.showMessageDialog(this, "This Password is Already Assigned To This Account. Try Different One.");
					}
					
					else
					{
						oldPassPF.setEchoChar('\u25CF');
						newpassPF.setEchoChar('\u25CF');
						confirmpassPF.setEchoChar('\u25CF');

						newpassPF.setText("");
						confirmpassPF.setText("");
						
						JOptionPane.showMessageDialog(this, "New Password and Confirm Password Mis-matched");
					}
				}
			
			    else
				{
					JOptionPane.showMessageDialog(this, "Password too short. New password must have minimum 4 digits. Please, insert again");
				}
			}
			
			else
			{
				oldPassPF.setText("");
				
				oldPassPF.setEchoChar('\u25CF');
				newpassPF.setEchoChar('\u25CF');
				confirmpassPF.setEchoChar('\u25CF');
				
				JOptionPane.showMessageDialog(this, "Re-type Old Password");
			}
			
		}
		
		else{}
	}
	
	public void mouseClicked(MouseEvent me){}
	
	public void mousePressed(MouseEvent me)
	{
		newpassPF.setEchoChar((char)0);
		oldPassPF.setEchoChar((char)0);
		confirmpassPF.setEchoChar((char)0);
	}
	public void mouseReleased(MouseEvent me)
	{
		newpassPF.setEchoChar('\u25CF');
		oldPassPF.setEchoChar('\u25CF');
		confirmpassPF.setEchoChar('\u25CF');	
	}
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == saveBtn)
		{
			saveBtn.setBackground(Color.GREEN);
			saveBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == backBtn)
		{
			backBtn.setBackground(Color.BLUE); 
			backBtn.setForeground(Color.BLACK);
		}
		
		else if(me.getSource() == allshowPassBtn)
		{
			allshowPassBtn.setBackground(Color.WHITE);  
			allshowPassBtn.setForeground(Color.BLACK);
		}
		
		else{}
	}
	
	public void mouseExited(MouseEvent me)
	{	
		if(me.getSource() == saveBtn)
		{
			saveBtn.setBackground(Color.DARK_GRAY);
			saveBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == backBtn)
		{
			backBtn.setBackground(Color.DARK_GRAY);
			backBtn.setForeground(Color.WHITE);
		}
		
		else if(me.getSource() == allshowPassBtn)
		{
			allshowPassBtn.setBackground(Color.DARK_GRAY);  
			allshowPassBtn.setForeground(Color.WHITE);
		}
		
		else{}
	}
}	