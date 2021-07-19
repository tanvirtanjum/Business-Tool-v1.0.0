package entity;

import java.lang.*;
import java.util.*;

public class Product
{
	private String PID;
	private String NAME;
	private String TYPE;
	private int QUANTITY;
	private double BUYPRICE;
	private double SELLPRICE;
	
	public Product(){}
	
	public Product(String PID, String NAME, String TYPE, int QUANTITY, double BUYPRICE, double SELLPRICE)
	{
		this.PID = PID;
		this.NAME = NAME;
		this.TYPE = TYPE;
		this.QUANTITY = QUANTITY;
		this.BUYPRICE = BUYPRICE;
		this.SELLPRICE = SELLPRICE;
	}
	
	public void setPID(String PID)
	{
		this.PID = PID;
	}
	
	public void setNAME(String NAME)
	{
		this.NAME = NAME;
	}
	
	public void setTYPE(String TYPE)
	{
		this.TYPE = TYPE;
	}
	
	public void setQUANTITY(int QUANTITY)
	{
		this.QUANTITY = QUANTITY;
	}
	
	public void setBUYPRICE(double BUYPRICE)
	{
		this.BUYPRICE = BUYPRICE;
	}
	
	public void setSELLPRICE(double SELLPRICE)
	{
		this.SELLPRICE = SELLPRICE;
	}
	
	public String getPID()
	{
		return PID;
	}
	
	public String getNAME()
	{
		return NAME;
	}
	
	public String getTYPE()
	{
		return TYPE;
	}
	
	public int getQUANTITY()
	{
		return QUANTITY;
	}
	
	public double getBUYPRICE()
	{
		return BUYPRICE;
	}
	
	public double getSELLPRICE()
	{
		return SELLPRICE;
	}
}