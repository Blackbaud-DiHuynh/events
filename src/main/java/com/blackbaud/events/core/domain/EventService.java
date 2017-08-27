package com.blackbaud.events.core.domain;

import com.blackbaud.events.api.Event;
import com.blackbaud.events.api.Ticket;
import com.blackbaud.events.resources.EventResource;
import com.blackbaud.mapper.ApiEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    DynamicPricingService dynamicPricingService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    EventRepository eventRepository;

    private EventResource.EventConverter eventConverter = new EventResource.EventConverter();
    private ApiEntityMapper<Ticket, TicketEntity> ticketMapper = new ApiEntityMapper<>(Ticket.class, TicketEntity.class);

    public Event getEventWithTicketInfo(EventEntity eventEntity) {
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

    public Event create(Event event) {
        EventEntity entity = eventConverter.toEntity(event);
        EventEntity createdEvent = eventRepository.save(entity);
        List<TicketEntity> ticketEntities = ticketMapper.toEntityList(event.getTickets());
        ticketEntities.forEach(ticket -> ticket.setEventId(createdEvent.getId()));
        ticketEntities = (List<TicketEntity>) ticketRepository.save(ticketEntities);
        Event savedEvent = eventConverter.toApi(createdEvent);
        savedEvent.setTickets(ticketMapper.toApiList(ticketEntities));
        return savedEvent;
    }
}
