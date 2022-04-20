package testLibrary;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import dataProvidersLibrary.PostEmployeesDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_EMP_Post_Request 
{

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "empDataProvider4", dataProviderClass = PostEmployeesDataProvider.class)
	void getBooksDetails(String uName, String pWord)
	{
		RestAssured.baseURI = "https://demoqa.com/Account/v1"; 
		RequestSpecification httpRequest = RestAssured.given(); 
		JSONObject requestParams = new JSONObject(); 
		
		requestParams.put("userName", uName); 
		requestParams.put("password", pWord); 
		
		// Add a header stating the Request body is a JSON 
		httpRequest.header("Content-Type", "application/json"); // Add the Json to the body of the request 
		httpRequest.body(requestParams.toJSONString()); // Post the request and check the response. Attached Data to the request
				
		Response response = httpRequest.request(Method.POST, "/user");
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response Body"+responseBody);
	
		
		
		/*
		 * Response Body{
    		"userID": "263c36a4-0d95-47a3-8580-c0d8c2fede27",
    		"username": "SRoy1234",
    		"books": [
       
    			]
			}
			Response Body{
		    "userID": "a61c2b5b-38ca-4367-aff0-983c21f7b6cf",
		    "username": "ARoy1234",
		    "books": [
		        
		    ]
		 * 
		 */
		
		
		// Status Code 406 recieved if same data pass again. 406 = The HyperText Transfer Protocol (HTTP) 406 Not Acceptable client error response code indicates that the server cannot produce a response matching the list of acceptable values defined in the request's proactive content negotiation headers, and that the server is unwilling to supply a default representation.
	}
	
	
	
}
