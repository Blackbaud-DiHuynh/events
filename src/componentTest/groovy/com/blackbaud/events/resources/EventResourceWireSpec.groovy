package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import spock.lang.Specification

@ComponentTest
class EventResourceWireSpec extends Specification {

    @Autowired
    private RESTClient client

    @Value("http://localhost:\${server.port}")
    private final String BASE_URI

}
