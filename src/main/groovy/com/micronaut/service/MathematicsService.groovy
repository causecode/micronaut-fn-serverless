package com.micronaut.service

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
@CompileStatic
@Slf4j
class MathematicsService {
    @Value('${helloMicronaut.greeting:Hello Causecode}')
    String greeting

    List<Integer> evenNumbers = [2, 4, 6, 8] as ArrayList

    List<Integer> getEvenNumbers() {
       return evenNumbers
    }

    String getName(String name) {
        log.debug 'This is sample logging from the micronaut...'

        return name ?: greeting
    }
}
