package com.blackbaud.events.client;

import com.blackbaud.events.api.Event;
import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.rest.client.CrudClient;

public class EventClient extends CrudClient<Event, EventClient> {

    public EventClient(String baseUrl) {
        super(baseUrl, ResourcePaths.EVENT_PATH, Event.class);
    }
}
