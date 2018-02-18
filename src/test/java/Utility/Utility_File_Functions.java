package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Time;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.formula.functions.TimeFunc;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Utility_File_Functions
{
	

	public String ReadPropertyFile(String parameter)
	{		
		String valuestr = null;
		try
		{
		
		Properties prop = new Properties();	
		FileInputStream input = new FileInputStream(new File("./src/test/java/config/config.properties"));		
		prop.load(input);		
		//System.out.println("str" + prop.getProperty(parameter));
		valuestr = prop.getProperty(parameter);
		//System.out.println(valuestr);		
		}
		catch(Exception e )
		{
			e.printStackTrace();			
		}
		return valuestr ;
		
	}
	//This Function will Read the Expected value of a condition from ExpectedValue property file
	
	public String ReadExpectedValue(String parameter)
	{		
		String valuestr = null;
		try
		{
		
		Properties prop = new Properties();	
		FileInputStream input = new FileInputStream(new File("./src/test/java/config/Expectedvalue.properties"));		
		prop.load(input);	
		valuestr = prop.getProperty(parameter);			
		}
		catch(Exception e )
		{
			e.printStackTrace();			
		}
		return valuestr ;	
	}
	
    // This Function will Read the Input Value from the Excel
	public String InputExcel(String inputparameter)
	{	
		String Expval = null ;	
		try
		{		
		File file  	= new File("D:\\AcceptanceCriteria.xlsx");
		FileInputStream fis = new FileInputStream(file);		
		XSSFWorkbook workbok = new XSSFWorkbook(fis); 
		XSSFSheet sheet = workbok.getSheet("sheet1");		
		int rowcount = sheet.getLastRowNum()+1;		
		for(int i =2; i<rowcount; i++)		
		{			
			String  condition = sheet.getRow(i).getCell(2).toString();						
			if (condition.equalsIgnoreCase(inputparameter))
			{								
				Expval  = sheet.getRow(i).getCell(3).toString();								
			}
			else
			{
				break; 
			}			
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Expval; 
	}		
	
	
	//This Function will compare the Actual and Expected Result 
	public  boolean ConditionAssert(String ActalResult, String ExpectedResult)
	{
		boolean Flag = false ;
		try
		{
			if (ActalResult.equalsIgnoreCase(ExpectedResult)) 
			{
				Flag = true; 		
			}
			else
			{
				Flag = false; 
			}			
		}
		catch(Exception e)		
		{
			e.printStackTrace();			
		}	
		return Flag;
	}
	
	
	//This Function will Print the result in External File(txt file) in Resources Folder with Date and time
	public void ResultPrint(String TestCasename , String reslt)
	{
		try
		{
			PrintStream result  = new PrintStream(new File("./src/test/resources/Results/results.txt"));
			Date date = new Date();			
			System.out.println(result);
			result.print("Test Case name : " + TestCasename  + "          Result :  "  + reslt + "               Date : " + date.toString() );
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}

