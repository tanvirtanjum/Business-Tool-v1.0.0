package repository;

import java.lang.*;
import java.util.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class EmployeeRepo implements IEmployeeRepo
{
	DatabaseConnection dbc;
	
	public EmployeeRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertIntoDataBase(Employee e)
	{
		String query = "INSERT INTO employee VALUES ('"+e.getUID()+"','"+e.getNAME()+"',"+e.getSID()+","+e.getSALARY()+","+e.getCONTACT()+");";
		
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
	
	public void deleteFromDataBase(String UID)
	{
		String query = "DELETE from employee WHERE UID = '"+UID+"';";
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
	
	public void updateIntoDataBase(Employee e)
	{
		String query = "UPDATE `employee` SET NAME='"+e.getNAME()+"', SID = '"+e.getSID()+"', SALARY = '"+e.getSALARY()+"', CONTACT = '"+e.getCONTACT()+"' WHERE UID = '"+e.getUID()+"';";
		
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
	
	public Employee searchEmployee(String UID)
	{
		Employee employee = null;
		String query = "SELECT `NAME`, `SID`, `SALARY`,`CONTACT` FROM `employee` WHERE `UID`='"+UID+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String NAME = dbc.result.getString("NAME");
				int SID = dbc.result.getInt("SID");
				double SALARY = dbc.result.getDouble("SALARY");
				int CONTACT = dbc.result.getInt("CONTACT");
				
				employee = new Employee();
				employee.setUID(UID);
				employee.setNAME(NAME);
				employee.setSID(SID);
				employee.setSALARY(SALARY);
				employee.setCONTACT(CONTACT);
			}
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		dbc.closeConnection();
		return employee;
	}
	
	public String[][] getAllEmployee()
	{
		ArrayList<Employee> ar = new ArrayList<Employee>();
		String query = "SELECT * FROM employee;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String UID = dbc.result.getString("UID");
				String NAME = dbc.result.getString("NAME");
				int SID = dbc.result.getInt("SID");
				double SALARY = dbc.result.getDouble("SALARY");
				int CONTACT = dbc.result.getInt("CONTACT");
				
				Employee e = new Employee(UID,NAME,SID,SALARY,CONTACT);
				
				ar.add(e);
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][5];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Employee)obj[i]).getUID();
			data[i][1] = ((Employee)obj[i]).getNAME();
			data[i][2] = ((Employee)obj[i]).getSID()+"";
			data[i][3] = ((Employee)obj[i]).getSALARY()+"";
			data[i][4] = (((Employee)obj[i]).getCONTACT())+"";
		}
		
		return data;
	}
}












































