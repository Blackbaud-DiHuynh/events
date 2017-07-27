package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.DynamicRule
import com.blackbaud.events.api.Event
import com.blackbaud.events.client.DynamicRuleClient
import com.blackbaud.events.client.EventClient
import com.blackbaud.events.client.TicketClient
import com.blackbaud.events.core.domain.DynamicRuleRepository
import com.blackbaud.events.core.domain.EventRepository
import com.blackbaud.events.core.domain.TicketRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class DynamicRuleResourceSpec extends Specification {

    @Autowired
    DynamicRuleClient dynamicRuleClient;

    @Autowired
    DynamicRuleRepository dynamicRuleRepository;

    def "can create a new dynamic rule"() {
        given:
        DynamicRule rule = aRandom.dynamicRule().build()

        when:
        DynamicRule createdRule = dynamicRuleClient.create(rule)

        then:
        null != dynamicRuleRepository.findOne(createdRule.id)
    }
}

