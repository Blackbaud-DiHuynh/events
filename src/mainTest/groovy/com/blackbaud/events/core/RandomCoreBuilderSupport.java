package com.blackbaud.events.core;
import com.blackbaud.events.core.domain.RandomEventEntityBuilder;

public class RandomCoreBuilderSupport {

    public RandomEventEntityBuilder eventEntity() {
        return new RandomEventEntityBuilder();
    }

}
