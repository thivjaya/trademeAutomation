# Trademe Automation
This project encompasses both UI and API tests to ensure the 'robustness' and reliability of the application.

Tools used : Java(JDK) 11, Selenium 4.2, Rest Assured 5.4

Test framework : TestNG 7.10

Build tool/Dependency management : Maven 3.6

# Introduction
This project includes UI tests to validate Customer login scenarios, Basic search functionality, Process of Listing Item. 
In addition to that this project includes API tests to verify the API functionality of Retrieve Latest Listing,
and listing an item.

# Authentication
For API automation tests consumer key, access token, and OAuth signature are used to authenticate APIs. 
Follow the instructions provided in TradeMe Developer Portal(https://developer.trademe.co.nz/api-overview/authentication) to obtain these parameters. 
These parameters are stored as environment variables.

# Postman Collection
Postman collection(trademe.postman_collection.json) is included in the project to facilitate API testing.
Import the collection into postman and configure environment variables as required.

# Usage
UI Tests
1. Customer login scenarios : mvn test -Dtest=LoginTest
2. Basic Search functionality : mvn test -Dtest=SearchTest
3. List Items : mvn test -Dtest=ListItemTest

API Tests
1. Retrieve Latest Listing : mvn test -Dtest=RetrieveLatestListingAPITest
2. List items : mvn test -Dtest=ListItemAPITest


##Note## Trademe login function has reCaptcha feature which is not intended to automate. 
It is recommended to disable this feature before running automation.

