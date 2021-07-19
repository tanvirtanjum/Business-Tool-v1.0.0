package repository;

import java.lang.*;
import java.util.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class ProductRepo implements IProductRepo
{
	DatabaseConnection dbc;
	
	public ProductRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertIntoDataBase(Product p)
	{
		String query = "INSERT INTO product VALUES ('"+p.getPID()+"','"+p.getNAME()+"','"+p.getTYPE()+"',"+p.getQUANTITY()+","+p.getBUYPRICE()+","+p.getSELLPRICE()+");";
		
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
	
	public void deleteFromDataBase(String PID)
	{
		String query = "DELETE from product WHERE PID= '"+PID+"';";
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
	
	public void updateIntoDataBase(Product p)
	{
		String query = "UPDATE `product` SET `NAME` = '"+p.getNAME()+"', `TYPE` ='"+p.getTYPE()+"',`QUANTITY` = '"+p.getQUANTITY()+"',`BUYPRICE` = '"+p.getBUYPRICE()+"', `SELLPRICE` = '"+p.getSELLPRICE()+"' WHERE `PID` = '"+p.getPID()+"';";
		
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
	
	public Product searchProduct(String PID)
	{
		Product product = null;
		String query = "SELECT `NAME`, `TYPE`, `QUANTITY`,`BUYPRICE`,`SELLPRICE` FROM `product` WHERE `PID` = '"+PID+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String NAME = dbc.result.getString("NAME");
				String TYPE = dbc.result.getString("TYPE");
				int QUANTITY = dbc.result.getInt("QUANTITY");
				double BUYPRICE = dbc.result.getDouble("BUYPRICE");
				double SELLPRICE = dbc.result.getDouble("SELLPRICE");
				
				product = new Product();
				product.setPID(PID);
				product.setNAME(NAME);
				product.setTYPE(TYPE);
				product.setQUANTITY(QUANTITY);
				product.setBUYPRICE(BUYPRICE);
				product.setSELLPRICE(SELLPRICE);
			}
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		dbc.closeConnection();
		return product;
	}
	
	public int searchQuantity(String PID)
	{
		Product product = null;
		String query = "SELECT `QUANTITY` FROM `product` WHERE `PID` = '"+PID+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				int QUANTITY = dbc.result.getInt("QUANTITY");
				
				product = new Product();
				product.setPID(PID);
				product.setQUANTITY(QUANTITY);
				
			}
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		dbc.closeConnection();
		return product.getQUANTITY();
	}
	
	public double searchBuyPrice(String PID)
	{
		Product product = null;
		String query = "SELECT `BUYPRICE` FROM `product` WHERE `PID` = '"+PID+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				double BUYPRICE = dbc.result.getInt("BUYPRICE");
				
				product = new Product();
				product.setPID(PID);
				product.setBUYPRICE(BUYPRICE);
				
			}
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		dbc.closeConnection();
		return product.getBUYPRICE();
	}
	
	
	public String[][] getAllProduct()
	{
		ArrayList<Product> ar = new ArrayList<Product>();
		String query = "SELECT * FROM product;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String PID = dbc.result.getString("PID");
				String NAME = dbc.result.getString("NAME");
				String TYPE = dbc.result.getString("TYPE");
				int QUANTITY = dbc.result.getInt("QUANTITY");
				double BUYPRICE = dbc.result.getDouble("BUYPRICE");
				double SELLPRICE = dbc.result.getDouble("SELLPRICE");
				
				Product p = new Product(PID,NAME,TYPE,QUANTITY,BUYPRICE,SELLPRICE);
				
				ar.add(p);
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
			data[i][0] = ((Product)obj[i]).getPID();
			data[i][1] = ((Product)obj[i]).getNAME();
			data[i][2] = ((Product)obj[i]).getTYPE();
			data[i][3] = ((Product)obj[i]).getQUANTITY()+"";
			data[i][4] = ((Product)obj[i]).getBUYPRICE()+"";
			data[i][5] = ((Product)obj[i]).getSELLPRICE()+"";
		}
		
		return data;
	}
}












































