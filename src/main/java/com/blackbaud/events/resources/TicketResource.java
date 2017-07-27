package com.blackbaud.events.resources;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.events.core.domain.TicketEntity;
import com.blackbaud.events.core.domain.TicketRepository;
import com.blackbaud.mapper.ApiEntityMapper;
import com.blackbaud.security.InvocationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Component
@Path(ResourcePaths.TICKET_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource {

    private ApiEntityMapper<Ticket, TicketEntity> ticketMapper = new ApiEntityMapper<>(Ticket.class, TicketEntity.class);


    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    InvocationContext invocationContext;

    @GET
    public List<Ticket> getTickets() {
        List<TicketEntity> ticketEntities = (List<TicketEntity>) ticketRepository.findAll();
        return ticketMapper.toApiList(ticketEntities);
    }

    @GET
    @Path("{eventId}")
    public Ticket getTicket(@PathParam("eventId") Long eventId) {
        TicketEntity ticketEntity = ticketRepository.findOneByEventId(eventId);
        Ticket ticket = ticketMapper.toApi(ticketEntity);
        return ticket;
    }

}
