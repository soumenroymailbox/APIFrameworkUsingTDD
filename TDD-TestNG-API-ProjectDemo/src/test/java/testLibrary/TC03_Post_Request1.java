package testLibrary;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import genericLibrary.RandomTestDataGenerator;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import testBaseLibrary.BaseTest;

public class TC03_Post_Request1 extends BaseTest
{
	@SuppressWarnings("unchecked")
	@BeforeClass
	void postUserAccount()
	{
		System.out.println("**************** Test Started ****************");
		
		String username = "SR"+RandomTestDataGenerator.alphaNumericValue(7);
		String password = "P1@"+RandomTestDataGenerator.alphaNumericValue(7);
		System.out.println("Username: "+username);
		System.out.println("Password: "+password);
		try { 	Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();	}
		
		RestAssured.baseURI = "https://demoqa.com/Account/v1";  // Specify the base URL to the RESTful web service 
		httpRequest = RestAssured.given(); // Get the RequestSpecification of the request to be sent to the server. 
		
		// JSONObject is a class that represents a Simple JSON. 
		// We can add Key - Value pairs using the put method also its called payload
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("userName", username); 
		requestParams.put("password", password); 
		
		// Add a header stating the Request body is a JSON 
		httpRequest.header("Content-Type", "application/json"); // Add the JSON to the body of the request 
		httpRequest.body(requestParams.toJSONString()); // Post the request and check the response. Attached Data to the request
	
		super.response = httpRequest.request(Method.POST, "/user");
		try { 	Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace();	}
		System.out.println("Response Body as  =>" +response.getBody().asString());
		
	}
	
	@Test
	void tearDown()
	{
		System.out.println("**************** Test Finished ****************");
	}
	
	
	
		
	@SuppressWarnings("unused")
	@Test(priority = 1)
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		System.out.println("Response : Body =>" +response.getBody().asPrettyString());
	}
	

	
	@Test(priority = 2)
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode();
		System.out.println("Response : StatusCode =>" +statusCode);
		
		if (statusCode == 201)
		{
			Assert.assertEquals(response.getStatusCode(), 201, "Status Code 201 Not Recieved");
			System.out.println("201 is Created - The request has succeeded and has led to the creation of a resource.");
		}
		else if (statusCode == 406)
		{
			Assert.assertEquals(response.getStatusCode(), 406, "Status Code 406 Not Recieved");
			System.out.println("406 is Not Acceptable Client at Side - The server cannot produce a response matching the list of acceptable values defined in the request's proactive content negotiation header which is occured due to same data pass again");
		}
		else
		{
			System.out.println("Unknown Status Code Found");
		}
	}
	
	@Test(priority = 3)
	void checkStatusLine()
	{
		String statusLine = response.getStatusLine();
		System.out.println("Response : Status Line =>" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created", "Status Line 'HTTP/1.1 200 OK' Not Recieved");
	}
	
	@Test(priority = 4)
	void checkResponseTime()
	{
		long responseTime = response.getTime();
		System.out.println("Response : ResponseTime =>" +responseTime);
		if(responseTime>2000)
		{
			System.out.println("Response Time is Greater Than 2000");
		}
		else
		{
			System.out.println("Response Time is less Than 2000");
		}
	}
	
	@Test(priority = 5)
	void checkContentType()
	{
		String contentType = response.header("content-type");
		System.out.println("Header : Content Type =>" +contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8", "Header : Content Type Mismatch");
	}
	
	@Test(priority = 6)
	void checkContentLength()
	{
		String contentLength = response.header("content-length");
		int parseIntContentLength = Integer.parseInt(contentLength);
		System.out.println("Header : Content Length =>" +parseIntContentLength);
		if(parseIntContentLength>2000)
		{
			System.out.println("Content Length is Greater Than 2000");
		}
		else
		{
			System.out.println("Content Length is less Than 2000");
		}
		Assert.assertTrue(parseIntContentLength>0);
	}
	
	@Test(priority = 7)
	void checkServerType()
	{
		String serverType = response.header("server");
		System.out.println("Header : Server Type =>" +serverType);
		Assert.assertEquals(serverType, "nginx/1.17.10 (Ubuntu)", "Header : Server Mismatch");
	}
	
	@Test(priority = 8)
	void printAllHeaders()
	{
		Headers allHeaders = response.headers();
		for (Header header :allHeaders )
		{
			System.out.println("Header Name is: "+header.getName()+",  "+"and Value is: "+header.getValue());
		}
	}
	
	@Test(priority = 9)
	void validateUser()
	{
		String userId = response.jsonPath().get("userID");
		System.out.println("Created UserID =>" +userId);
	}
	

	/** Created User Details
	 	Username: SRnJ0La8d
		Password: P1@MTq8sRD
		UserID/apiKey: f66228dc-81d6-4fed-b8d5-fe6ef6dfe59d
	 */
	/**
	
		  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlNSbkowTGE4ZCIsInBhc3N3b3JkIjoiUDFATVRxOHNSRCIsImlhdCI6MTY0OTkwODY4NH0.sRkos6YyKdlDZTps2HWBvqIO2j9zzdFYDVT41JXASVM",
		  "expires": "2022-04-21T03:58:04.589Z",
		  "status": "Success",
		  "result": "User authorized successfully."
	*/
	
	
}
