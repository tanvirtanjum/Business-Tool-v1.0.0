package repository;

import java.lang.*;
import java.util.*;

import entity.*;
import interfaces.*;

public class LoginRepo implements ILoginRepo
{
	DatabaseConnection dbc;
	
	public LoginRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public Login getLogin(String UID, String PASS)
	{
		Login login = null;
		String query = "SELECT UID,SID,PASS FROM login WHERE UID='"+UID+"' AND PASS= '"+PASS+"';";
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				login = new Login();
				login.setUID(dbc.result.getString("UID"));
				login.setSID(dbc.result.getInt("SID"));
				login.setPASS(dbc.result.getString("PASS"));
			}
		}
		
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		dbc.closeConnection();
		
		return login;
	}
	
	public Login srchLogin(String UID)
	{
		Login login = null;
		String query = "SELECT UID,SID,PASS FROM login WHERE UID='"+UID+"';";
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				login = new Login();
				login.setUID(dbc.result.getString("UID"));
				login.setSID(dbc.result.getInt("SID"));
				login.setPASS(dbc.result.getString("PASS"));
			}
		}
		
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		dbc.closeConnection();
		
		return login;
	}
	
	public Login srchLogin(String PASS, String UID)
	{
		Login login = null;
		String query = "SELECT UID,SID,PASS FROM login WHERE PASS='"+PASS+"' AND UID='"+UID+"';";
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				login = new Login();
				login.setUID(dbc.result.getString("UID"));
				login.setSID(dbc.result.getInt("SID"));
				login.setPASS(dbc.result.getString("PASS"));
			}
		}
		
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		dbc.closeConnection();
		
		return login;
	}
	
	public void insertLogin(Login l)
	{
		String query = "INSERT INTO login VALUES ('"+l.getUID()+"',"+l.getSID()+",'"+l.getPASS()+"');";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void updateLogin(Login l)
	{
		String query = "UPDATE login SET SID = '"+l.getSID()+"' WHERE `UID` = '"+l.getUID()+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void updateLoginPass(Login l)
	{
		String query = "UPDATE login SET `PASS` = '"+l.getPASS()+"' WHERE `UID` = '"+l.getUID()+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void deleteLogin(String UID)
	{
		String query = "DELETE from login  WHERE UID='"+UID+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public String checkPass(String UID)
	{
		Login login = null;
		String query = "SELECT `PASS` WHERE `UID`='"+UID+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String PASS = dbc.result.getString("PASS");
				
				login = new Login();
				login.setPASS(PASS);
			}
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		dbc.closeConnection();
		return login.getPASS();
	}
}