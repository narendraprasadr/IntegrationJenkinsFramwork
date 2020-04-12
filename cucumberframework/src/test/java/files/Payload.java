package files;

public class Payload {
	
	public static String coursePrice() {
		
		return "[\n" + 
				"  {\n" + 
				"    \"title\": \"Selenium python\",\n" + 
				"    \"price\": 50,\n" + 
				"    \"copies\": 6\n" + 
				"  },\n" + 
				"  {\n" + 
				"    \"title\": \"Cypress\",\n" + 
				"    \"price\": 40,\n" + 
				"    \"copies\": 4\n" + 
				"  },\n" + 
				"  {\n" + 
				"    \"title\": \"RPA\",\n" + 
				"    \"price\": 45,\n" + 
				"    \"copies\": 3\n" + 
				"  },\n" + 
				"  {\n" + 
				"    \"dashboard\": {\n" + 
				"      \"purchaseAmount\": \"100\",\n" + 
				"      \"website\": \"unacademy.com\"\n" + 
				"    },\n" + 
				"    \"id\": \"1\"\n" + 
				"  }\n" + 
				"]";
		
	}
	
	public static String addBook(String id, String isbn) {
		return
			" {\n" + 
			"  \"name\": \"Learn Appium Automation with Java\",\n" + 
			"  \"isbn\": \""+isbn+"\",\n" + 
			"  \"aisle\": \""+id+"\",\n" + 
			"  \"author\": \"John foe\",\n" + 
			"  \"id\": \""+id+isbn+"\"\n" + 
			"}";
	}

}
