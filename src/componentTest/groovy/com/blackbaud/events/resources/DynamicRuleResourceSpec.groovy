package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.DynamicRule
import com.blackbaud.events.client.DynamicRuleClient
import com.blackbaud.events.core.domain.DynamicRuleRepository
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

    def "can get all dynamic rules for a ticket"() {
        given:
        Integer ticketId = aRandom.intId()
        DynamicRule rule1 = dynamicRuleClient.create(aRandom.dynamicRule().ticketId(ticketId).build())
        DynamicRule rule2 = dynamicRuleClient.create(aRandom.dynamicRule().ticketId(ticketId).build())

        when:
        List<DynamicRule> rules = dynamicRuleClient.findManyByTicketId(ticketId)

        then:
        rules[0] == rule1
        rules[1] == rule2
    }

    def "can delete a dynamic rule"() {
        given:
        Integer ticketId = aRandom.intId()
        DynamicRule rule = dynamicRuleClient.create(aRandom.dynamicRule().ticketId(ticketId).build())

        when:
        dynamicRuleClient.delete(rule.id)

        then:
        dynamicRuleClient.findManyByTicketId(ticketId) == []
    }

    def "trying to delete a nonexistent rule should not throw errors"() {
        when:
        dynamicRuleClient.delete(aRandom.intId())

        then:
        noExceptionThrown()
    }

}

