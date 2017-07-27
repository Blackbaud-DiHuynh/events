package com.blackbaud.events.client;

import com.blackbaud.rest.client.CrudClient;
import com.blackbaud.rest.client.CrudClientRequest;
import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.api.Event;

import java.util.List;

public class EventClient extends CrudClient<Event, EventClient> {

    public EventClient(String baseUrl) {
        super(baseUrl, ResourcePaths.EVENT_PATH, Event.class);
    }

}
