package testLibrary;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import dataProvidersLibrary.PostEmployeesDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_EMP_Post_Request 
{
	@SuppressWarnings("unchecked")
	@Test(dataProvider = "empDataProvider", dataProviderClass = PostEmployeesDataProvider.class)
	void postNewEmployee(String eName, String eSal, String eAge)
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1"; 
		RequestSpecification httpRequest = RestAssured.given(); 
		JSONObject requestParams = new JSONObject(); 
		
		requestParams.put("name", eName); 
		requestParams.put("salary", eSal); 
		requestParams.put("age", eAge); 
		
		//For JSON Format data we need to pass the header part as the data might be present in hashmap
		httpRequest.header("Content-Type", "application/json");  
		httpRequest.body(requestParams.toJSONString()); 
				
		
		Response response = httpRequest.request(Method.POST, "/create");
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response Body"+responseBody);
			
		//System.out.println("Response :Body =>\n" +response.getBody().asPrettyString());
	//	System.out.println("Response :Body =>\n" +response.getBody().asString());	
		//System.out.println("Response : Status Code =>\n" + response.getStatusCode()); 
		//System.out.println("Response : Status Line =>\n" + response.getStatusLine());
	}
}