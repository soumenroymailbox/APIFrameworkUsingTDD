package testBaseLibrary;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings("unused")
public class BaseTest 
{
	public static RequestSpecification httpRequest;
	public Response response;
	public String id = "";
	
	
	/**
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		logger = Logger.getLogger("TDD API Test");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	*/
	
}
