package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.api.Event
import com.blackbaud.events.client.EventClient
import com.blackbaud.events.core.domain.EventEntity
import com.blackbaud.events.core.domain.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class EventResourceSpec extends Specification {

    @Autowired
    private EventRepository eventRepository

    @Autowired
    private EventClient eventClient

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
    }
}
