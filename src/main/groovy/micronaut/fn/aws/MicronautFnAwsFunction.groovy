package micronaut.fn.aws

import com.micronaut.customtypes.CustomRequest
import com.micronaut.customtypes.CustomResponse
import com.micronaut.service.CustomerService
import com.micronaut.service.InterestService
import com.micronaut.service.MathematicsService
import groovy.transform.CompileStatic
import groovy.transform.Field

import javax.inject.Inject

@Field @Inject MathematicsService mathematicsService
@Field InterestService interestService
@Field CustomerService customerService

/**
 * This method tests the Service Injection only.
 * @return
 */
@CompileStatic
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
