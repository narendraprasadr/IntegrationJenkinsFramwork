Feature: Using the session and attachements

	@web
  Scenario Outline: Session filter and attachment methods
    Given the user creates session for the "<url>"
    When the user POST and issues type bug and the "<statusCode>"
    And the user attaches an "<file>"
    And get the issues via get call
    

     Examples:
		|url                    |statusCode|file                                               | 
		|http://localhost:8080  |201       |/Users/nprasad/Documents/CucumberFramework/src/test/java/files/JiraTest.txt|