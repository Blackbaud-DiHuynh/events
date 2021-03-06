package com.blackbaud.events.api;

import com.blackbaud.testsupport.RandomGenerator;
import lombok.experimental.Delegate;

public class ClientARandom {

    public static final ClientARandom aRandom = new ClientARandom();

    @Delegate
    private RandomClientBuilderSupport randomClientBuilderSupport = new RandomClientBuilderSupport();
    @Delegate
    private RandomGenerator randomGenerator = new RandomGenerator();

}
