package com.blackbaud.events.core.domain;


import com.blackbaud.events.api.DynamicRule;
import com.blackbaud.events.api.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DynamicPricingService {

    @Autowired
    DynamicRuleRepository ruleRepository;

    public BigDecimal getCurrentPrice(Ticket ticket) {
        return ticket.getBasePrice();
    }
}
