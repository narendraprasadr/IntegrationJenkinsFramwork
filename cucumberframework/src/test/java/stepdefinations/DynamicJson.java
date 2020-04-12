package stepdefinations;

import cucumber.api.java.en.Given;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.CommonFunction;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {
	
	
	@Given(value = "the user navigates to \"([^\"]*)\" and post an request")
	@Test(dataProvider ="booksData")
	public void addBook(String url,String id, String isbn) throws Throwable {
		System.out.println("Insdide add book");
		RestAssured.baseURI = url;
		
		String data = given()
							.headers("Content-Type","application/json")
							.body(Payload.addBook(id,isbn))
					.when()
							.post("addbook.php")
					.then()
							.extract().response().asString();	
		JsonPath js = CommonFunction.rawToJson(data);
		String ids = js.get("id");
		System.out.println("the id is: "+ids);
	}
	
	
	@DataProvider(name="booksData")
	public Object[][] getData() {
		
		return new Object[][] {{"134","prasad"},{"144","prasad1"}};
	}

}
