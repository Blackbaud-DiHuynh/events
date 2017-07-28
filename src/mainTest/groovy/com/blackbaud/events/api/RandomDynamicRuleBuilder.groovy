package com.blackbaud.events.api

import static com.blackbaud.events.api.ClientARandom.aRandom

class RandomDynamicRuleBuilder extends DynamicRule.DynamicRuleBuilder {

    public RandomDynamicRuleBuilder() {
        this.id(aRandom.intId())
                .priceChange(aRandom.intBetween(0, 100))
                .ticketId(aRandom.intId())
                .inventoryThreshold(aRandom.intBetween(0, 100))
                .type(DynamicRuleType.CAPACITY)
    }


}
