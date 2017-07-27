package com.blackbaud.events.api;

import static com.blackbaud.events.api.ClientARandom.aRandom

class RandomEventBuilder extends Event.EventBuilder {

    public RandomEventBuilder() {
        this.id(aRandom.intId())
                .capacity(Math.abs(aRandom.nextInt()))
                .date(aRandom.sqlDateInFuture())
                .location(aRandom.words(10))
                .ticketId(aRandom.intId())
                .time(aRandom.sqlDate())
                .name(aRandom.name())
    }

}
