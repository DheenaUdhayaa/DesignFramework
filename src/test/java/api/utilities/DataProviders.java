package api.utilities;


import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data",parallel = true)
	public String[][] getAllData()
	{
		String path="./testData/UserData.xlsx";
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
		
		return apidata;
	}
	

	@DataProvider(name="UserNames",parallel = true)
	public String[] getUserNames() 
	{
		String path="./testData/Userdata.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getRowCount("Sheet1");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}

		return apidata;

	}
}
