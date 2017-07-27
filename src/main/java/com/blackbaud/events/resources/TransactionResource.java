package com.blackbaud.events.resources;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.events.api.Transaction;
import com.blackbaud.events.core.domain.TicketEntity;
import com.blackbaud.events.core.domain.TransactionEntity;
import com.blackbaud.events.core.domain.TransactionRepository;
import com.blackbaud.mapper.ApiEntityMapper;
import com.blackbaud.security.InvocationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

    @POST
    public Transaction purchaseTicket(Transaction transaction) {
        // todo validate quantity is available
        TransactionEntity transactionEntity = transactionMapper.toEntity(transaction);
        TransactionEntity savedEntity = transactionRepository.save(transactionEntity);
        return transactionMapper.toApi(savedEntity);
    }

    @DELETE
    @Path("{id}")
    public void deleteTicket(@PathParam("id") Integer id) {
        transactionRepository.delete(id);
    }

}
