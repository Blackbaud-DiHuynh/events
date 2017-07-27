package com.blackbaud.events.api;

import static com.blackbaud.events.api.ClientARandom.aRandom

class RandomTicketBuilder extends Ticket.TicketBuilder {

    public RandomTicketBuilder() {
        this.id(aRandom.intId())
        .basePrice(BigDecimal.valueOf(aRandom.nextDouble()))
        .eventId(aRandom.intId())
    }

}
