package com.blackbaud.events.api;

import com.blackbaud.rest.api.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    private String name;

    private List<Ticket> tickets;

    private Integer remainingInventory;

    public int getFirstTicketId() {
        return tickets.get(0).getId();
    }

    public Ticket getFirstTicket() {
        return tickets.get(0);
    }
}
