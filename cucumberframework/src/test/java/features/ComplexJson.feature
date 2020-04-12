Feature: Accessing the complex json like array elements

	@web
  Scenario Outline: Validate Complex response 
    Given the user navigates to "<url>" for accessing the total course

     Examples: 
		|url                          |statusCode|
		|http://localhost:3000/posts  |200       |