package apitestcases;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class getrequest extends BaseClass {
	
	

	@Test
	public static void testgetrequest() {
		Response res=given().header("Accept","application/json")
		.when()
		.get("http://localhost:8080/app/videogames")
		.then().extract().response();
		logger.info("enter get request");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusCode());
		System.out.println(res.contentType());
		logger.info("check for status code");
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("check for content type");
	    Assert.assertEquals(res.getContentType(),"application/json");
	}
	
	@Test
	
	public static void testget() {
		Response res=given()
				.header("Accept","application/json")
				.when()
				.get("http://localhost:8080/app/videogames/2")
				.then().extract().response();
		System.out.println(res.asPrettyString());
		Headers head=res.getHeaders();
		logger.info("check for content type");
		Assert.assertEquals(res.contentType(),"application/json");
		logger.info("check for status code");
		Assert.assertEquals(res.getStatusCode(),200);
		System.out.println(head.get("Content-Length"));
		//Assert.assertEquals(res.headers().get("Content-Length"),"Content-Length=118");
		logger.info("check for content length");
		Assert.assertEquals(head.get("Content-Length").getValue(),"118");
		
	}

}
