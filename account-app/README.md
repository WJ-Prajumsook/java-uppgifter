## account-app

account-app is the REST API build with Microprofile 4.0 and an Open Liberty.

To run this application on your local environment follow the step below:

To run the build and install:

```
mvn clean install
```

To start the Open liberty server:
```
mvn liberty:run
```

Or to run the server with DEV MODE:
```
mvn liberty:dev
```
To run Unit tests:
```
mvn clean test
```

Then you can access these endpoints.

List accounts:
```
GET: http://localhost:9090/api/account
```
Get account by ID:
```
GET: http://localhost:9090/api/account/{accountID}
```
Create account:
```
POST: http://localhost:9090/api/account

request body:
{
    "accountName": "Savings Account",
    "balance": "234.000",
    "id": "test_account_02224"
}
```

Open API can be found at:
```
/openapi/ui/
```

