package com.blackbaud.events.core;
import com.blackbaud.events.core.domain.RandomEventEntityBuilder;
import com.blackbaud.events.core.domain.RandomTicketEntityBuilder;

public class RandomCoreBuilderSupport {

    public RandomEventEntityBuilder eventEntity() {
        return new RandomEventEntityBuilder();
    }

    public RandomTicketEntityBuilder ticketEntity() {
        return new RandomTicketEntityBuilder();
    }

}
