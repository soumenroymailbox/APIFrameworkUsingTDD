package genericLibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIExcelWriterLibrary 
{
	
	public static String outputFileName; //= ApachePOIExcelLibrary.testDataPath + "ExcelWriter.xlsx";
	public static FileOutputStream fos;
	static Map<String, Object[]> dataList = new TreeMap<String, Object[]>();
	
	
	public static void writeSingleRowInExcel(String sheetName, String dataHeading, String data) throws IOException
	{
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(sheetName);
		XSSFRow row;

		// This data needs to be written (Object[])
	
		dataList.put("1", new Object[] {dataHeading});
		dataList.put("2", new Object[] {data});

		Set<String> keyid = dataList.keySet();
		int rowid = 0;

		// writing the data into the sheets...
		for (String key : keyid)
		{
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = dataList.get(key);
			int cellid = 0;
			for (Object obj : objectArr) 
			{
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);
			}
		}

		fos = new FileOutputStream(new File(outputFileName));
		workbook.write(fos);
		fos.close();
	}

}
