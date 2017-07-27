package com.blackbaud.events.resources;

import com.blackbaud.events.api.Event;
import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.events.core.domain.DynamicPricingService;
import com.blackbaud.events.core.domain.EventEntity;
import com.blackbaud.events.core.domain.EventRepository;
import com.blackbaud.events.core.domain.TicketEntity;
import com.blackbaud.events.core.domain.TicketRepository;
import com.blackbaud.events.core.domain.TransactionEntity;
import com.blackbaud.events.core.domain.TransactionRepository;
import com.blackbaud.mapper.ApiEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path(ResourcePaths.EVENT_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private EventConverter eventConverter = new EventConverter();
    private ApiEntityMapper<Ticket, TicketEntity> ticketMapper = new ApiEntityMapper<>(Ticket.class, TicketEntity.class);

    @Autowired
    private DynamicPricingService dynamicPricingService;

    @GET
    public List<Event> findAll() {
        return eventConverter.toApiList((List<EventEntity>) eventRepository.findAll());
    }

    @GET
    @Path("{id}")
    public Event get(@PathParam("id") Integer id) {
        EventEntity eventEntity = eventRepository.findOne(id);
        List<TicketEntity> ticketEntities = ticketRepository.findByEventId(eventEntity.getId());

        Event event = eventConverter.toApi(eventEntity);
        event.setTickets(ticketMapper.toApiList(ticketEntities));

        event.getTickets().forEach(ticket -> ticket.setCurrentPrice(dynamicPricingService.getCurrentPrice(ticket)));

        event.setRemainingInventory(getRemainingInventory(event));
        return event;
    }

    private Integer getRemainingInventory(Event event) {
        List<Integer> ticketIds = event.getTickets().stream().map(ticket -> ticket.getId()).collect(Collectors.toList());
        List<TransactionEntity> transactions = transactionRepository.findByTicketIdIn(ticketIds);
        Integer totalTicketsSold = transactions.stream().mapToInt(TransactionEntity::getQuantity).sum();
        return event.getCapacity() - totalTicketsSold;
    }

    @POST
    public Event create(@Valid Event event) {
        EventEntity entity = eventConverter.toEntity(event);
        EventEntity createdEvent = eventRepository.save(entity);
        List<TicketEntity> ticketEntities = ticketMapper.toEntityList(event.getTickets());
        ticketEntities.forEach(ticket -> ticket.setEventId(createdEvent.getId()));
        ticketRepository.save(ticketEntities);
        Event savedEvent = eventConverter.toApi(createdEvent);
        savedEvent.setTickets(ticketMapper.toApiList(ticketEntities));
        return savedEvent;
    }

    @PUT
    @Path("{id}")
    public Event update(@PathParam("id") Integer id, @Valid Event event) {
        EventEntity savedEvent = eventRepository.save(eventConverter.toEntity(event));
        return eventConverter.toApi(savedEvent);
    }

    public static class EventConverter extends ApiEntityMapper<Event, EventEntity> {

        public EventConverter() {
            super(Event.class, EventEntity.class);
        }
    }
}
