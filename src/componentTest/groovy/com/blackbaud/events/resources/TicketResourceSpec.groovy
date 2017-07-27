package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.core.domain.EventEntity
import com.blackbaud.events.core.domain.EventRepository
import com.blackbaud.events.core.domain.TicketEntity
import com.blackbaud.events.core.domain.TicketRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class TicketResourceSpec extends Specification {

    @Autowired
    private TicketRepository ticketRepository

    def "Basic crud operations"() {
        given:
        TicketEntity ticketEntity = aRandom.ticketEntity().id(null).build()

        when:
        TicketEntity savedTicket = ticketRepository.save(ticketEntity)

        then:
        savedTicket.id > 0

        when:
        savedTicket.setBasePrice(BigDecimal.ONE)
        TicketEntity updatedTicket = ticketRepository.save(savedTicket)

        then:
        updatedTicket.basePrice == BigDecimal.ONE

        when:
        ticketRepository.delete(updatedTicket)

        then:
        ticketRepository.findAll().size() == 0
    }
}
