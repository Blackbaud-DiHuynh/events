package com.blackbaud.events.api

import java.math.RoundingMode;

import static com.blackbaud.events.api.ClientARandom.aRandom

class RandomTicketBuilder extends Ticket.TicketBuilder {

    public RandomTicketBuilder() {
        this.id(aRandom.intId())
        .basePrice(BigDecimal.valueOf(aRandom.nextDouble()).setScale(2, RoundingMode.HALF_UP))
        .eventId(aRandom.intId())
    }

}
