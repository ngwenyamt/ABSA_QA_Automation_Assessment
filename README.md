# AUTOMATION FRAMEWORK- API (KARATE) & WEB (UI) SELENIUM WITH JAVA

This project is a modular automation testing framework designed to handle both API and Web (UI) testing. The API module leverages the Karate Framework for streamlined RESTful API testing, while the Web (UI) module uses Selenium with Java for browser-based testing. This modular approach ensures ease of maintenance, scalability, and flexibility in test execution.

## 1. SETUP INSTRUCTIONS
- Ensure JDK 19.0.1 is installed.
- Clone the repository.
- Import the project into your IDE (e.g., IntelliJ IDEA).
- Install Maven dependencies by running `mvn clean install`.

## 2. HOW TO RUN THE TESTS
- **Local Execution**: Tests can only be executed using the `TestRunner` class. Maven test execution is not configured.
- **API Tests**: Run the `TestRunner` class located in the `src/test/java` directory.
- **Web (UI) Tests**: Run the `TestRunner` class located in the `src/test/java` directory.
- **APIMockServer Tests**: Run the `TestRunner` class located in the `src/test/java` directory. Note: before you can run the test runner make sure that your server has started using the class named 'StartMockServer.java'

## 3. PROJECT STRUCTURE OVERVIEW


- `A_API_Karate/src/test/java`: Contains the test classes and test resources.
- `B_Web_Selenium/src/main/java`: Contains the main application code such as MainClass, Enablers etc..
                      `/test/java`: Contains the test consumption classes and test resources.
- `C_KarateAPIMocking_AdditionalChallenge/src/test/java`: Contains the test classes and test resources such as StartMockServer Class, Utils etc..

## 4. ASSUMPTIONS OR DECISIONS MADE
- Karate was chosen for API testing because it contains pre-defined methods, making it faster and easier to write tests.
- The framework is designed to be modular to ensure ease of maintenance and scalability.

## 5. LIST OF TOOLS AND RESOURCES USED
- **Karate Framework**: For API testing using REST Protocol.
- **Selenium**: For Web (UI) testing.
- **Java**: Programming language.
- **Javascript**: Programming language.
- **Maven**: Build automation tool.
- **IntelliJ IDEA**: Integrated Development Environment (IDE).

## API Module
- Uses the Karate framework for API testing.
- Supports writing tests in a BDD style, enabling easy-to-read test scripts.

## Web Module
- Uses Selenium with Java for browser-based testing.
- Supports cross-browser testing capability.
- Supports multiple operating systems (Windows, Mac OS, Linux).
- Test evidence and reports can be viewed in the target directory after test execution.

## APIMockServer Module
- Contains configurations and feature files for mocking API responses.

## Configuring `settings.xml` File
To configure the `settings.xml` file under the `.m2` folder, follow these steps:

1. Navigate to the `.m2` directory in your user home folder.
2. Create or edit the `settings.xml` file with the following content:
   <img src="target/cucumber-html-reports/report1.png" alt="Description">
```xml
<settings>
  <localRepository>/path/to/local/repo</localRepository>
  <mirrors>
    <mirror>
      <id>central</id>
      <mirrorOf>central</mirrorOf>
      <url>https://repo.maven.apache.org/maven2</url>
    </mirror>
  </mirrors>
  <profiles>
    <profile>
      <id>default</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo.maven.apache.org/maven2</url>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>default</activeProfile>
  </activeProfiles>
</settings>


