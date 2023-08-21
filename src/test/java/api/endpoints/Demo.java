package api.endpoints;

import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.utilities.ExcelUtility;

public class Demo {
	
	
	static ResourceBundle getURL()
	{
		ResourceBundle resource = ResourceBundle.getBundle("bundle");
		return resource;
	}
	@Test
	public void demo1()
	{
		String s=getURL().getString("name");
		System.out.println(s);
	}
	

	@Test
	public void demo()
	{
		String path=".//UserData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getRowCount("Sheet1");
		int colnum=xl.getCellCount("Sheet1",1);
		
		String apidata[][]=new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colnum;j++)
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		for(String[] s : apidata)
		{
			for(int i=0;i<colnum;i++)
			{
			System.out.print(s[i]+ "   ");
			}
			System.out.println();
		}
		
	}
	
}
