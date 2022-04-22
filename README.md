# Capital One Take Home Interview Project

Congratulations on distinguishing yourself to make it to this stage of the Capital One interview process.

This application is a Java SpringBoot application where you will be enhancing the functionality to handle 
additional use cases described below. You will also be adding to the test suite to make sure the application is 
behaving as expected.

## Application Overview

This is the beginnings of a scheduled transfer application. Current functionality supports the creation of a scheduled 
transfer with some basic validation. 
Your goal is to add the following features in priority order:
    
    1. Retrieval of scheduled transfers by an account number
        - For example, based on the mock data in `data.sql`, a GET request with account `987654321` should return all
        3 mock transfers since the account number matches the TO account or the FROM account for each row.
    2. Editing the memo, start date, amount of a transfer via confirmation number
    3. Deleting a transfer via confirmation number
    4. Any additional endpoints you see could be valuable to a "customer"
     
Your new functionality is expected to handle most common validation scenarios (i.e. users cannot edit a scheduled 
transfer to the past, users cannot delete a transfer that has already been deleted).

All new functionality should have appropriate (> 80%) test coverage via JUnit and Cucumber tests. Some examples have 
been provided.

Feel free to make any changes to the existing code as needed and to be creative with your functionality/business requirements.

## Application Structure
The application has a defined structure with 2 main models. The `take-home-interview-app` module houses the main Spring
Boot application and corresponding unit tests. `take-home-interview-test` contains the cucumber (component/integration)
tests.

The application also has a in-memory H2 database. The schema of which can be found in `src/main/resources/schema.sql`.
Some data has been pre-populated via `src/main/resources/data.sql`. Each time the application is started, the database
is reset.

## Example POST request
The following is an example `curl` that can be used against the application immediately out of the box:

```
curl --location --request POST 'http://localhost:8080/transfers' \
--header 'Content-Type: application/json' \
--data-raw '{
	"transferDate": "2022-10-31",
	"toAccountNumber":"23423432",
    "fromAccountNumber":"123456789",
    "amount": 10.00,
    "memo":"Babysitter"
}'
```

## Criteria
A successful application will have the following:
 
    - At least 4 functional CRUD endpoints
        - Creating a scheduled transfer
        - Retrieval of scheduled transfers
        - Updating a scheduled transfer
        - Deleting a scheduled transfer
    - Validations on invalid inputs (negative amounts, date in the past, etc)
    - Test converage that is above 80% on new code
    - Cucumber tests covering each scenario
    - Clear and readable code
    
 