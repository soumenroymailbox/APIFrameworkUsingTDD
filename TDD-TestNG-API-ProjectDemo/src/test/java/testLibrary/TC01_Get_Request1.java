package testLibrary;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import testBaseLibrary.BaseTest;

public class TC01_Get_Request1 extends BaseTest
{

	@BeforeClass
	void getBooksDetails()
	{
		System.out.println("**************** Test Started ****************");
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
		httpRequest = RestAssured.given(); 
		response = httpRequest.request(Method.GET, "");
		try { 	Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace();	}
	}
	
	@AfterClass
	void tearDown()
	{
		System.out.println("**************** Test Finished ****************");
	}
	
	@Test(priority = 1)
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		System.out.println("Response : Body =>" +response.getBody().asPrettyString());
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test(priority = 2)
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode();
		System.out.println("Response : StatusCode =>" +statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code 200 Not Recieved");
	}
	
	@Test(priority = 3)
	void checkStatusLine()
	{
		String statusLine = response.getStatusLine();
		System.out.println("Response : Status Line =>" +statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK", "Status Line 'HTTP/1.1 200 OK' Not Recieved");
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
		Assert.assertTrue(parseIntContentLength>100);
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
			System.out.println("Header Name is: "+header.getName()+",  "+"Header Value is: "+header.getValue());
		}
	}

	
}
