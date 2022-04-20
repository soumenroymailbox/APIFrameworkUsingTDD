package testLibrary;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_Request 
{

	@Test
	void getBooksDetails()
	{
		// Specify the base URL to the RESTful web service 
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
	
		// Get the RequestSpecification of the request to be sent to the server. 
		RequestSpecification httpRequest = RestAssured.given(); 
		
		// specify the method type (GET) and the parameters if any. 
		//In this case the request does not take any parameters 
		Response response = httpRequest.request(Method.GET, "");
		
		// Print the status and message body of the response received from the server 
		System.out.println("Response : Status Code => " + response.getStatusCode()); 
		System.out.println("Response : Status Line => " + response.getStatusLine()); 
		System.out.println("Response :Body =>" +response.getBody().asPrettyString()); //Same as response.prettyPrint()
		
		System.out.println("Header : Content Type =>" + response.header("content-type"));
		System.out.println("Header : Content Length =>" + response.header("content-length"));
		System.out.println("Header : Date =>" + response.header("date"));
		System.out.println("Header : ETag =>" + response.header("etag"));
		System.out.println("Header : Server =>" + response.header("server"));
		System.out.println("Header : X-Powered-By =>" + response.header("x-powered-by"));
		
		
		Assert.assertEquals(response.getStatusCode(), 200, "Status Code 200 Not Recieved");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Status Line 'HTTP/1.1 200 OK' Not Recieved");
		
		Assert.assertEquals(response.header("content-type"), "application/json; charset=utf-8", "Header : Content Type Mismatch");
		Assert.assertEquals(response.header("server"), "nginx/1.17.10 (Ubuntu)", "Header : Server Mismatch");
		
		Headers allHeaders = response.headers();
		
		for (Header header :allHeaders )
		{
			System.out.println("Header Name is: "+header.getName()+",  "+"Header Value is: "+header.getValue());
		}
	}
	
}
