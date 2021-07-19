package repository;

import java.lang.*;
import java.util.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class SalesRepo implements ISalesRepo
{
	DatabaseConnection dbc;
	
	public SalesRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertIntoDataBase(Sales s)
	{
		String query = "INSERT INTO `sales` VALUES ('"+s.getCID()+"','"+s.getNAME()+"','"+s.getCONTACT()+"','"+s.getPID()+"',"+s.getQUANTITY()+","+s.getSELLPRICE()+");";
		
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
	
	public void updateIntoDataBase(Product p)
	{
		Sales s;
		
		String query = "UPDATE `product` SET `QUANTITY` = "+p.getQUANTITY()+" WHERE `PID` = '"+p.getPID()+"';";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());    
		}
	}
	
	public Sales searchProduct(String CID)
	{
		Sales sales = null;
		String query = "SELECT `NAME`, `CONTACT`, `PID`,`QUANTITY`,`SELLPRICE` FROM `sales` WHERE `CID` = '"+CID+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String NAME = dbc.result.getString("NAME");
				int CONTACT = dbc.result.getInt("CONTACT");
				String PID = dbc.result.getString("PID");
				int QUANTITY = dbc.result.getInt("QUANTITY");
				double SELLPRICE = dbc.result.getDouble("SELLPRICE");
				
				sales = new Sales();
				sales.setCID(CID);
				sales.setNAME(NAME);
				sales.setCONTACT(CONTACT);
				sales.setPID(PID);
				sales.setQUANTITY(QUANTITY);
				sales.setSELLPRICE(SELLPRICE);
			}
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		dbc.closeConnection();
		return sales;
	}
	
	public String[][] getAllSales()
	{
		ArrayList<Sales> ar = new ArrayList<Sales>();
		String query = "SELECT * FROM sales;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String CID = dbc.result.getString("CID");
				String NAME = dbc.result.getString("NAME");
				int CONTACT = dbc.result.getInt("CONTACT");
				String PID = dbc.result.getString("PID");
				int QUANTITY = dbc.result.getInt("QUANTITY");
				double SELLPRICE = dbc.result.getDouble("SELLPRICE");
				
				Sales s = new Sales(CID,NAME,CONTACT,PID,QUANTITY,SELLPRICE);
				
				ar.add(s);
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][6];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Sales)obj[i]).getCID();
			data[i][1] = ((Sales)obj[i]).getNAME();
			data[i][2] = ((Sales)obj[i]).getCONTACT()+"";
			data[i][3] = ((Sales)obj[i]).getPID();
			data[i][4] = ((Sales)obj[i]).getQUANTITY()+"";        
			data[i][5] = ((Sales)obj[i]).getSELLPRICE()+"";
		}
		
		return data;
	}
}












































