package com.blackbaud.events.resources

import com.blackbaud.events.ComponentTest
import com.blackbaud.events.core.domain.EventEntity
import com.blackbaud.events.core.domain.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import static com.blackbaud.events.core.CoreARandom.aRandom

@ComponentTest
class EventResourceSpec extends Specification {

    @Autowired
    private EventRepository eventRepository

    def "can CRUD"() {
        given:
        EventEntity eventEntity = aRandom.eventEntity().build()

        when:
        EventEntity savedEvent = eventRepository.save(eventEntity)

        then:
        savedEvent.id > 0

        when:
        String newLocation = aRandom.words(10)
        eventEntity.setLocation(newLocation)
        EventEntity updatedEvent = eventRepository.save(eventEntity)

        then:
        updatedEvent.location == newLocation

        when:
        eventRepository.delete(eventEntity)

        then:
        eventRepository.findAll().size() == 0
    }
}
