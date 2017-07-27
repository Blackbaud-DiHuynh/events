package com.blackbaud.events.core.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TicketRepository extends PagingAndSortingRepository<TicketEntity, Long> {

    TicketEntity findOneByEventId(Long eventId);

}
