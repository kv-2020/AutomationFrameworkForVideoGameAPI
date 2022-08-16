package apitestcases;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Deleterequest extends BaseClass{
	@Test
	public static void testdeletereq() {
		Response res=given()
				.when()
				.delete("http://localhost:8080/app/videogames/111")
				.then().extract().response();
		logger.info("print the response");
		System.out.println(res.asPrettyString());
		logger.info("check the response code");
		Assert.assertEquals(res.getStatusCode(),200);
		Headers head=res.getHeaders();
		logger.info("print all headers");
		System.out.println(head);
		logger.info("check the header");
		Assert.assertEquals(head.getValue("Content-Type"),"application/xml");
		logger.info("check te header");
		Assert.assertEquals(head.getValue("Content-Length"),"41");
		logger.info("check on the response");
		Assert.assertEquals(res.asString(),"{\"status\": \"Record Deleted Successfully\"}" );
	}

}
