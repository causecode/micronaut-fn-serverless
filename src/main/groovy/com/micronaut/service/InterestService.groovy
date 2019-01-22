/*
 * Copyright (c) $today.year-Present, CauseCode Technologies Pvt Ltd, India.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are not permitted.
 */

package com.micronaut.service

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Value

@CompileStatic
class InterestService {
    @Value('${interestService.message: This is getMethodName from InterestService.}')
    String message

    String getMethodName() {
        return message
    }
}
