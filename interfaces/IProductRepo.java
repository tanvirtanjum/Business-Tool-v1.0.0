package interfaces;

import java.lang.*;
import java.util.*;

import entity.*;

public interface IProductRepo
{
	public void insertIntoDataBase(Product p);
	
	public void deleteFromDataBase(String PID);
	
	public void updateIntoDataBase(Product p);
	
	public Product searchProduct(String PID);
	
	public int searchQuantity(String PID);
	
	public double searchBuyPrice(String PID);
	
	public String[][] getAllProduct();
}