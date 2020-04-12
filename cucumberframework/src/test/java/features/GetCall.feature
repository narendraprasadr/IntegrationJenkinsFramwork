#Author: freeautomationlearning@gmail.com
Feature: Google Search

	@web
  Scenario Outline: Validate google search text field
    Given the user navigates to "<url>" via get calls

     Examples: 
		|url                          |statusCode|
		|http://localhost:3000/posts  |200       |