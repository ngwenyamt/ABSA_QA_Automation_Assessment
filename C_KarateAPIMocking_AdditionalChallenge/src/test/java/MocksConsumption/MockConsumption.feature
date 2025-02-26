Feature: Mock Consumption
  # This feature file contains scenarios to test the user management API using mock data.

  Background:
    # Set the base URL for the mock endpoint
    Given url MockEndPoint
    # Import utility functions from the Utils class
    * def KarateUtil = Java.type('Utils.Utils')
    # Define the relative path for the API endpoints
    * def RelativePath = '/angularjs-protractor/apitables/'
    # Read the username from a file
    * def getUsername = read('file:src/test/java/Responses/Username.txt')
    # Define a function to generate a random username
    * def randomUserName =
        """
      function(s){
      var text = "";
      var pattern = "abcdefghijklompqrstuvwxyz";
      for(var i=0; i<s; i++)
         text += pattern.charAt(Math.floor(Math.random() * pattern.length()));
         return text;
      }
        """
    # Configure a delay after each scenario
    * configure afterScenario =
        """
      function(){
           var Thread = Java.type('java.lang.Thread');
           Thread.sleep(4000);
      }
        """
#
  @scenario1
  Scenario: Validate user listing with valid username
    # Send a GET request to retrieve user details for a valid username
    Given path RelativePath + 'tman'
    When method GET
    Then status 200
    * print response
    # Extract data from the response
    * string firstName = get response.data[0].firstName
    * string lastName = get response.data[0].lastName
    * string role = get response.data[0].role
    * string cell = get response.data[0].cell
    * string email = get response.data[0].email
    * string username = get response.data[0].username
    * string customer = get response.data[0].customer
    # Validate the extracted data against expected values
    * match firstName == 'tebogo'
    * match lastName == 'ngwenya'
    * match role == 'Admin'
    * match cell == '082555'
    * match email == 'admin@gmail.com'
    * match username == 'tman'
    * match customer == 'Company AAA'

  @scenario2
  Scenario: Validate user listing with invalid username
    # Send a GET request to retrieve user details for an invalid username
    Given path RelativePath + 'invalidUser'
    When method GET
    Then status 404
    * print response
    # Extract error message from the response
    * string error = get response.error
    # Validate the error message
    * match error == 'User not found!'

  @scenario3
  Scenario: Add user to the User List Table
    # Generate a random username
    * def generatedUserName = 'autoUser' + randomUserName(6)
    # Read the payload from a JSON file and set the generated username
    * def getAuthTokenBody = read('file:src/test/java/MocksConsumption/AddUserPayLoad.json')
    * getAuthTokenBody.username = generatedUserName
    # Send a POST request to add a new user
    Given path RelativePath
    And request getAuthTokenBody
    When method POST
    Then status 200
    * print response
    # Validate the response data
    * match response.data.Listed[0] == generatedUserName
    * match response.metadata.result[0].status == 'Success'
    * match response.metadata.result[0].code == 'R000'
    * match response.metadata.result[0].description == 'Success'
    # Write the generated username to a file
    * KarateUtil.WriteToFile('src/test/java/Responses/Username.txt', generatedUserName)

  @scenario5
  Scenario: Get the user added in the User List Table
    # Send a GET request to retrieve the details of the newly added user
    Given path RelativePath + 'dynamic/' + getUsername
    When method GET
    Then status 200
    * print response
    # Extract data from the response
    * string firstName = get response.data[0].firstName
    * string lastName = get response.data[0].lastName
    * string role = get response.data[0].role
    * string cell = get response.data[0].cell
    * string email = get response.data[0].email
    * string username = get response.data[0].username
    * string customer = get response.data[0].customer
    # Validate the extracted data against expected values
    * match firstName == 'FName1'
    * match lastName == 'LName2'
    * match role == 'Admin'
    * match cell == '082555'
    * match email == 'admin@gmail.com'
    * match username == getUsername
    * match customer == 'Company AAA'