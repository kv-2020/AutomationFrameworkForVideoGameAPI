package apitestcases;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import utils.ReadExcel;

	public class PostDDT extends BaseClass{
		@Test(dataProvider="postddt")
		public  void testpostddt(String namevalue,String jobvalue) {
			JSONObject obj=new JSONObject();
			logger.info("getting name from excel");
			obj.put("name",namevalue);
			logger.info("getting job from excel");
			obj.put("job",jobvalue);
			logger.info("enter post request");
			Response res=given() 
					      .header("Content-Type","application/json")
					      .body(obj.toString())
			              .when()
			              .post("https://reqres.in/api/users")
			              .then().extract().response();
			logger.info("print the response");
			System.out.println(res.asPrettyString());
			logger.info("print the status code");
			System.out.println(res.getStatusCode());
			logger.info("checking the status code");
			Assert.assertEquals(res.getStatusCode(),201);
			
		}
		@DataProvider(name="postddt")
		
			String [][]getdata() throws IOException{
				String path="C:\\Users\\kittu\\Selenium\\RestAssured\\src\\test\\resources\\TestData\\PostDDT.xlsx";
				int rownum=ReadExcel.getRowCount(path, "Sheet1");
				int colcount=ReadExcel.getCellCount(path, "Sheet1", 1);
				String postddt[][]=new String[rownum][colcount]; //6,2
				//0 is for header..column is 0
				for(int i=1;i<=rownum;i++) {
					for(int j=0;j<colcount;j++ ) {
						postddt[i-1][j]=ReadExcel.getCellData(path, "Sheet1", i, j);//1  0
					}
				}
				return postddt;
		}

	}



