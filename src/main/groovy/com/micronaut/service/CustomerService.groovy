package com.micronaut.service

import com.micronaut.customtypes.Customer
import com.micronaut.data.CustomerRecords
import com.micronaut.exception.InvalidIdException
import groovy.transform.CompileStatic
import javax.inject.Singleton
import io.micronaut.context.annotation.Value

@Singleton
@CompileStatic
class CustomerService {
    @Value('${customerService.id: 1}')
    String id

    Customer findCustomerById(String id) {
        Customer customer = CustomerRecords.CUSTOMER_RECORDS_MAP.get(id.toInteger())

        if (!customer) {
            throw new InvalidIdException("No customer found with id: ${id}. Please input a valid id")
        }

        return customer
    }
}
