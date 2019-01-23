package com.micronaut.data

import com.micronaut.customtypes.Address
import com.micronaut.customtypes.Country
import com.micronaut.customtypes.Customer

class CustomerRecords {
    public static final Map<Integer, Customer> CUSTOMER_RECORDS_MAP = [
            1: new Customer(
                    id: 1,
                    name: 'John',
                    address: new Address(
                            line: 'Pune',
                            zipCode: '411007',
                            country: new Country(
                                    name: 'India'
                            )
                    )
            )
    ]
}
