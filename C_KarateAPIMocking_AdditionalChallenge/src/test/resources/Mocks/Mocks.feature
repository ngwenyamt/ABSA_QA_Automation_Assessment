Feature: User Management

  Background:
    # Set the default response headers
    * def responseHeaders = { 'Content-Type': 'application/json', 'Accept': 'application/json' }

  # Validate if user is listed in the User List Table
  Scenario: pathMatches('/angularjs-protractor/apitables/{username}') && methodIs('get')
    # Extract the username from the path parameters
    * def username = pathParams.username
    * print 'GET request received'
    * print 'username:', username

    # Mock user data
    * def globalUsername = 'tman'
    * def firstName = 'tebogo'
    * def lastName = 'ngwenya'
    * def customer = 'Company AAA'
    * def role = 'Admin'
    * def email = 'admin@gmail.com'
    * def cell = '082555'

    # Determine if the user is not found
    * def userNotFound = globalUsername == null || username != globalUsername
    * print 'userNotFound:', userNotFound

    # Define the 404 response
    * def response_404 =
    """
    {
      "error": "User not found!"
    }
    """

    # Define the 200 response
    * def response_200 =
    """
    {
      "data": [
        {
          "username": "#(globalUsername)",
          "firstName": "#(firstName)",
          "lastName": "#(lastName)",
          "customer": "#(customer)",
          "role": "#(role)",
          "email": "#(email)",
          "cell": "#(cell)"
        }
      ],
      "metadata": {
        "page": 1,
        "pageSize": null,
        "pageCount": 1,
        "validationErrors": null,
        "result": [
          {
            "status": "Success",
            "code": "R000",
            "description": "Success"
          }
        ]
      }
    }
    """

    # Set the response status and body based on whether the user was found
    * def status = userNotFound ? 404 : 200
    * def response = userNotFound ? response_404 : response_200

    # Set the response headers, status, and body
    * karate.set('responseHeaders', responseHeaders)
    * karate.set('responseStatus', status)
    * karate.set('response', response)

    * print 'Final status:', status
    * print 'Final response:', response

  # Add User to User List Table
  Scenario: pathMatches('/angularjs-protractor/apitables/') && methodIs('post')
    # Extract user details from the request payload
    * def username = request.username ? request.username : 'undefined'
    * def firstName = request.firstName ? request.firstName : 'undefined'
    * def lastName = request.lastName ? request.lastName : 'undefined'
    * def password = request.password ? request.password : 'undefined'
    * def customer = request.customer ? request.customer : 'undefined'
    * def role = request.role  ? request.role  : 'undefined'
    * def email = request.email  ? request.email  : 'undefined'
    * def cell = request.cell  ? request.cell  : 'undefined'

    # Set global variables with the extracted user details
    * karate.set('globalUsername', username)
    * karate.set('globalFirstName', firstName)
    * karate.set('globalLastName', lastName)
    * karate.set('globalPassword', password)
    * karate.set('globalCustomer', customer)
    * karate.set('globalRole', role)
    * karate.set('globalEmail', email)
    * karate.set('globalCell', cell)

    * print 'POST request received'
    * print request
    * print requestHeaders

    # Set the response status to 200
    * def status = 200
    * karate.set('responseHeaders', responseHeaders)

    # Define the response body
    * def response =
    """
    {
      "data": {
        "notListed": [],
        "Listed": [
          "#(username)"
        ]
      },
      "metadata": {
        "page": 0,
        "pageSize": 0,
        "pageCount": 0,
        "validationErrors": null,
        "result": [
          {
            "status": "Success",
            "code": "R000",
            "description": "Success"
          }
        ]
      }
    }
    """

    # Set the response status and body
    * karate.set('responseStatus', status)
    * karate.set('response', response)
    * print response

  # Get the user added in the User List Table
  Scenario: pathMatches('/angularjs-protractor/apitables/dynamic/{username}') && methodIs('get')
    # Extract the username from the path parameters
    * def username = pathParams.username
    * print 'GET request received'
    * print 'username:', username

    # Retrieve global variables for the user details
    * def globalUsername = karate.get('globalUsername')
    * def firstName = karate.get('globalFirstName')
    * def lastName = karate.get('globalLastName')
    * def customer = karate.get('globalCustomer')
    * def role = karate.get('globalRole')
    * def email = karate.get('globalEmail')
    * def cell = karate.get('globalCell')

    # Determine if the user is not found
    * def userNotFound = globalUsername == null || username != globalUsername
    * print 'userNotFound:', userNotFound

    # Define the 404 response
    * def response_404 =
    """
    {
      "error": "User not found!"
    }
    """

    # Define the 200 response
    * def response_200 =
    """
    {
      "data": [
        {
          "username": "#(globalUsername)",
          "firstName": "#(firstName)",
          "lastName": "#(lastName)",
          "customer": "#(customer)",
          "role": "#(role)",
          "email": "#(email)",
          "cell": "#(cell)"
        }
      ],
      "metadata": {
        "page": 1,
        "pageSize": null,
        "pageCount": 1,
        "validationErrors": null,
        "result": [
          {
            "status": "Success",
            "code": "R000",
            "description": "Success"
          }
        ]
      }
    }
    """

    # Set the response status and body based on whether the user was found
    * def status = userNotFound ? 404 : 200
    * def response = userNotFound ? response_404 : response_200

    # Set the response headers, status, and body
    * karate.set('responseHeaders', responseHeaders)
    * karate.set('responseStatus', status)
    * karate.set('response', response)

    * print 'Final status:', status
    * print 'Final response:', response