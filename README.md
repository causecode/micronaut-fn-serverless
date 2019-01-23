# Micronaut-function 1.0.3 on AWS - Lambda

### Requirements:

Micronaut 1.0.3

Gradle 4.8

Java 1.8

AWS Credentials stored at /.aws/credentials in the home directory

### Deployments
To deploy the micronaut function on the AWS Lambda or testing locally, we have setup and tested this function with the following steps:

### Deploy the micronaut function to the AWS Lambda

####To deploy the fucntion on AWS directly from the console:
```
➜  micronaut-fn-aws git:(master) ✗ ./gradlew deploy
```

##### From Executable Jars

Use the following commands, where option in {} denotes the input to the lambda function created.

```
libs git:(master) ✗ java -jar micronaut-fn-aws-0.1-all.jar '{"customMessage": "This is custom message from user.", "customerId": 1}'
```

##### Test the deployed AWS Lambda function from terminal

###### Result of AWS Lambda when we invoke the function from console:
```
micronaut-fn-aws git:(master) ✗ ./gradlew invoke

> Task :invoke
Lambda function result:
    {
        "message":"This is custom message from user.",
        "customer":{
                    "id":1,
                    "name":"John",
                    "address":{
                                "line":"Pune",
                                "zipCode":"411007",
                                "country":{
                                            "name":"India"
                                         }
                             }
                  },
        "fromMathService":"[2, 4, 6, 8]",
        "fromInterestService":" This is getMethodName from InterestService."
    }
```

and see what happens when we pass the wrong details in the request params:

###### When we pass wrong customer Id:
In the `invoke` task, if we change the customerId to 2 which doesn't exists in the records,
Now our payload is 
```
payload = '{"customMessage": "This is custom message from user.", "customerId": "2"}' // 2 doesn't exists
```

then response will become

```
➜  micronaut-fn-aws git:(master) ✗ ./gradlew invoke

> Task :invoke
Lambda function result: 
    {   
        "errorMessage":"No customer found with id: 2. Please input a valid id",
        "errorType":"com.micronaut.exception.InvalidIdException"
    }
```
