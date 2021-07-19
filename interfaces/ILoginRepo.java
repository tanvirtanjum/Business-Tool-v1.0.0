package interfaces;

import java.lang.*;
import java.util.*;

import entity.*;

public interface ILoginRepo
{
	Login getLogin(String UID, String PASS);
	
	void insertLogin(Login l);
	
	void updateLogin(Login l);
	
	void deleteLogin(String UID);
	
	public Login srchLogin(String UID);
	
	public Login srchLogin(String PASS, String UID);
	
	public void updateLoginPass(Login l);
	
	public String checkPass(String UID);
}