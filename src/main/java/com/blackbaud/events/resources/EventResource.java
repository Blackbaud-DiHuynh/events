package com.blackbaud.events.resources;

import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Event;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Path(ResourcePaths.EVENT_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    @Context
    private UriInfo uriInfo;

    @GET
    public List<Event> findAll() {
        return null;
    }



}
