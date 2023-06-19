### Problem Statements

- Navigate to https://open-meteo.com/en/docs and find city wellington and get the API end point for weather data in wellington.
- Using any opensource tool of your choice please automate the test case.
- Add validations to the response appropriately.
- Using similar approach ,automate a testcase to find weather data for Auckland.
- For Auckland add daily weather variables, weather code and sunrise without passing the mandatory time zone parameter, capture and validate the response.

### Test Solution

Created the following tests by utilising below approach:

##### A. Positive Scenario

1. Sending a GET request to the api endpoint using the required parameters.
2. Validating the HTTP response code.
3. Validating the JSON schema from the JSON response.
4. Validating the JSON response / values based on the input parameter/s.

##### B. Negative Scenario
1. Sending a GET request to the api endpoint with missing parameters.
2. Validating the HTTP response code.
3. Validating the error response.

### Technology Stack
```
Automation Tool - RestAssured (Java library)
Design Pattern - Cucumber (BDD)
Programming Language - Java
Test Framework / Runner - JUnit
Project Lifecycle - Maven
```

### JAVA Libraries

```
Runner - io.cucumber
API & JSON Schema Validator - io.rest-assured
Logging - org.slf4j
Assertion - org.testng
Extracting Data from the JSON elements - org.json
```

### BASIC INSTALLATION & SETUP

1. Download and install IntelliJ into your computer.
```
https://www.jetbrains.com/idea/download/#section=windows
```
2. Download Java into your computer.
3. Download Maven into your computer.
4. Open the IntelliJ, navigate to File >> Settings >> Plugins and install Cucumber for Java


### RUNNING THE AUTOMATION FRAMEWORK

1. Import the project using the GitHub repositories below.
```
  https://github.com/sjgrospe/meteo.git
```
3. To run the tests as 'Feature' File, perform any of the following:
- Click on 'Run' icon and select Run 'Feature:<featureName>' or
-  Navigate to the Feature file itself - e.g., ...\src\test\java\features\open-meteo.feature then right click on the page to select Run 'Feature:<feautureName>.

3. To run by 'Scenario', right click on the scenario name to select Run 'Scenario:<scenarioName>'.

### CHALLENGES/BUGS FOUND WHEN IMPLEMENTING SOLUTION

- Positive scenario are failing due to incorrect json response as being compared to input parameters: e.g., Longitude and Latitude

Note: Added only few data validation for demo purposes

### COMMON TROUBLESHOOTING
- Sending request to the api using POSTMAN

