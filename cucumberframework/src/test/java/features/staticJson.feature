Feature: Accessing the Dynamic json like 

	@web
  Scenario Outline: Validate Dynamic json response 
    Given the user navigates to "<url>" and post an request using the file

     Examples: 
		|url                     |statusCode|
		|http://localhost:3000/  |200       |