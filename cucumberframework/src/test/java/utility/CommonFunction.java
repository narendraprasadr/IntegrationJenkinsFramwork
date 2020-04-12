package utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonFunction {
	
	public static JsonPath rawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}
	
	
}
