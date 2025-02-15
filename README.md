
# TodoMVC Automation Framework

This project automates the testing of the TodoMVC React Application using Selenium,
Java, and Cucumber (BDD) with a Page Object Model (POM) structure.

## Pre-requisites:

Ensure you have the following installed:

#### 1. Java 8+ – Check with:
````
 java -version
`````
#### 2. Maven – Check with:
````
mvn -version
`````
#### 3. Google Chrome – Required for running tests in Chrome.

#### 4. ChromeDriver – Automatically managed in Framework.



## Setup & Configuration

### 1. Clone the Repository
````
git clone <repository-url>
````
````
cd todomvc-automation
````

### 2. Configure Test Execution Settings

Modify src/test/resources/config.properties:
````
browser=chrome
url=https://todomvc.com/examples/react/dist/
````

### 3. Run the Tests

Option 1: Run All Tests
````
mvn test
````
Option 2: Run Specific Feature
````
mvn test -Dcucumber.options="src/test/resources/features/addTodo.feature"
````


## Test Execution & Reports

### View Cucumber HTML Report

After execution, the report will be generated in:
````
target/cucumber-reports.html
````


## Additional Features

• Page Object Model (POM) for maintainability.

• Configurable browser settings in config.properties.

• Cucumber Reports for test execution details.


## Troubleshooting

### 1. WebDriver Exception

If you get WebDriverException, update ChromeDriver to match your Chrome version:

````
mvn clean test
````

###  2. Tests Not Running?

• Ensure you are in the project root directory.

• Run:
````
mvn clean test
````