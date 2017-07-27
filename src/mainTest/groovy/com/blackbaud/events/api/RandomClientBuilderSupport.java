package com.blackbaud.events.api;
import com.blackbaud.events.api.RandomEventBuilder;

public class RandomClientBuilderSupport {

    public RandomEventBuilder event() {
        return new RandomEventBuilder();
    }

}
