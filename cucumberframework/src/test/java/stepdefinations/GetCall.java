package stepdefinations;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utility.CommonFunction;

import static io.restassured.RestAssured.*;

import org.testng.Reporter;

public class GetCall {
	
	String placeHolder;
	
	@Given("^the user navigates to \"([^\"]*)\" via get calls$")
	public void the_user_navigates_to_via_get_call(String url) throws Throwable {
		
		Reporter.log("Inside given");
		RestAssured.baseURI = url;
		Reporter.log("The url is:" + RestAssured.baseURI);
		
		String response;
		
	 response=
		given()
				.log().all()
				//.param("id",1)
				.headers("Content-Type","application/json")
		.when()
				.get("?id=1")
		.then()
				//.log().all()
				.assertThat().statusCode(200)
				//.body("id",equalTo("1")); hamcrest dependency is needed
				.header("Content-Type", "application/json; charset=utf-8")
				.extract().response().asString();
	 
	 		Reporter.log("The response is" + response);
	    
	    JsonPath js=CommonFunction.rawToJson(response);
	   
	    placeHolder = js.getString("title");
	    Reporter.log("The query value fetched using json path is: "+ placeHolder);
		
	    
	    
		
	}

}
