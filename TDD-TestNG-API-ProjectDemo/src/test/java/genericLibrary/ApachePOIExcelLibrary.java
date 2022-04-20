package genericLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ApachePOIExcelLibrary
{
	public File file;
	public static Workbook workbook;
	public FileInputStream fis;
	public static FileOutputStream fos;
	public static Sheet sheet;
	public static Cell cell;
	public Row row;
	public CellStyle cellstyle;
	public Color myColor;
	
	public ArrayList<String>strList = new ArrayList<String>();
	public Map<String, Integer> columns = new HashMap<>();
	
	public ApachePOIExcelLibrary(String excelInputFilePath, Object sheetRef) 
	{
		try {
			file = new File(excelInputFilePath);
			workbook = WorkbookFactory.create(file);
			//System.out.println(sheetRef instanceof String);
			if (sheetRef instanceof String)
			{
				//System.out.println("String: "+sheetRef instanceof String);
				sheet = workbook.getSheet(sheetRef.toString());
			}	
			else if (sheetRef instanceof Integer)
			{
				//System.out.println("Integer:"+(sheetRef instanceof Integer));
				sheet = workbook.getSheetAt(((Integer) sheetRef).intValue());
			}
			sheet.getRow(0).forEach(cell->
			{
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public String cellDataTpe(Cell cell)
	{
		String CellData = null;
		switch(cell.getCellType())
		{
		case STRING:
			CellData = cell.getStringCellValue();
			break;

		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell))
			{
				CellData = String.valueOf(cell.getDateCellValue());
			}
			else
			{
				CellData = String.valueOf((long)cell.getNumericCellValue());
			}
			break;
		case BOOLEAN:
			CellData = Boolean.toString(cell.getBooleanCellValue());
			break;
		case BLANK:
			CellData = "";
			break;
		case ERROR:
			break;
		case FORMULA:
			break;
		case _NONE:
			break;
		default:
			break;
		}
		return CellData;
	}

	public void closeWorkbook()
	{
		try
		{
			workbook.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception Occurs In CloseWorkbook()");
			e.getMessage();
		}
	}

	public int getRowCount()
	{
		int rowCount = sheet.getLastRowNum();
		rowCount = rowCount+1;
		return rowCount;
	}

	public int getColumnCount()
	{
		int columnCount = sheet.getRow(0).getLastCellNum();
		return columnCount;
	}

	public int getCellCount()
	{
		int cellCount = getColumnCount()*getRowCount();
		return cellCount;
	}

	public String getCellData(int row,int column)
	{
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}

	public String getCellStringValue(int row,int column)
	{
		cell = sheet.getRow(row).getCell(column);
		DataFormatter formatter = new DataFormatter();
		String strValue = formatter.formatCellValue(cell);
		return strValue;
	}

	public String getOneRandomCellStringValue(int columnIndex)
	{
		int rowCount = getRowCount();
		
		for(int i=0;i<rowCount;i++)
		{
			String cellDataFromExcel = getCellStringValue(i,columnIndex);
			strList.add(cellDataFromExcel); 
		}

		int randomArrayIndex =  ThreadLocalRandom.current().nextInt(strList.size()); 
		String randomCellValue = strList.get(randomArrayIndex);
		return randomCellValue; 
	}

	public String getCellDataUsingRowNumberAndColumnNumber(int rowNum, int columnNum)
	{
		try 
		{
			cell = sheet.getRow(rowNum).getCell(columnNum);
			
			String CellData = null;
			switch(cell.getCellType())
			{
			case STRING:
				CellData = cell.getStringCellValue();
				break;

			case NUMERIC:
				if(DateUtil.isCellDateFormatted(cell))
				{
					CellData = String.valueOf(cell.getDateCellValue());
				}
				else
				{
					CellData = String.valueOf((long)cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			case BLANK:
				CellData = "";
				break;
			case ERROR:
				break;
			case FORMULA:
				break;
			case _NONE:
				break;
			default:
				break;
			}
			return CellData;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "";
		}
	}

	public String getCellDataUsingColumnNameAndRowNumber(String columnName, int rowNum) throws Exception
	{
		return getCellDataUsingRowNumberAndColumnNumber(rowNum, columns.get(columnName));
	}

	public List<String> getSpecificRowsData(int rowNumber)
	{
		List<String> dataLists = new ArrayList<>();
		int cellCount = sheet.getRow(rowNumber).getLastCellNum();
		
		for (int i=1;i<cellCount;i++)
		{
			cell = sheet.getRow(rowNumber).getCell(i);
			String cellData = cellDataTpe(cell);
			dataLists.add(cellData);	
		}
		return dataLists;	
	}
	
	public String getSpecificCellData(int rowNumber, int columnNumber)
	{
		cell = sheet.getRow(rowNumber).getCell(columnNumber);
		String cellData = cellDataTpe(cell);
		return cellData;	
	}
	



}
