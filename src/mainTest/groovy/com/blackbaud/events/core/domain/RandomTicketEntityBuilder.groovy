package com.blackbaud.events.core.domain;

import static com.blackbaud.events.core.CoreARandom.aRandom

class RandomTicketEntityBuilder extends TicketEntity.TicketEntityBuilder {

    public RandomTicketEntityBuilder() {
        this.id(aRandom.nextInt())
        .basePrice(BigDecimal.valueOf(aRandom.nextDouble()))
        .eventId(aRandom.nextInt())
    }
}
