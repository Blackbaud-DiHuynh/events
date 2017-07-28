package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.Event
import com.blackbaud.events.api.Ticket
import com.blackbaud.events.api.Transaction
import com.blackbaud.events.client.DynamicRuleClient
import com.blackbaud.events.client.EventClient
import com.blackbaud.events.client.TransactionClient
import com.blackbaud.events.core.domain.DynamicRuleRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class DynamicPricingEventResourceSpec extends Specification {

    @Autowired
    DynamicRuleClient dynamicRuleClient

    @Autowired
    DynamicRuleRepository dynamicRuleRepository

    @Autowired
    EventClient eventClient

    @Autowired
    TransactionClient transactionClient

    int capacity = 100
    BigDecimal basePrice = BigDecimal.TEN

    def "when an event reaches inventory threshold, ticket price should automatically adjusts"() {
        given:
        Ticket ticket = aRandom.ticket().basePrice(basePrice).capacity(capacity).build()
        Event event = eventClient.create(aRandom.event().tickets([ticket]).build())

        and:
        dynamicRuleClient.create(aRandom.dynamicRule(event.firstTicket().id)
                                         .inventoryThreshold(50)
                                         .priceChange(5)
                                         .build())

        when:
        Event eventDetail = eventClient.find(event.id)

        then:
        eventDetail.tickets[0].currentPrice == basePrice

        when:
        sellTickets(eventDetail.firstTicket().id, 50)

        then:
        eventClient.find(event.id).firstTicket().currentPrice == basePrice + 5
    }

    def "should support multiple dynamic rules"() {
        given:
        Ticket ticket = aRandom.ticket().basePrice(basePrice).capacity(capacity).build()
        Event event = eventClient.create(aRandom.event().tickets([ticket]).build())
        Integer ticketId = event.firstTicket().id

        and:
        int firstPriceChange = 5
        dynamicRuleClient.create(aRandom.dynamicRule(ticketId).inventoryThreshold(50).priceChange(firstPriceChange).build())

        and:
        int secondPriceChange = 10
        dynamicRuleClient.create(aRandom.dynamicRule(ticketId).inventoryThreshold(75).priceChange(secondPriceChange).build())

        when:
        sellTickets(ticketId, 75)

        then:
        eventClient.find(event.id).firstTicket().currentPrice == basePrice + firstPriceChange + secondPriceChange
    }

    def sellTickets(Integer ticketId, Integer quantity) {
        Transaction transaction = aRandom.transaction()
                .quantity(quantity)
                .ticketId(ticketId)
                .build()
        transactionClient.create(transaction)
    }
}

