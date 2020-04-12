package stepdefinations;

import java.util.ArrayList;

import org.testng.Reporter;

import cucumber.api.java.en.Given;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	@Given("^the user navigates to \"([^\"]*)\" for accessing the total course$")
	public void the_user_navigates_to_for_accessing_the_total_course(String arg1) throws Throwable {
		System.out.println("Inside complex json function");
	    JsonPath jp = new JsonPath(Payload.coursePrice());
	    int count = jp.getInt("course.size()");
	    System.out.println("There are total course is:" +count);
	    	for(int i=0; i<count;i++) {
	    		String dat=jp.get("["+i+"].title");
	    	    System.out.println("The data is:" +dat);
	    	}
	    
	    
	    
	    
	  
	}

}
