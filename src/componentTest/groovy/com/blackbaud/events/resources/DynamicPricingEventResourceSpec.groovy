package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.DynamicRule
import com.blackbaud.events.api.Event
import com.blackbaud.events.client.DynamicRuleClient
import com.blackbaud.events.core.domain.DynamicRuleRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class DynamicPricingEventResourceSpec extends Specification {

    @Autowired
    DynamicRuleClient dynamicRuleClient;

    @Autowired
    DynamicRuleRepository dynamicRuleRepository;


    def "when an event reaches inventory threshold, ticket price should automatically adjusts"() {
    }

}

