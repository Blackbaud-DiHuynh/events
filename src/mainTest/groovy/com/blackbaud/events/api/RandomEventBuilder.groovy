package com.blackbaud.events.api;

import static com.blackbaud.events.api.ClientARandom.aRandom

class RandomEventBuilder extends Event.EventBuilder {

    public RandomEventBuilder() {
        int eventId = aRandom.intId()
        this.id(eventId)
                .capacity(Math.abs(aRandom.nextInt()))
                .date(aRandom.sqlDateInFuture())
                .location(aRandom.words(10))
                .time(aRandom.sqlDate())
                .name(aRandom.name())
                .tickets([aRandom.ticket().id(null).eventId(eventId).build()])
    }
}
