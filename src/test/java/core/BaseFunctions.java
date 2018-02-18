package core;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import Utility.Utility_File_Functions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BaseFunctions extends Utility_File_Functions
{
	//This Function will convert the APi into jsonString
	public String ConvertAPItoJSONstring()
	{
		String jsonstrng = null ;	
		try
		{						
			String APIurl = ReadPropertyFile("APIurl");
			HttpClient client = new HttpClient(); 
			GetMethod get = new GetMethod(APIurl);
			client.executeMethod(get);
	        jsonstrng = get.getResponseBodyAsString();	        			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}		
		return jsonstrng ;		 
	}
	
	//This function will parse the json String and give the value of Parsed Element
	public String ParseJSONString(String str, String parsedElement) {
		String val = null;
		try {
			JsonParser parser = new JsonParser();
			JsonObject jsonobj = (JsonObject) parser.parse(str);
			val = jsonobj.get(parsedElement).getAsString();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	/*public Object ParseJSONString(String str, String parsedElement) {
		Object val = null;
		//parsedElement = "CanRelist";
		try {
			JsonParser parser = new JsonParser();
			JsonObject jsonobj = (JsonObject) parser.parse(str);
			val=jsonobj.get(parsedElement);			
		}

		catch (Exception e) {

		}
		return val;
	}
*/



	
	
	public Boolean ParseJSONString(String str, String parsedElement, String ele)
	{
		Boolean boolval = null; 
		try
		{
		JsonParser parser = new JsonParser();
		JsonObject jsonobj = (JsonObject) parser.parse(str);
		boolval = jsonobj.get(parsedElement).getAsBoolean();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return boolval;
	}



}
