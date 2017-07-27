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

    def "when an event reaches inventory threshold, ticket price should automatically adjusts"() {
        given:
        int capacity = 100
        BigDecimal basePrice = BigDecimal.TEN
        Ticket ticket = aRandom.ticket().basePrice(basePrice).capacity(capacity).build()
        Event event = eventClient.create(aRandom.event().tickets([ticket]).build())

        and:
        dynamicRuleClient.create(aRandom.dynamicRule()
                                         .ticketId(event.firstTicket().id)
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

    def sellTickets(Integer ticketId, Integer quantity) {
        Transaction transaction = aRandom.transaction()
                .quantity(quantity)
                .ticketId(ticketId)
                .build()
        transactionClient.create(transaction)
    }
}

