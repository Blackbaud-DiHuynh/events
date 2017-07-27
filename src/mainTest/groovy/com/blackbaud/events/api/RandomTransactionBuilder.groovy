package com.blackbaud.events.api

import java.math.RoundingMode

import static com.blackbaud.events.api.ClientARandom.aRandom

class RandomTransactionBuilder extends Transaction.TransactionBuilder {

    public RandomTransactionBuilder() {
        this.id(aRandom.intId())
        .ticketId(aRandom.intId())
        .quantity(aRandom.intBetween(0, 100))
        .unitPrice(BigDecimal.valueOf(aRandom.nextDouble()).setScale(2, RoundingMode.HALF_UP))
    }

}
