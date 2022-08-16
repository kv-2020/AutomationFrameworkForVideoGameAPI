package apitestcases;
import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Postrequest extends BaseClass{
	@Test(priority=1)
	public static void testpost() {
		JSONObject obj=new JSONObject();
		obj.put("id","111");
		obj.put("name", "spiderman");
		obj.put("releaseDate", "2022-07-22T20:19:33.948Z");
		obj.put("reviewScore", "90");
		obj.put("category", "Driving");
		obj.put("rating", "Mature");
		logger.info("enter the data with json object");
		Response res=given()
				.header("Content-Type","application/json")
				.body(obj.toString())
				.when()
				.post("http://localhost:8080/app/videogames")
				.then().extract().response();
		logger.info("print status code");
		System.out.println(res.getStatusCode());
		logger.info("print content length");
		System.out.println(res.getHeader("content-length"));
		logger.info("check for content length");
		Assert.assertEquals(res.getHeader("content-length"),"39");
		System.out.println(res.asPrettyString());
		String s1=res.asString();
		System.out.println(s1);
		logger.info("check for response body");
		Assert.assertEquals(res.asString(),"{\"status\": \"Record Added Successfully\"}");
	}
@Test(priority=2)
	
	public static void testget() {
	    Response res=given()
				.header("Accept","application/json")
				.when()
				.get("http://localhost:8080/app/videogames/111")
				.then().extract().response();
		logger.info("print status code");
		System.out.println(res.getStatusCode());
		logger.info("check for status code");
		Assert.assertEquals(res.getStatusCode(),200);
		Headers head=res.getHeaders();
		System.out.println(head);
		logger.info("check for content type");
		Assert.assertEquals(res.contentType(),"application/json");
		logger.info("check for status code");
		Assert.assertEquals(res.getStatusCode(),200);
		System.out.println(head.get("Content-Length"));
		//Assert.assertEquals(res.headers().get("Content-Length"),"Content-Length=118");
		Assert.assertEquals(head.getValue("Content-Length"),"112");
		JsonPath js=res.jsonPath();
		System.out.println(js.get("id"));
		Assert.assertEquals(js.get("id").toString(),"111");
		
	}

}  
