package core;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Loadingdatafromconfig 
{
	/*public static void main(String[] args) 
	{
		Loadingdatafromconfig data = new Loadingdatafromconfig();
		String str = data.ReadPropertyFile("url");
		System.out.println("API String : " + str);
		
		
	}
*/
	
	// this Function will load the content of Config.Properties
	//@Test
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

}
