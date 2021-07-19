package interfaces;

import java.lang.*;
import java.util.*;

import entity.*;

public interface ISalesRepo
{
	public void insertIntoDataBase(Sales p);
	
	public void updateIntoDataBase(Product p);
	
	public Sales searchProduct(String CID);
	
	public String[][] getAllSales();
}