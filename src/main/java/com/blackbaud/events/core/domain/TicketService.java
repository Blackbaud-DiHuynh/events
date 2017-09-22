package com.blackbaud.events.core.domain;

import com.blackbaud.events.api.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketService {

    @Autowired
    TransactionRepository transactionRepository;

    private Integer calculateTicketsRemaining(Integer ticketId, Integer capacity) {
        List<TransactionEntity> transactions = transactionRepository.findByTicketId(ticketId);
        Integer totalTicketsSold = transactions.stream().mapToInt(TransactionEntity::getQuantity).sum();
        Integer ticketCapacity = capacity == null ? 0 : capacity;
        return ticketCapacity - totalTicketsSold;
    }

    public Integer calculateTicketsRemaining(Ticket ticket) {
        return calculateTicketsRemaining(ticket.getId(), ticket.getCapacity());
    }

    public Integer calculateTicketsRemaining(TicketEntity ticket) {
        return calculateTicketsRemaining(ticket.getId(), ticket.getCapacity());
    }
}
