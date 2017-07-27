package com.blackbaud.events.api;

public class RandomClientBuilderSupport {

    public RandomTicketBuilder ticket() {
        return new RandomTicketBuilder();
    }

    public RandomEventBuilder event() {
        return new RandomEventBuilder();
    }

    public RandomDynamicRuleBuilder dynamicRule() { return new RandomDynamicRuleBuilder(); }


}
