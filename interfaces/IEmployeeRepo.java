package interfaces;

import java.lang.*;
import java.util.*;

import entity.*;

public interface IEmployeeRepo
{
	public void insertIntoDataBase(Employee e);
	
	public void deleteFromDataBase(String UID);
	
	public void updateIntoDataBase(Employee e);
	
	public Employee searchEmployee(String UID);
	
	public String[][] getAllEmployee();
}