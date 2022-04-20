package genericLibrary;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomTestDataGenerator 
{
	
	public static String alphaNumericValue(int letterCount)
	{
		String testData = RandomStringUtils.randomAlphanumeric(letterCount);
		return testData;
	}
	
	public static String numericValue(int letterCount)
	{
		String testData = RandomStringUtils.randomNumeric(letterCount);
		return testData;
	}
	
	public static String alphabeticValue(int letterCount)
	{
		String testData = RandomStringUtils.randomAlphabetic(letterCount);
		return testData;
	}
	
	public static String asciiValue(int letterCount)
	{
		String testData = RandomStringUtils.randomAscii(letterCount);
		return testData;
	}
	
}
