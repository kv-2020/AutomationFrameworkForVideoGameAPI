package apitestcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
	System.out.println("In Setup()");
	 logger=Logger.getLogger("VideoGame");
	PropertyConfigurator.configure("C:\\Users\\kittu\\VideoGameAPI\\VideoGame\\src\\test\\resources\\log4j.properties");
	}
}
