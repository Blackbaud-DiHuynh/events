package com.blackbaud.events.api;

import com.blackbaud.rest.api.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event implements ApiEntity<Integer> {

    private Integer id;

    private Date date;

    private Date time;

    private String location;

    private int capacity;

    private int ticketId;

    private String name;

    private Ticket ticket;
}
