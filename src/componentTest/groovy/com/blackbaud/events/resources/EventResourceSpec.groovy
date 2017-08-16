package com.blackbaud.events.resources

import com.blackbaud.boot.exception.NotFoundException
import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.Event
import com.blackbaud.events.api.Transaction
import com.blackbaud.events.client.EventClient
import com.blackbaud.events.client.TicketClient
import com.blackbaud.events.client.TransactionClient
import com.blackbaud.events.core.domain.EventRepository
import com.blackbaud.events.core.domain.TicketRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class EventResourceSpec extends Specification {

    @Autowired
    private EventRepository eventRepository

    @Autowired
    private TicketRepository ticketRepository

    @Autowired
    private EventClient eventClient

    @Autowired
    private TicketClient ticketClient

    @Autowired
    private TransactionClient transactionClient

    def "Get event includes ticket info"() {
        given:
        Event savedEvent = eventClient.create(aRandom.event().build())

        when:
        Event returnedEvent = eventClient.find(savedEvent.id)

        then:
        returnedEvent.tickets[0].currentPrice == savedEvent.tickets[0].basePrice
    }

    def "can CRUD"() {
        given:
        Event event = aRandom.event().build()

        when:
        Event savedEvent = eventClient.create(event)

        then:
        savedEvent.id > 0

        when:
        String newLocation = aRandom.words(10)
        savedEvent.setLocation(newLocation)
        Event updatedEvent = eventClient.update(savedEvent)

        then:
        updatedEvent.location == newLocation

        and:
        eventClient.find(updatedEvent.id).location == newLocation

        when:
        eventClient.delete(updatedEvent.id)

        and:
        eventClient.find(updatedEvent.id)

        then:
        thrown(NotFoundException)
    }

    def "should be able to delete nonexistent events"() {
        when:
        eventClient.delete(aRandom.intId())

        then:
        noExceptionThrown()
    }

    def "should save ticket information when posting an event"() {
        given:
        Event event = aRandom.event().build()

        when:
        Event createdEvent = eventClient.create(event)

        then: "we save the ticket price in the database"
        ticketRepository.findByEventId(createdEvent.id).size() == 1
    }

    def "get event should return inventory remaining"() {
        given:
        int capacity = 100
        Event event = eventClient.create(aRandom.event().capacity(capacity).build())

        when:
        Event eventDetail = eventClient.find(event.id)

        then:
        eventDetail.remainingInventory == capacity

        when:
        sellOneTicket(eventDetail.tickets[0].id)
        sellOneTicket(eventDetail.tickets[0].id)

        then:
        capacity - 2 == eventClient.find(event.id).remainingInventory
    }

    def "getAll should return tickets and their prices"() {
        given:
        eventClient.create(aRandom.event().build())
        eventClient.create(aRandom.event().build())

        when:
        List<Event> events = eventClient.findMany()

        then:
        events.each {
            assert it.tickets != null
        }
    }

    def sellOneTicket(Integer ticketId) {
        Transaction transaction = aRandom.transaction().quantity(1)
                .ticketId(ticketId)
                .build()
        transactionClient.create(transaction)
    }
}
