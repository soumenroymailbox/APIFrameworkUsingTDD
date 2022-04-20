package dataProvidersLibrary;

import org.testng.annotations.Test;

import genericLibrary.ApachePOIExcelLibrary;

public class TestClass 
{
	@Test
	public void sampleData()
	{
		String excelPath = System.getProperty("user.dir") + "/projectResources/testDataLibs/TC_01_Data.xlsx";
		ApachePOIExcelLibrary objExcel = new ApachePOIExcelLibrary(excelPath,1);
		int rowCount = objExcel.getRowCount();
		int colCount = objExcel.getColumnCount();
		String empData [][] = new String [rowCount][colCount];
		
		for(int row=1;row<rowCount; row++)
		{
			System.out.println("Row : "+row);
			for (int col = 0; col<colCount; col++)
			{
				empData[row-1][col] = objExcel.getCellDataUsingRowNumberAndColumnNumber(row,col);
				System.out.println("Column: " +col +"\t"+empData[row-1][col]);
			}
		}
	}
}
