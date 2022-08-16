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

public class Putrequest extends BaseClass {
	@Test
	public static void testputrequest() {
		JSONObject obj=new JSONObject();
		obj.put("id","111");
		obj.put("name", "pacman");
		obj.put("releaseDate", "2022-07-22T20:19:33.948Z");
		obj.put("reviewScore", "99");
		obj.put("category", "Driving");
		obj.put("rating", "Mature");
		logger.info("request for updating the game name");
		Response res=given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body(obj.toString())
				.when()
				.put("http://localhost:8080/app/videogames/111")
				.then().extract().response();
		logger.info("print out the response");
		System.out.println(res.asPrettyString());
		logger.info("print the status code");
		System.out.println(res.getStatusCode());
		logger.info("check the status code");
		Assert.assertEquals(res.getStatusCode(),200);
		Headers head=res.getHeaders();
		logger.info("print all the headers");
		System.out.println(head);
		logger.info("check the headers");
		Assert.assertEquals(head.getValue("Content-Length"),"109");
		logger.info("parse the response to Json");
		JsonPath js=res.jsonPath();
		logger.info("print the id");
		System.out.println(js.get("id"));
		logger.info("check the id");
		Assert.assertEquals(js.get("id"),111);
		logger.info("check the name");
		Assert.assertEquals(js.get("name"),"pacman");
		
		
	}

}
