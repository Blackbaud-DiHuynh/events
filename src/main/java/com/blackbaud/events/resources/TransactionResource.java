package com.blackbaud.events.resources;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Transaction;
import com.blackbaud.events.core.domain.TicketRepository;
import com.blackbaud.events.core.domain.TicketService;
import com.blackbaud.events.core.domain.TransactionEntity;
import com.blackbaud.events.core.domain.TransactionRepository;
import com.blackbaud.events.core.domain.TransactionService;
import com.blackbaud.mapper.ApiEntityMapper;
import com.blackbaud.security.InvocationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Component
@Path(ResourcePaths.TRANSACTION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private ApiEntityMapper<Transaction, TransactionEntity> transactionMapper = new ApiEntityMapper<>(Transaction.class, TransactionEntity.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    InvocationContext invocationContext;

    @Autowired
    TransactionService transactionService;

    @POST
    public Transaction purchaseTicket(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transaction);
        transactionService.validateTransaction(transactionEntity);
        TransactionEntity savedEntity = transactionRepository.save(transactionEntity);
        return transactionMapper.toApi(savedEntity);
    }

    @DELETE
    @Path("{id}")
    public void deleteTicket(@PathParam("id") Integer id) {
        transactionRepository.delete(id);
    }

}
