package com.blackbaud.events.resources;

import com.blackbaud.boot.exception.NotFoundException;
import com.blackbaud.events.api.Event;
import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.events.core.domain.DynamicPricingService;
import com.blackbaud.events.core.domain.EventEntity;
import com.blackbaud.events.core.domain.EventErrorCodes;
import com.blackbaud.events.core.domain.EventRepository;
import com.blackbaud.events.core.domain.EventService;
import com.blackbaud.events.core.domain.TicketEntity;
import com.blackbaud.events.core.domain.TicketRepository;
import com.blackbaud.events.core.domain.TransactionEntity;
import com.blackbaud.events.core.domain.TransactionRepository;
import com.blackbaud.mapper.ApiEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
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
@Slf4j
public class EventResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    private EventConverter eventConverter = new EventConverter();

    @GET
    public List<Event> findAll() {
        List<EventEntity> allEvents = (List<EventEntity>) eventRepository.findAll();
        return allEvents.stream().map(event -> eventService.getEventWithTicketInfo(event)).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public Event get(@PathParam("id") Integer id) {
        EventEntity eventEntity = eventRepository.findOne(id);
        if (eventEntity == null) {
            throw new NotFoundException(EventErrorCodes.EVENT_NOT_FOUND, "Event with id={} does not exist", id);
        }
        return eventService.getEventWithTicketInfo(eventEntity);
    }
    @POST
    public Event create(@Valid Event event) {
        return eventService.create(event);
    }

    @PUT
    @Path("{id}")
    public Event update(@PathParam("id") Integer id, @Valid Event event) {
        EventEntity savedEvent = eventRepository.save(eventConverter.toEntity(event));
        return eventConverter.toApi(savedEvent);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        try {
            eventRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            return;
        }
    }

    public static class EventConverter extends ApiEntityMapper<Event, EventEntity> {

        public EventConverter() {
            super(Event.class, EventEntity.class);
        }
    }
}
