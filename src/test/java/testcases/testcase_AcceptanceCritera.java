package testcases;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.BaseFunctions;

public class testcase_AcceptanceCritera extends BaseFunctions {
	@Test
	public void AcceptanceCriteria() 
	{		
		try {
			testcase_AcceptanceCritera tc = new testcase_AcceptanceCritera();
			String jsonstring = tc.ConvertAPItoJSONstring();
			JsonParser parser = new JsonParser();
			JsonObject jsonobj = (JsonObject) parser.parse(jsonstring);
			String FirstCondition = ReadPropertyFile("Condition1");
			String SecondCondition = ReadPropertyFile("Condition2");
			String ThirdCondition = ReadPropertyFile("Condition3");
			String subcondition1 = ReadPropertyFile("SubCondition1");
			String subcondition2 = ReadPropertyFile("SubCondition2");
			String Cond1AV = ParseJSONString(jsonstring, FirstCondition);
			Boolean Cond2AV = Boolean.parseBoolean(ParseJSONString(jsonstring,SecondCondition));			
			String Cond1EV = tc.ReadExpectedValue("Condition1");			
			Boolean Cond2EV =  Boolean.parseBoolean(ReadExpectedValue("Condition2"));
			String SubCond1EV = tc.ReadExpectedValue("SubCondition1");
			String SubCond2EV = tc.ReadExpectedValue("SubCondition2");
			
			
			Assert.assertEquals(Cond1EV,Cond1AV);
			Assert.assertEquals("Actual value" + Cond1AV + "Expected value" + Cond1EV , Cond1EV, Cond1AV);
			Reporter.log("Condition1 - Exepected "+Cond1EV+" Actual "+Cond1AV);
			Assert.assertEquals("Actual value" + Cond2AV + "Expected value" + Cond2EV ,Cond2EV, Cond2AV);
			Reporter.log("Condition2 - Exepected "+Cond1EV+" Actual "+Cond1AV);
			
			JsonArray parentarray = jsonobj.getAsJsonArray(ThirdCondition);
			for (int i = 0; i < parentarray.size(); i++) {
				JsonObject jsonobject1 = parentarray.get(i).getAsJsonObject();
				String SubCond1AV = jsonobject1.get(subcondition1).getAsString();

				if (SubCond1AV.equalsIgnoreCase(SubCond1EV))
				{
					Reporter.log("Condition3 - Exepected "+SubCond1AV+" Actual "+SubCond1EV);
					String SubCond2AV = jsonobject1.get(subcondition2).getAsString();
					Assert.assertTrue("Actual string" + SubCond1AV + "Expected string" + SubCond2EV , SubCond2AV.contains(SubCond2EV));					
					Reporter.log("Condition4 - Exepected "+ SubCond1EV+" Actual "+ SubCond2AV);
					tc.ResultPrint("AcceptanceCriteria", "Passed");
				}
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
