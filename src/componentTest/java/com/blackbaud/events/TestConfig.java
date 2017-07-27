package com.blackbaud.events;

import com.blackbaud.testsupport.BaseTestConfig;
import org.springframework.context.annotation.Bean;
import com.blackbaud.events.client.EventClient;

import com.blackbaud.testsupport.TestTokenSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig extends BaseTestConfig {

    @Autowired
    TestTokenSupport testTokenSupport;

    @Bean
    public EventClient eventClient() {
        return new EventClient(hostUri)
                .header(testTokenSupport.createTestTokenHeader());
    }

}
