Feature: Pet Finder

  Background:
    # Set the base URL for the PetFinder API
    Given url PetFinderBaseUrl
    # Load the utility class for writing to files
    * def KarateUtil = Java.type('Utils.Enablers')
    # Set the Accept header to the content type
    * header Accept = ContentType
    # Read the OAuth token from the file
    * def getOAuthToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Configure a delay after each scenario
    * configure afterScenario =
      """
      function(){
           var Thread = Java.type('java.lang.Thread');
           Thread.sleep(4000);
      }
      """

  @GetOAuthAccessToken
  Scenario: Get an OAuth2.0 access token
    # Read the request body for getting the OAuth token
    * def getOAuthTokenBody = read('file:src/test/java/Payloads/GetOathAccessTokenBody.json')
    # Set the path for the OAuth token endpoint
    When path '/v2/oauth2/token'
    # Set the request body
    And request getOAuthTokenBody
    # Make a POST request
    And method POST
    # Verify the response status is 200
    Then status 200
    # Print the response
    * print response
    # Extract the access token from the response
    * def OAuthToken = get response.access_token
    # Write the access token to a file
    * KarateUtil.WriteToFile('src/test/java/Tokens/OauthAccessToken.txt', OAuthToken)

  @RetrieveListOfAllAnimalTypesUsingGetMethod
  Scenario: Retrieve a list of all animal types available on pet finder using Get method
    # Read the access token from the file
    * def accessToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Set the Authorization header with the access token
    * header Authorization = 'Bearer ' + accessToken
    # Set the path for retrieving animal types
    When path '/v2/types'
    # Make a GET request
    And method GET
    # Verify the response status is 200
    Then status 200
    # Print the response
    * print response

  @RetrieveListOfAllAnimalTypesUsingPostMethod
  Scenario: Retrieve a list of all animal types available on pet finder using Post method
    # Read the access token from the file
    * def accessToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Set the Authorization header with the access token
    * header Authorization = 'Bearer ' + accessToken
    # Set the path for retrieving animal types
    When path '/v2/types'
    # Make a POST request
    And method POST
    # Verify the response status is 405 (Method Not Allowed)
    Then status 405
    # Print the response
    * print response

  @VerifyDogIsOneOfAnimalTypesReturned
  Scenario: Verify that Dog is one of the animal types returned
    # Read the access token from the file
    * def accessToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Set the Authorization header with the access token
    * header Authorization = 'Bearer ' + accessToken
    # Set the path for retrieving animal types
    When path '/v2/types'
    # Make a GET request
    And method GET
    # Verify the response status is 200
    Then status 200
    # Print the response
    * print response
    # Find the dog type in the response
    * def dogType = response.types.find(x => x.name == 'Dog')
    # Verify that the dog type is not null
    * match dogType != null

  @VerifyAnimalTypesDataStructure
  Scenario: Verify the data structure of the animal types returned
    # Read the access token from the file
    * def accessToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Set the Authorization header with the access token
    * header Authorization = 'Bearer ' + accessToken
    # Set the path for retrieving animal types
    When path '/v2/types'
    # Make a GET request
    And method GET
    # Verify the response status is 200
    Then status 200
    # Print the response
    * print response
    # Verify the response contains the types array
    * match response contains { types: '#[]' }
    # Verify the structure of the first type in the array
    * match response.types[0] contains { name: '#string', coats: '#[]', colors: '#[]', genders: '#[]' }

  @RetrieveListOfAllDogsBreedsAvailableOnPetFinder
  Scenario: Retrieve a list of all dog breeds available on pet finder
    # Read the access token from the file
    * def accessToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Set the Authorization header with the access token
    * header Authorization = 'Bearer ' + accessToken
    # Set the path for retrieving dog breeds
    When path '/v2/types/dog/breeds'
    # Make a GET request
    And method GET
    # Verify the response status is 200
    Then status 200
    # Print the response
    * print response
    # Verify the response contains the breeds array
    * match response contains { breeds: '#[]' }
    # Verify the structure of the first breed in the array
    * match response.breeds[0] contains { name: '#string' }

  @SearchForDogsOfTheGoldenRetriever
  Scenario: Search for dogs of the "golden retriever" breed and verify that at least one result is returned
    # Read the access token from the file
    * def accessToken = read('file:src/test/java/Tokens/OauthAccessToken.txt')
    # Set the Authorization header with the access token
    * header Authorization = 'Bearer ' + accessToken
    # Set the path for searching animals
    When path '/v2/animals'
    # Set the query parameter for the breed
    And param breed = 'golden retriever'
    # Make a GET request
    And method GET
    # Verify the response status is 200
    Then status 200
    # Print the response
    * print response
    # Filter the response to find golden retriever breeds
    * def goldenRetrieverBreeds = response.animals.filter(x => x.breeds.primary.toLowerCase() == 'golden retriever')
    # Verify that at least one golden retriever breed is found
    * assert goldenRetrieverBreeds.length > 0