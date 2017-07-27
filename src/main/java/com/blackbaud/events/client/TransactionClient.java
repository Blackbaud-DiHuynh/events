package com.blackbaud.events.client;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.events.api.Transaction;
import com.blackbaud.rest.client.CrudClient;

public class TransactionClient extends CrudClient<Transaction, TransactionClient> {

    public TransactionClient(String baseUrl) {
        super(baseUrl, ResourcePaths.TRANSACTION_PATH, Transaction.class);
    }

}
