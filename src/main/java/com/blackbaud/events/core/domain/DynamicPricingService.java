package com.blackbaud.events.core.domain;


import com.blackbaud.events.api.Ticket;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DynamicPricingService {

    public BigDecimal getCurrentPrice(Ticket ticket) {
        return ticket.getBasePrice();
    }
}
