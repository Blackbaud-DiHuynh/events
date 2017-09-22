package com.blackbaud.events.core.domain;


import com.blackbaud.events.api.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DynamicPricingService {

    @Autowired
    DynamicRuleRepository ruleRepository;

    @Autowired
    TicketService ticketService;

    public BigDecimal getCurrentPrice(Ticket ticket) {
        List<DynamicRuleEntity> dynamicRules = ruleRepository.findByTicketId(ticket.getId());
        if (!dynamicRules.isEmpty()) {

            BigDecimal totalPriceChange = dynamicRules.stream()
                    .filter(rule -> rule.shouldApply(ticketService.calculateTicketsRemaining(ticket)))
                    .map(DynamicRuleEntity::getPriceChange)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return ticket.getBasePrice().add(totalPriceChange);
        }
        return ticket.getBasePrice();
    }

}

