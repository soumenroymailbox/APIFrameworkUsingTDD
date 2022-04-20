package testLibrary;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Request 
{

	@Test
	void getBooksDetails()
	{
		RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1"; 
	
		//Another way of writting
		/*
		RequestSpecification httpRequest = RestAssured.given(); 
		Response response = httpRequest.queryParam("ISBN","9781449325862").get("/Book");
		System.out.println("Response :Body =>\n" +response.getBody().asPrettyString());
		*/
		
		RequestSpecification	httpRequest = RestAssured.given().queryParam("ISBN","9781449325862"); 
		Response response = httpRequest.request(Method.GET, "/Book");
		System.out.println("Response :Body =>\n" +response.getBody().asPrettyString());
			
			
		
		Headers allHeaders = response.headers();
		for (Header header :allHeaders )
		{
			System.out.println("Header Name is: "+header.getName()+",  "+"Header Value is: "+header.getValue());
		}
		Assert.assertEquals(response.getBody().asString().contains("isbn"),true, "ISBN is not available");
		
		
		JsonPath jsonPath = response.jsonPath();
		System.out.println("Isbn : " +jsonPath.get("isbn"));
		System.out.println("Title : " +jsonPath.get("title"));
		System.out.println("SubTitle : " +jsonPath.get("subTitle"));
		System.out.println("Author : " +jsonPath.get("author"));
		System.out.println("Publish_Date : " +jsonPath.get("publish_date"));
		System.out.println("Publisher : " +jsonPath.get("publisher"));
		System.out.println("Pages : " +jsonPath.get("pages"));
		System.out.println("Description : " +jsonPath.get("description"));
	}
	
}
