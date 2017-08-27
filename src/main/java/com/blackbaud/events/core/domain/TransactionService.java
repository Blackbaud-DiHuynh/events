package com.blackbaud.events.core.domain;

import com.blackbaud.boot.exception.BadRequestException;
import com.blackbaud.events.core.domain.TicketEntity;
import com.blackbaud.events.core.domain.TicketRepository;
import com.blackbaud.events.core.domain.TicketService;
import com.blackbaud.events.core.domain.TransactionEntity;
import com.blackbaud.events.core.domain.TransactionErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    public void validateTransaction(TransactionEntity transaction) {
        if (!transaction.isValid()) {
            throw new BadRequestException(TransactionErrorCodes.INVALID_QUANTITY, "Can not purchase a negative amount {} of tickets.", transaction.getQuantity());
        }
        validateCapacity(transaction);
    }

    private void validateCapacity(TransactionEntity transaction) {
        TicketEntity ticket = ticketRepository.findOne(transaction.getTicketId());
        if (ticket == null) {
            throw new BadRequestException(TransactionErrorCodes.INVALID_TICKET, "Ticket {} does not exist", transaction.getTicketId());
        }
        if (transaction.getQuantity() > ticketService.calculateTicketsRemaining(ticket)) {
            throw new BadRequestException(TransactionErrorCodes.INVALID_QUANTITY, "The number of tickets quantity {} is more than capacity {}.", transaction.getQuantity(), ticket.getCapacity());
        }
    }
}
