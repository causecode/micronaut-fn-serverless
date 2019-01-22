package micronaut.fn.aws

import con.micronaut.service.InterestService
import con.micronaut.service.MathematicsService
import groovy.transform.Field

import javax.inject.Inject

@Field @Inject MathematicsService mathematicsService
@Field InterestService interestService

/**
 * This method tests the Service Injection only.
 * @return
 */
String printServicesMessage() {
    return "${interestService.methodName} and ${mathematicsService.getEvenNumbers()}"
}
