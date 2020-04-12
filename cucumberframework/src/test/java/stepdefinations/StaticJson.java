package stepdefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Reporter;

import cucumber.api.java.en.Given;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utility.CommonFunction;

public class StaticJson {
		
		@Given("^the user navigates to \"([^\"]*)\" and post an request using the file$")
		public void the_user_navigates_to_and_post_an_requst_using_the_file(String url) throws Throwable {
			
			System.out.println("Insdide add book");
			RestAssured.baseURI = url;
			
			String data = given()
								.headers("Content-Type","application/json")
								.body(GenerateStringFromResource("/cucumberframework/src/test/java/files/Static.json"))
						.when()
								.post("addbook.php")
						.then()
								.extract().response().asString();	
			JsonPath js = CommonFunction.rawToJson(data);
			String ids = js.get("id");
			System.out.println("the id is: "+ids);
		}
		
		public static String GenerateStringFromResource(String path) throws IOException {
			
			return new String(Files.readAllBytes(Paths.get(path)));
		}

}
