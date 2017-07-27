package com.blackbaud.events.client;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.rest.client.CrudClient;

public class TicketClient extends CrudClient<Ticket, TicketClient> {

    public TicketClient(String baseUrl) {
        super(baseUrl, ResourcePaths.TICKET_PATH, Ticket.class);
    }

}
