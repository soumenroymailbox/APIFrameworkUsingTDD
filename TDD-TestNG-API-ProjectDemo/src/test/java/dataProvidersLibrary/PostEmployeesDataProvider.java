package dataProvidersLibrary;

import org.testng.annotations.DataProvider;
import genericLibrary.ApachePOIExcelLibrary;

public class PostEmployeesDataProvider 
{
	@DataProvider(name = "empDataProvider")
	String [][] getEmpData()
	{
		String excelPath = System.getProperty("user.dir") + "/projectResources/testDataLibs/TC_01_Data.xlsx";
		ApachePOIExcelLibrary objExcel = new ApachePOIExcelLibrary(excelPath,0);
		int rowCount = objExcel.getRowCount();
		int colCount = objExcel.getColumnCount();
		
		//System.out.println("rowCount"+rowCount);
		//System.out.println("colCount"+colCount);
				
		String empData [][] = new String [rowCount][colCount];
		for(int row=1;row<rowCount; row++)
		{
			//System.out.println("Row : "+row);
			for (int col = 0; col<colCount; col++)
			{
				empData[row-1][col] = objExcel.getCellDataUsingRowNumberAndColumnNumber(row,col);
				//System.out.println("Column: " +col +"\t"+empData[row-1][col]);
			}
		}
		return(empData);
	}
	
	
	@DataProvider(name = "empDataProvider2")
	String [][] getEmpData2()
	{
		String excelPath = System.getProperty("user.dir") + "/projectResources/testDataLibs/TC_01_Data.xlsx";
		ApachePOIExcelLibrary objExcel = new ApachePOIExcelLibrary(excelPath,1);
		int rowCount = objExcel.getRowCount();
		int colCount = objExcel.getColumnCount();
		
		//System.out.println("rowCount"+rowCount);
		//System.out.println("colCount"+colCount);
				
		String empData [][] = new String [rowCount][colCount];
		for(int row=1;row<rowCount; row++)
		{
			//System.out.println("Row : "+row);
			for (int col = 0; col<colCount; col++)
			{
				empData[row-1][col] = objExcel.getCellDataUsingRowNumberAndColumnNumber(row,col);
				//System.out.println("Column: " +col +"\t"+empData[row-1][col]);
			}
		}
		return(empData);
	}
	
	@DataProvider(name = "empDataProvider3")
	String [][] getEmpData3()
	{		
		String empData [][] = 
			{
				{"SRoy1234","AbCD@12345" }, {"ARoy1234", "WxyZ@12345"}
			};
		return(empData);
	}
	
	@DataProvider(name = "empDataProvider4")
	String [][] getEmpData4()
	{		
		String empData [][] = 
			{
				{"SR8ACrf4E","P1@uUsp939" } // "userID": "a0f699df-fc54-41f8-98e1-669d0ad12e97"
			};
		return(empData);
	}
	
	
}