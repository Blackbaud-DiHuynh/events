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
import spock.lang.Ignore
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

    int firstThreshold = 50
    int firstPriceChange = 5

    int secondThreshold = 25
    int secondPriceChange = 10

    def "when an event reaches inventory threshold, ticket price should automatically adjusts"() {
        given:
        Ticket ticket = aRandom.ticket().basePrice(basePrice).capacity(capacity).build()
        Event event = eventClient.create(aRandom.event().tickets([ticket]).build())

        and:
        dynamicRuleClient.create(aRandom.dynamicRule(event.firstTicket().id)
                                         .inventoryThreshold(firstThreshold)
                                         .priceChange(firstPriceChange)
                                         .build())

        when:
        Event eventDetail = eventClient.find(event.id)

        then:
        eventDetail.tickets[0].currentPrice == basePrice

        when:
        sellTickets(eventDetail.firstTicket().id, 50)

        then:
        getCurrentPriceForEvent(eventDetail) == basePrice + 5
    }

    def "should support multiple dynamic rules"() {
        given:
        Ticket ticket = aRandom.ticket().basePrice(basePrice).capacity(capacity).build()
        Event event = eventClient.create(aRandom.event().tickets([ticket]).build())
        Integer ticketId = event.firstTicket().id

        and: "add all dynamic rules"
        dynamicRuleClient.create(aRandom.dynamicRule(ticketId).inventoryThreshold(firstThreshold).priceChange(firstPriceChange).build())
        dynamicRuleClient.create(aRandom.dynamicRule(ticketId).inventoryThreshold(secondThreshold).priceChange(secondPriceChange).build())

        when:
        sellTickets(ticketId, 20)

        then:
        getCurrentPriceForEvent(event) == basePrice

        when:
        sellTickets(ticketId, 34)

        then:
        getCurrentPriceForEvent(event) == basePrice + firstPriceChange

        when:
        sellTickets(ticketId, 25)

        then:
        getCurrentPriceForEvent(event) == basePrice + firstPriceChange + secondPriceChange
    }

    def BigDecimal getCurrentPriceForEvent(Event event) {
        eventClient.find(event.id).firstTicket().currentPrice
    }

    def sellTickets(Integer ticketId, Integer quantity) {
        Transaction transaction = aRandom.transaction()
                .quantity(quantity)
                .ticketId(ticketId)
                .build()
        transactionClient.create(transaction)
    }
}

