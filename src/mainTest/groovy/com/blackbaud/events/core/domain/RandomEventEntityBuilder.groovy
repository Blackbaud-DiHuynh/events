package com.blackbaud.events.core.domain;

import static com.blackbaud.events.core.CoreARandom.aRandom

class RandomEventEntityBuilder extends EventEntity.EventEntityBuilder {

    public RandomEventEntityBuilder() {
        this.capacity(Math.abs(aRandom.nextInt()))
        .date(aRandom.sqlDateInFuture())
        .location(aRandom.words(10))
        .ticketId(aRandom.intId())
        .time(aRandom.sqlDate())
    }
}
