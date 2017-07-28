package com.blackbaud.events.core.domain;


import com.blackbaud.events.api.DynamicRule;
import com.blackbaud.events.api.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DynamicPricingService {

    @Autowired
    DynamicRuleRepository ruleRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public BigDecimal getCurrentPrice(Ticket ticket) {
        List<TransactionEntity> transactions = transactionRepository.findByTicketId(ticket.getId());
        Integer totalTicketsSold = transactions.stream().mapToInt(TransactionEntity::getQuantity).sum();
        Integer ticketCapacity = ticket.getCapacity() == null ? 0 : ticket.getCapacity();
        Integer ticketRemaining = ticketCapacity - totalTicketsSold;

        List<DynamicRuleEntity> dynamicRules = ruleRepository.findByTicketId(ticket.getId());
        if (!dynamicRules.isEmpty()) {

            BigDecimal totalPriceChange = dynamicRules.stream()
                    .filter(rule -> rule.shouldApply(ticketRemaining))
                    .map(DynamicRuleEntity::getPriceChange)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return ticket.getBasePrice().add(totalPriceChange);
        }
        return ticket.getBasePrice();
    }
}
