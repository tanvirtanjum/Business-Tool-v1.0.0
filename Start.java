import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.sql.*;

import frames.*;

public class Start
{
	public static void main(String args[]) throws Exception
	{
		try
		{
			LoginFrame LF = new LoginFrame();
			LF.setVisible(true);	
		}
		
		catch(Exception ex){}	
	}
}