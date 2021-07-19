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


public class EmployeeList extends JFrame implements ActionListener, MouseListener
{
	JLabel empIdLabel, empNameLabel, empMobNoLabel, empSalaryLabel, empSIDLabel;
	
	JTextField empIdTF, empNameTF, empMobNoTF, MobileExTF, empSalaryTF /*, empSIDTF*/;
	
	JButton srchBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, LogoutBtn, backBtn, showTableBtn;
	
	JComboBox combo;
	
	JPanel panel;
	
	JTable empTable;
	
	JScrollPane empTableSP;
	
	Font f2,f3;
	
	Login ck;
	Login lg;
	
	EmployeeRepo er;
	
	LoginRepo lgr; 
	
	String id;
	int sid;
	
	public EmployeeList(Login ck)
	{
		super("Employee Details");
		this.setSize(800,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ck = ck;
		
		id = ck.getUID();
		sid = ck.getSID();
		System.out.println ("ID: "+id+" SID: "+sid+"~EMPLOYEE-LIST FRAME");
		
		lg = new Login();
		er = new EmployeeRepo();
		lgr = new LoginRepo();
		
		f2 = new Font("Candara",Font.PLAIN,14);
		f3 = new Font("Consolas",Font.PLAIN,17);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		String data[][] = {{"", "", "", "", ""}};
		
		String head[] = {"UID", "NAME", "SID", "SALARY", "CONTACT"};
		
		empTable = new JTable(data,head);
		empTableSP = new JScrollPane(empTable);
		empTableSP.setBounds(350, 110, 400, 170);
		empTable.setFont(f3);
		empTableSP.setFont(f3);
		empTable.setEnabled(false);
		panel.add(empTableSP);
		
		empIdLabel = new JLabel("USER ID.: ");
		empIdLabel.setBounds(100,100,100,30);
		empIdLabel.setFont(f2);
		empIdLabel.setBackground(Color.DARK_GRAY);
		empIdLabel.setForeground(Color.WHITE);
		panel.add(empIdLabel);
		
		empIdTF = new JTextField();
		empIdTF.setBounds(220,100,100,30);
		empIdTF.setFont(f3);
		panel.add(empIdTF);
		
		empNameLabel = new JLabel("NAME:");
		empNameLabel.setBounds(100,150,100,30);
		empNameLabel.setFont(f2);
		empNameLabel.setBackground(Color.DARK_GRAY);
		empNameLabel.setForeground(Color.WHITE);
		empNameLabel.setOpaque(true);
		panel.add(empNameLabel);
		
		empNameTF = new JTextField();
		empNameTF.setBounds(220,150,100,30);
		empNameTF.setFont(f3);
		panel.add(empNameTF);
		
		empSIDLabel = new JLabel("DESIGNATION:");
		empSIDLabel.setBounds(100,200,100,30);
		empSIDLabel.setFont(f2);
		empSIDLabel.setBackground(Color.DARK_GRAY);
		empSIDLabel.setForeground(Color.WHITE);
		empSIDLabel.setOpaque(true);
		panel.add(empSIDLabel);
		
		/*empSIDTF = new JTextField();
		empSIDTF.setBounds(220,200,30,30);
		empSIDTF.setFont(f3);
		empSIDTF.setEnabled(false);
		panel.add(empSIDTF);*/
		
		String item[] = {"--Select--","ADMIN", "MANAGER", "SALES-MAN"} ;
		combo = new JComboBox(item);
		//combo.setBounds(260,200,80,30);
		combo.setBounds(220,200,100,30);
		combo.addActionListener(this);
		panel.add(combo);
		
		empSalaryLabel = new JLabel("SALARY:");
		empSalaryLabel.setBounds(100,250,100,30);
		empSalaryLabel.setFont(f2);
		empSalaryLabel.setBackground(Color.DARK_GRAY);
		empSalaryLabel.setForeground(Color.WHITE);
		empSalaryLabel.setOpaque(true);
		panel.add(empSalaryLabel);
		
		empSalaryTF = new JTextField();
		empSalaryTF.setBounds(220,250,100,30);
		empSalaryTF.setFont(f3);
		panel.add(empSalaryTF);
		
		empMobNoLabel = new JLabel("CONTACT:");
		empMobNoLabel.setBounds(100,300,100,30);
		empMobNoLabel.setFont(f2);
		empMobNoLabel.setBackground(Color.DARK_GRAY);
		empMobNoLabel.setForeground(Color.WHITE);
		empMobNoLabel.setOpaque(true);
		panel.add(empMobNoLabel);
		
		MobileExTF = new JTextField();
		MobileExTF.setBounds(210,300,45,30);
		MobileExTF.setText("+880");
		MobileExTF.setEditable(false);
		MobileExTF.setFont(f3);
		panel.add(MobileExTF);
	
		empMobNoTF = new JTextField();
		empMobNoTF.setBounds(250,300,100,30);
		empMobNoTF.setFont(f3);
		panel.add(empMobNoTF);
		
		
		srchBtn = new JButton("Search");
		srchBtn.setBounds(100,350,80,30);
		srchBtn.setFont(f2);
		srchBtn.setBackground(Color.DARK_GRAY);
		srchBtn.setForeground(Color.WHITE);
		srchBtn.setOpaque(true);
		srchBtn.addMouseListener(this);
		srchBtn.setEnabled(true);
		srchBtn.addActionListener(this);
		panel.add(srchBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,350,80,30);
		insertBtn.setFont(f2);
		insertBtn.setBackground(Color.DARK_GRAY);
		insertBtn.setForeground(Color.WHITE);
		insertBtn.setOpaque(true);
		insertBtn.addMouseListener(this);
		insertBtn.setEnabled(true);
		insertBtn.addActionListener(this);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,350,80,30);
		updateBtn.setFont(f2);
		updateBtn.setBackground(Color.DARK_GRAY);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setOpaque(true);
		updateBtn.addMouseListener(this);
		updateBtn.setEnabled(false);;
		updateBtn.addActionListener(this);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400,350,80,30);
		deleteBtn.setFont(f2);
		deleteBtn.setBackground(Color.DARK_GRAY);
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setOpaque(true);
		deleteBtn.addMouseListener(this);
		deleteBtn.setEnabled(false);
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(500,300,80,30);
		refreshBtn.setFont(f2);
		refreshBtn.setBackground(Color.DARK_GRAY);
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setOpaque(true);
		refreshBtn.addMouseListener(this);
		refreshBtn.setEnabled(true);
		refreshBtn.addActionListener(this);
		panel.add(refreshBtn);
		
		showTableBtn = new JButton("Show Table");
		showTableBtn.setBounds(600, 300, 120, 30);
		showTableBtn.setFont(f2);
		showTableBtn.setBackground(Color.DARK_GRAY);
		showTableBtn.setForeground(Color.WHITE);
		showTableBtn.setOpaque(true);
		showTableBtn.addMouseListener(this);
		showTableBtn.setEnabled(true);
		showTableBtn.addActionListener(this);
		panel.add(showTableBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(500, 350, 80, 30);
		backBtn.setFont(f2);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setForeground(Color.WHITE);
		backBtn.setOpaque(true);
		backBtn.addMouseListener(this);
		backBtn.setEnabled(true);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		
		LogoutBtn = new JButton("Logout");
		LogoutBtn.setBounds(705, 425, 80, 30);
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
		
		else if(command.equals(srchBtn.getText()))
		{
			if(!empIdTF.getText().equals("") || !empIdTF.getText().equals(null))
			{
				Employee e = er.searchEmployee(empIdTF.getText());
				
				if(e != null)
				{
					empNameTF.setText(e.getNAME());
					//empSIDTF.setText(e.getSID()+"");
					if(e.getSID()==1)
					{
						combo.setSelectedIndex(1);
					}
					else if(e.getSID() == 2)
					{
						combo.setSelectedIndex(2);
					}
					else if(e.getSID() == 3)
					{
						combo.setSelectedIndex(3);
					}
					else
					{
						combo.setSelectedIndex(0);
					}
					empSalaryTF.setText(e.getSALARY()+"");
					empMobNoTF.setText(e.getCONTACT()+"");
					
					empIdTF.setEditable(false);
					empNameTF.setEditable(true);
					//empSIDTF.setEnabled(false);
					empSalaryTF.setEditable(true);
					empMobNoTF.setEditable(true);
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					srchBtn.setEnabled(false);
				
				}
				
				else
				{
					JOptionPane.showMessageDialog(this,"INVALID ID. FILL CORRECTLY");
				}
			}
		}
		
		else if(command.equals(showTableBtn.getText()))
		{
			String data[][] = er.getAllEmployee();
			String head[] = {"UID", "NAME", "SID", "SALARY", "CONTACT"};
			
			panel.remove(empTableSP);
			
			empTable = new JTable(data,head);
			empTable.setEnabled(false);
			empTableSP = new JScrollPane(empTable);
			empTableSP.setBounds(350, 100, 400, 150);
			empTable.setFont(f3);
			empTableSP.setFont(f3);
			panel.add(empTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		
		else if(command.equals(insertBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				/*String Status = combo.getSelectedItem().toString();
				
				if(Status.equals("ADMIN"))
				{
					empSIDTF.setText("1");
				}
				
				else if(Status.equals("MANAGER"))
				{
					empSIDTF.setText("2");
				}
				
				else if(Status.equals("SALES-MAN"))
				{
					empSIDTF.setText("3");
				}
				
				else
				{
					empSIDTF.setText("");
				}*/
				
				if((!empIdTF.getText().equals("") || !empIdTF.getText().equals(null)) && (combo.getSelectedIndex() == 1 || combo.getSelectedIndex() == 2 || combo.getSelectedIndex() == 3) && (combo.getSelectedIndex() != 0) /*(empSIDTF.getText().equals("1") || empSIDTF.getText().equals("2") || empSIDTF.getText().equals("3"))*/&& (!empSalaryTF.getText().equals("") || !empSalaryTF.getText().equals(null)) && (!empMobNoTF.getText().equals("") || !empMobNoTF.getText().equals(null)))
				{
					Login lck;
					lck = lgr.srchLogin(empIdTF.getText());
					if(lck == null) 
					{
						try
						{
							Employee e = new Employee();
							Login lg = new Login();
							Random rd = new Random();
							int x = rd.nextInt(9999999)+10000000;
							
							e.setUID(empIdTF.getText());
							e.setNAME(empNameTF.getText());
							//e.setSID(Integer.parseInt(empSIDTF.getText()));
							e.setSID(combo.getSelectedIndex());
							e.setSALARY(Double.parseDouble(empSalaryTF.getText()));
							e.setCONTACT(Integer.parseInt(empMobNoTF.getText()));
							
							lg.setUID(empIdTF.getText());
							//lg.setSID(Integer.parseInt(empSIDTF.getText()));
							lg.setSID(combo.getSelectedIndex());
							lg.setPASS(x+"");
							
							er.insertIntoDataBase(e);
							lgr.insertLogin(lg);
							
							JOptionPane.showMessageDialog(this, "Inserted, Id: "+empIdTF.getText()+" and Password: "+x);
							
							/*try
							{
								String s = "Id: "+empIdTF.getText()+"\nPassword: "+x;
								File file = new File(""+empIdTF.getText()+".txt");
								FileWriter writer = new FileWriter(file);
								file.createNewFile();
								writer.write(s); 
								writer.flush();
								writer.close();
							}*/
							
							try
							{
								String write = "Id: "+empIdTF.getText()+"\nPassword: "+x;
								String loc = "C:\\Users\\tanju\\Desktop\\BT Updated - Copy\\Data\\Password";
								String FileName = empIdTF.getText()+".txt";
								
								File file = new File(loc);
								FileWriter writer = new FileWriter(new File(file,FileName));
								file.createNewFile();
								writer.write(write); 
								writer.flush();
								writer.close();
							}
							
							catch(Exception ex)
							{
								System.out.println("NOT CREATED");
							}
							
							empIdTF.setText("");
							empNameTF.setText("");
							//empSIDTF.setText("");
							combo.setSelectedIndex(0);
							empSalaryTF.setText("");
							empMobNoTF.setText("");
							
							srchBtn.setEnabled(true);
							insertBtn.setEnabled(true);
							updateBtn.setEnabled(false);
							deleteBtn.setEnabled(false);
							refreshBtn.setEnabled(false);
						}
						
						catch(Exception ex)
						{
							empIdTF.setText("");
							empNameTF.setText("");
							//empSIDTF.setText("");
							combo.setSelectedIndex(0);
							empSalaryTF.setText("");
							empMobNoTF.setText("");
							
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
						    empIdTF.setText("");
							empNameTF.setText("");
							//empSIDTF.setText("");
							combo.setSelectedIndex(0);
							empSalaryTF.setText("");
							empMobNoTF.setText("");
							
							srchBtn.setEnabled(true);
							insertBtn.setEnabled(true);
							updateBtn.setEnabled(false);
							deleteBtn.setEnabled(false);
							refreshBtn.setEnabled(false);
						
						JOptionPane.showMessageDialog(this,"Invalid ID.");
					}
				}
				
				else
				{
					empIdTF.setText("");
					empNameTF.setText("");
					//empSIDTF.setText("");
					combo.setSelectedIndex(0);
					empSalaryTF.setText("");
					empMobNoTF.setText("");
					
					srchBtn.setEnabled(true);
					insertBtn.setEnabled(true);
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
					refreshBtn.setEnabled(false);
					
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
			empIdTF.setText("");
			empNameTF.setText("");
			//empSIDTF.setText("");
			combo.setSelectedIndex(0);
			empSalaryTF.setText("");
			empMobNoTF.setText("");
			
			empIdTF.setEditable(true);
			
			srchBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		
		else if(command.equals(updateBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				if(ck.getSID() == 1)
				{
					/*String Status = combo.getSelectedItem().toString();
					
					if(Status.equals("ADMIN"))
					{
						empSIDTF.setText("1");
					}
					
					else if(Status.equals("MANAGER"))
					{
						empSIDTF.setText("2");
					}
					
					else if(Status.equals("SALES-MAN"))
					{
						empSIDTF.setText("3");
					}
					
					else
					{
						empSIDTF.setText("");
					}*/
					if((!empIdTF.getText().equals("") || !empIdTF.getText().equals(null)) && (combo.getSelectedIndex() == 1 || combo.getSelectedIndex() == 2 || combo.getSelectedIndex() == 3) && (combo.getSelectedIndex() != 0) /*(empSIDTF.getText().equals("1") || empSIDTF.getText().equals("2") || empSIDTF.getText().equals("3"))*/&& (!empSalaryTF.getText().equals("") || !empSalaryTF.getText().equals(null)) && (!empMobNoTF.getText().equals("") || !empMobNoTF.getText().equals(null)))
					{
						Employee e = new Employee();
						Login lg = new Login();
						
						e.setUID(empIdTF.getText());
						e.setNAME(empNameTF.getText());
						//e.setSID(Integer.parseInt(empSIDTF.getText()));
						e.setSID(combo.getSelectedIndex());
						e.setSALARY(Double.parseDouble(empSalaryTF.getText()));
						e.setCONTACT(Integer.parseInt(empMobNoTF.getText()));
						
						lg.setUID(empIdTF.getText());
						//lg.setSID(Integer.parseInt(empSIDTF.getText()));
						lg.setSID(combo.getSelectedIndex());
						
						String nid;
						nid = e.getUID();
						
						int nsid;
						nsid = e.getSID();
						
						if(id.equals(nid) && sid != nsid)
						{
							lgr.updateLogin(lg);
							er.updateIntoDataBase(e);
							
							JOptionPane.showMessageDialog(this, "Updated");
							
							LoginFrame l = new LoginFrame();
							l.setVisible(true);
							this.setVisible(false);
						}
						
						else
						{
							lgr.updateLogin(lg);
							er.updateIntoDataBase(e);
							
							JOptionPane.showMessageDialog(this, "Updated");
							
							empIdTF.setText("");
							empNameTF.setText("");
							//empSIDTF.setText("");
							combo.setSelectedIndex(0);
							empSalaryTF.setText("");
							empMobNoTF.setText("");
							
							srchBtn.setEnabled(true);
							insertBtn.setEnabled(true);
							updateBtn.setEnabled(false);
							deleteBtn.setEnabled(false);
							refreshBtn.setEnabled(false);
							
							empIdTF.setEditable(true);
							empNameTF.setEditable(true);
							//empSIDTF.setEnabled(false);
							empSalaryTF.setEditable(true);
							empMobNoTF.setEditable(true);
						}
						

				    }
				
					else
					{
						JOptionPane.showMessageDialog(this, "FILL CORRECTLY");
						
						empIdTF.setText("");
						empNameTF.setText("");
						//empSIDTF.setText("");
						combo.setSelectedIndex(0);
						empSalaryTF.setText("");
						empMobNoTF.setText("");
						
						srchBtn.setEnabled(true);
						insertBtn.setEnabled(true);
						updateBtn.setEnabled(false);
						deleteBtn.setEnabled(false);
						refreshBtn.setEnabled(false);
						
						empIdTF.setEditable(true);
						empNameTF.setEditable(true);
						//empSIDTF.setEnabled(false);
						empSalaryTF.setEditable(true);
						empMobNoTF.setEditable(true);	
					}
			    }
		    }
			
			else
			{
				JOptionPane.showMessageDialog(this,"ACCESS DENIED");		
			}	
		}
		
		else if(command.equals(deleteBtn.getText()))
		{
			if(ck.getSID() == 1)
			{
				String nid;
				nid = empIdTF.getText();
				
				if(id.equals(nid))
				{
					JOptionPane.showMessageDialog(this,"Self-Destroy Not Allowed");
				}
				
				else
				{
					er.deleteFromDataBase(empIdTF.getText());
					lgr.deleteLogin(empIdTF.getText());
					
					try
					{
						String loc = "C:\\Users\\tanju\\Desktop\\BT Updated - Copy\\Data\\Password\\"+empIdTF.getText()+".txt";
						
						File file = new File(loc);
						file.delete();
						JOptionPane.showMessageDialog(this,"Deleted");
					}
					
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(this,"DONE");
					}
					
					empIdTF.setText("");
					empNameTF.setText("");
					//empSIDTF.setText("");
					combo.setSelectedIndex(0);
					empSalaryTF.setText("");
					empMobNoTF.setText("");
					
					empIdTF.setEditable(true);
					empNameTF.setEditable(true);
					//empSIDTF.setEnabled(false);
					empSalaryTF.setEditable(true);
					empMobNoTF.setEditable(true);

					srchBtn.setEnabled(true);
					insertBtn.setEnabled(true);
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
					refreshBtn.setEnabled(false);	
				}
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
			if(ck.getSID() == 1)
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
			if(ck.getSID() == 1)
			{
				updateBtn.setBackground(Color.WHITE);
				updateBtn.setForeground(Color.BLACK);
			}
			
			else
			{
				updateBtn.setBackground(Color.WHITE);
				updateBtn.setForeground(Color.BLACK);
			}
		}
		
		else if(me.getSource() == deleteBtn)
		{
			if(ck.getSID() == 1)
			{
				deleteBtn.setBackground(Color.RED);
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