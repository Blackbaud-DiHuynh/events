package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.Ticket
import com.blackbaud.events.client.TicketClient
import com.blackbaud.events.core.domain.TicketRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class TicketResourceSpec extends Specification {

    @Autowired
    private TicketRepository ticketRepository

    @Autowired
    private TicketClient ticketClient

    def "Basic crud operations"() {
        given:
        Ticket ticket = aRandom.ticket().build()

        when:
        Ticket savedTicket = ticketClient.create(ticket)

        then:
        savedTicket.id > 0

        when:
        savedTicket.setBasePrice(BigDecimal.ONE)
        Ticket updatedTicket = ticketClient.update(savedTicket)

        then:
        updatedTicket.basePrice == BigDecimal.ONE

        when:
        ticketClient.delete(updatedTicket)

        then:
        ticketClient.findAll().size() == 0
    }
}
