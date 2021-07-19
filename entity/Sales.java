package entity;

import java.lang.*;
import java.util.*;

public class Sales
{
	private String CID;
	private String NAME;
	private int CONTACT;
	private String PID;
	private int QUANTITY;
	private double SELLPRICE;
	
	public Sales(){}
	
	public Sales(String CID, String NAME, int CONTACT, String PID, int QUANTITY, double SELLPRICE)
	{
		this.CID = CID;
		this.NAME = NAME;
		this.CONTACT = CONTACT;
		this.PID = PID;
		this.QUANTITY = QUANTITY;
		this.SELLPRICE = SELLPRICE;
	}
	
	public void setCID(String CID)
	{
		this.CID = CID;
	}
	
	public void setNAME(String NAME)
	{
		this.NAME = NAME;
	}
	
	public void setCONTACT(int CONTACT)
	{
		this.CONTACT = CONTACT;	
	}
	
	public void setPID(String PID)
	{
		this.PID = PID;
	}
	
	public void setQUANTITY(int QUANTITY)
	{
		this.QUANTITY = QUANTITY;
	}
	
	public void setSELLPRICE(double SELLPRICE)
	{
		this.SELLPRICE = SELLPRICE;
	}
	
	public String getCID()
	{
		return CID;
	}
	
	public String getNAME()
	{
		return NAME;
	}
	
	public int getCONTACT()
	{
		return CONTACT;
	}
	
	public String getPID()
	{
		return PID;
	}
	
	public int getQUANTITY()
	{
		return QUANTITY;
	}
	
	public double getSELLPRICE()
	{
		return SELLPRICE;
	}
}