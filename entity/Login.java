package entity;

import java.lang.*;
import java.util.*;

public class Login
{
	String UID;
	String PASS;
	int SID;
	
	public Login(){}
	
	public Login(String UID, String PASS, int SID)
	{
		this.UID = UID;
		this.PASS = PASS;
		this.SID = SID;
	}
	
	public void setUID(String UID)
	{
		this.UID = UID;
	}
	
	public void setPASS(String PASS)
	{
		this.PASS = PASS;
	}
	
	public void setSID(int SID)
	{
		this.SID = SID;
	}
	
	public String getUID()
	{
		return UID;
	}
	
	public String getPASS()
	{
		return PASS;
	}
	
	public int getSID()
	{
		return SID;
	}
}