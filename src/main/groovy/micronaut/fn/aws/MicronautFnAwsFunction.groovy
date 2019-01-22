package micronaut.fn.aws

import com.micronaut.customtypes.CustomMessage
import com.micronaut.service.InterestService
import com.micronaut.service.MathematicsService
import groovy.transform.Field

import javax.inject.Inject

@Field @Inject MathematicsService mathematicsService
@Field InterestService interestService

/**
 * This method tests the Service Injection only.
 * @return
 */
String printServicesMessage(CustomMessage customMessage) {
    return "${interestService.methodName} and ${mathematicsService.getEvenNumbers()} and " +
            "Custom Message: ${customMessage.message}"
}
