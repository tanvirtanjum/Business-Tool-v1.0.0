package entity;

import java.lang.*;
import java.util.*;

public class Employee
{
	private String UID;
	private String NAME;
	private int SID;
	private double SALARY;
	private int CONTACT;
	
	public Employee(){}
	
	public Employee(String UID, String NAME, int SID, double SALARY, int CONTACT)
	{
		this.UID = UID;
		this.NAME = NAME;
		this.SID = SID;
		this.SALARY = SALARY;
		this.CONTACT = CONTACT;
	}
	
	public void setUID(String UID)
	{
		this.UID = UID;
	}
	
	public void setNAME(String NAME)
	{
		this.NAME = NAME;
	}
	
	public void setSID(int SID)
	{
		this.SID = SID;
	}
	
	public void setSALARY(double SALARY)
	{
		this.SALARY = SALARY;
	}
	
	public void setCONTACT(int CONTACT)
	{
		this.CONTACT = CONTACT;
	}
	
	public String getUID()
	{
		return UID;
	}
	
	public String getNAME()
	{
		return NAME;
	}
	
	public int getSID()
	{
		return SID;
	}
	
	public double getSALARY()
	{
		return SALARY;
	}
	
	public int getCONTACT()
	{
		return CONTACT;
	}
}