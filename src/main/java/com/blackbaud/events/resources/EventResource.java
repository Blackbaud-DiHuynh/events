package com.blackbaud.events.resources;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Event;

import java.util.Arrays;
import java.util.List;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.blackbaud.events.core.domain.EventEntity;
import com.blackbaud.events.core.domain.EventRepository;
import com.blackbaud.mapper.ApiEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Path(ResourcePaths.EVENT_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private EventRepository eventRepository;

    private EventConverter eventConverter = new EventConverter();

    @GET
    public List<Event> findAll() {
        return eventConverter.toApiList((List<EventEntity>) eventRepository.findAll());
    }

    @GET
    @Path("{id}")
    public Event get(@PathParam("id")Integer id) {
        return eventConverter.toApi(eventRepository.findOne(id));
    }

    @POST
    public Event create(@Valid Event event) {
        EventEntity entity = eventConverter.toEntity(event);
        EventEntity createdEntity = eventRepository.save(entity);
        return eventConverter.toApi(createdEntity);
    }

    @PUT
    @Path("{id}")
    public Event update(@PathParam("id")Integer id, @Valid Event event) {
        EventEntity savedEvent = eventRepository.save(eventConverter.toEntity(event));
        return eventConverter.toApi(savedEvent);
    }

    public static class EventConverter extends ApiEntityMapper<Event, EventEntity> {

        public EventConverter() {
            super(Event.class, EventEntity.class);
        }
    }
}
