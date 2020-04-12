package stepdefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.CommonFunction;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {
	
	static SessionFilter session = new SessionFilter();
	String commentId;
	
	
	@Given("^the user creates session for the \"([^\"]*)\"$")
	public void the_user_creates_session_for_the(String url) throws Throwable {
		RestAssured.baseURI = url;
		
		//Getting session
		String response=given()
						.header("Content-Type","application/json")
					    .body("{\n" + 
							"	\"username\": \"narendra\",\n" + 
							"	\"password\": \"Jira1234!\"\n" + 
							"}")
					    .log().all()
					    .filter(session)
			    .when()
					    .post("/rest/auth/1/session")
				.then()
					    .extract()
					    .response().asString();
		System.out.println("The access token response is: "+response);    
	}
	
	
	@When("^the user POST and issues type bug and the \"([^\"]*)\"$")
	public void the_user_and_issues_type_bug(String expectedStatusCode) throws Throwable {
		
		//RestAssured.baseURI = url;
		String msg = "This comment is from automation script";
		//add comment
	String addCommentResponse =	given()
			.pathParam("key", "RAC-1")
			.log().all()
			.header("Content-Type","application/json")
			.filter(session)
	.when()
			.body("{\n" + 
				"	\"body\":\""+msg+"\",\n" + 
				"	\"visibility\": {\n" + 
				"		\"type\":\"role\",\n" + 
				"		\"value\": \"Administrators\"\n" + 
				"	}\n" + 
				"}")
			.post("/rest/api/2/issue/{key}/comment")
	.then()
			.log().all()
			.assertThat().statusCode(201).extract().response().asString();
	JsonPath js =CommonFunction.rawToJson(addCommentResponse);
    commentId = js.getString("id").toString();
	System.out.println("The newly created comment id is: "+commentId);
	
		
	}
	
	@When("^the user attaches an \"([^\"]*)\"$")
	public void the_user_attaches_an(String path) throws Throwable {
		//RestAssured.baseURI = url;
		System.out.println("the path value is: "+ path);
	//add attachment	
	given()
			.header("X-Atlassian-Token","no-check")
			.filter(session)
			.pathParam("key", "RAC-1")
			.header("Content-Type","multipart/form-data")
			.multiPart("file", new File(path))
	.when()
			.post("/rest/api/2/issue/{key}/attachments")
	.then()
			.log().all().assertThat().statusCode(200);	
	}

	@When("^get the issues via get call$")
	public void get_the_issues_via_get_call() throws Throwable {
		//RestAssured.baseURI = url;
		
	//get call using query parameter	
	String issuesDetails=given()
			.relaxedHTTPSValidation()
			.filter(session)
			.pathParam("key", "RAC-1")
			.queryParam("fields", "comment")
			.log().all()
	.when()
			.get("/rest/api/2/issue/{key}")
	.then()
			.log().all().extract().response().asString();
	System.out.println("The issue detail is:" + issuesDetails);
	
	JsonPath js1 = CommonFunction.rawToJson(issuesDetails);	
	int commentCount = js1.getInt("fields.comment.comments.size()");
	for(int i=0;i<commentCount;i++) {
		String ids = js1.get("fields.comment.comments["+i+"].id").toString();
		if(commentId.equalsIgnoreCase(ids)) {
			String message=js1.get("fields.comment.comments["+i+"].body");
			System.out.println("The matching comment msg is"+ message);
		}
	}
	}

}
