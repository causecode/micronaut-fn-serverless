# Micronaut-function 1.0.3 on AWS - Lambda

#### Requirements:

Micronaut 1.0.3

Gradle 4.8

Java 1.8

AWS Credentials stored at /.aws/credentials in the home directory which can be created from the "My security credentials" section from the AWS dashboard.
The credentials file has a form like

```
[default]
region=ap-south-1
aws_access_key_id=##################
aws_secret_access_key=##################
```

The default is profile name which can be changed if we are using multiple profiles based on the region. `default` is used in the build.gradle at root of this project as

```
 aws {
     profileName = "default"
     region = 'ap-south-1'
 }
 ```

### Set the AWS Lambda function region
To set the region of lambda function, add the following block in the build.gradle file and this can be changed according to the region in use.


```
lambda {
    region = 'ap-south-1'
}
```

In this app, the main function is defined in the `MicronautFnAwsFunction` class and should be declared in the config as

```
mainClassName = "micronaut.fn.aws.MicronautFnAwsFunction"
```

It has a method named `executeMnAwsFunction` which is invoked when we hit the lambda function.

## Deployments
For creating a JAR of the function:
```
micronaut-fn-aws git:(master) ✗ ./gradlew assemble
```

To deploy the micronaut function on the AWS Lambda or testing locally, we have setup and tested this function with the following steps:

### Deploy the micronaut function to the AWS Lambda

#### To deploy the fucntion on AWS directly from the console:

```
micronaut-fn-aws git:(master) ✗ ./gradlew deploy

```

### GET and POST requests (Only supported)
#### POST Request:
To make a post request, add your request body in the `payload` as when you test from the console using `invoke` gradle task.

```
 payload = '{"customMessage": "This is custom message from user.", "customerId": "1"}'
```
The arguments can be customized based on the need in the method defined `executeMnAwsFunction` by changing its parameters. 
For an example:

The defined micronaut function is

```
CustomResponse executeMnAwsFunction(CustomRequest request) {
    String customerId = request.customerId

    CustomResponse customResponse = new CustomResponse()

    if (customerId) {
        customResponse.customer = customerService.findCustomerById(customerId)
    }

    customResponse.fromInterestService = interestService.methodName
    customResponse.fromMathService = mathematicsService.getEvenNumbers()

    customResponse.message = request.customMessage ?: 'No message from console'

    return customResponse
}
```

It accepts a request, a custom type, argument only.

#### GET Request:
To make a GET request, you do not need a `payload` and micronaut function should be no args.

### Test the deployed function using the test events
Create a test event using the request body:

```
{
  "customMessage": "This is custom message from user.",
  "customerId": "1"
}
```

and hit the test to see the results and summay details.

See the working:
![](https://github.com/causecode/micronaut-fn-serverless/blob/master/assets/micronaut-aws-fn-demo.gif)

##### From Executable Jars

Use the following commands, where option in {} denotes the input to the lambda function created.

```
libs git:(master) ✗ java -jar micronaut-fn-aws-0.1-all.jar '{"customMessage": "This is custom message from user.", "customerId": 1}'
```

#### Test the deployed AWS Lambda function from terminal using gradle tasks

##### Result of AWS Lambda when we invoke the function from console:
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

#### Test when we pass wrong inputs (Wrong customer id in this function)
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
