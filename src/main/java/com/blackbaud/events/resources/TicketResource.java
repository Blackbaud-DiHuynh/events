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

import javax.validation.Valid;
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

    @POST
    public Ticket createTicket(Ticket ticket) {
        TicketEntity ticketEntity = ticketMapper.toEntity(ticket);
        TicketEntity savedEntity = ticketRepository.save(ticketEntity);
        return ticketMapper.toApi(savedEntity);
    }

    @PUT
    @Path("{id}")
    public Ticket update(@PathParam("id") Integer id, Ticket ticket) {
        TicketEntity ticketEntity = ticketMapper.toEntity(ticket);
        TicketEntity savedEntity = ticketRepository.save(ticketEntity);
        return ticketMapper.toApi(savedEntity);
    }

    @DELETE
    @Path("{id}")
    public void deleteTicket(@PathParam("id") Integer id) {
        ticketRepository.delete(id);
    }

}
