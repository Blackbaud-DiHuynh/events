package com.blackbaud.events;

import com.blackbaud.events.client.DynamicRuleClient;
import com.blackbaud.events.client.EventClient;
import com.blackbaud.events.client.TicketClient;
import com.blackbaud.events.client.TransactionClient;
import com.blackbaud.testsupport.BaseTestConfig;
import com.blackbaud.testsupport.TestTokenSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public TicketClient ticketClient() {
        return new TicketClient(hostUri)
                .header(testTokenSupport.createTestTokenHeader());
    }

    @Bean
    public DynamicRuleClient dynamicRuleClient() {
        return new DynamicRuleClient(hostUri)
                .header(testTokenSupport.createTestTokenHeader());
    }

    @Bean
    public TransactionClient transactionClient() {
        return new TransactionClient(hostUri)
                .header(testTokenSupport.createTestTokenHeader());
    }

}
