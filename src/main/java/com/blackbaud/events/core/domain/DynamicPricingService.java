package com.blackbaud.events.core.domain;


import com.blackbaud.events.api.DynamicRule;
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
    TransactionRepository transactionRepository;

    public BigDecimal getCurrentPrice(Ticket ticket) {
        List<TransactionEntity> transactions = transactionRepository.findByTicketId(ticket.getId());
        Integer totalTicketsSold = transactions.stream().mapToInt(TransactionEntity::getQuantity).sum();
        Integer ticketRemaining = ticket.getCapacity() - totalTicketsSold;

        List<DynamicRuleEntity> dynamicRules = ruleRepository.findByTicketId(ticket.getId());
        if (!dynamicRules.isEmpty()) {
            DynamicRuleEntity dynamicRule = dynamicRules.get(0);
            if (dynamicRule.shouldApply(ticketRemaining)) {
                return dynamicRule.apply(ticket.getBasePrice());
            }
        }
        return ticket.getBasePrice();
    }
}
